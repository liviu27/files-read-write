package transaction.manager;

import transaction.config.AppConfig;
import transaction.manager.io.reader.FileType;
import transaction.manager.io.reader.TransactionFileReader;
import transaction.manager.io.reader.TransactionFileReaderFactory;
import transaction.manager.io.writer.CsvTransactionFileWriter;
import transaction.manager.io.writer.TransactionFileWriter;
import transaction.manager.io.writer.TransactionFileWriterFactory;
import transaction.manager.io.writer.XlsxTransactionFileWriter;
import transaction.manager.model.TransactionReadResults;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        AppConfig appConfig = AppConfig.getConfigInstance();
        System.out.println(appConfig.getTransactionInputDirectory());
        System.out.println(appConfig.getTransactionOutputDirectory());
        System.out.println(appConfig.getTransactionProcessedDirectory());
        System.out.println(appConfig.getTransactionRejectedDirectory());

        String transactionInputDir = appConfig.getTransactionInputDirectory();
        File input = new File(transactionInputDir);
        File[] files = input.listFiles();

        for (File file : files) {
            TransactionFileReader transactionFileReader = TransactionFileReaderFactory.createTransactionFileReader(file.getName());
            TransactionReadResults transactionReadResults = transactionFileReader.readTransactions();
//            System.out.println(transactionReadResults);

            String transactionOutputType = AppConfig.getConfigInstance().getTransactionOutputType();
            TransactionFileWriter transactionFileWriter = TransactionFileWriterFactory.createTransactionFileWriter(transactionOutputType);
            transactionFileWriter.writeTransactions(transactionReadResults.getTransactions());

        }

    }

}
