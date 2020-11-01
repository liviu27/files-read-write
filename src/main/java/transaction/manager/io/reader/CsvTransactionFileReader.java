package transaction.manager.io.reader;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBeanBuilder;
import transaction.manager.model.Transaction;
import transaction.manager.model.TransactionReadResults;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static java.nio.file.Files.*;
import static transaction.config.AppConfig.getConfigInstance;
import static transaction.manager.util.TransactionMapper.mapTransactionStringToObject;

public class CsvTransactionFileReader extends TransactionAbstractFileReader {
    CsvTransactionFileReader(String fileName) {
        super(fileName);
    }

    @Override
    protected TransactionReadResults readTransactions(Path filePath) {
        System.out.println("I am reading from .csv files");
        TransactionReadResults transactionReadResults = new TransactionReadResults();
        try (BufferedReader reader = newBufferedReader(filePath)) {
            CsvToBeanBuilder<Transaction> txToBeanBuilder = new CsvToBeanBuilder<>(reader);
            List<Transaction> txLines = txToBeanBuilder.withType(Transaction.class).build().parse();
            for (Transaction txLine : txLines) {
                transactionReadResults.getTransactions().add(txLine);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return transactionReadResults;
    }
}
