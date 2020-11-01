package transaction.manager.io.writer;

import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import transaction.manager.model.Transaction;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class CsvTransactionFileWriter extends TransactionAbstractFileWriter {

    @Override
    protected boolean writeTransactionsInFile(List<Transaction> transactions, Path outputFile) {
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(outputFile)) {
            bufferedWriter.write("ID; FromIban ; ToIban ; Amount; FromBank; ToBank");
            bufferedWriter.newLine();
            StatefulBeanToCsv beanToCsv = new StatefulBeanToCsvBuilder(bufferedWriter)
                    .withOrderedResults(true)
                    .withSeparator(';')
                    .withQuotechar((char) 0)
                    .build();
            beanToCsv.write(transactions);
        } catch (IOException | CsvDataTypeMismatchException | CsvRequiredFieldEmptyException exception) {
            exception.printStackTrace();
            return false;
        }
//        System.out.println("Scrie fisiere CSV");
        return true;
    }

}
