package transaction.manager.io.writer;

import transaction.config.AppConfig;
import transaction.manager.model.Transaction;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public abstract class TransactionAbstractFileWriter implements TransactionFileWriter {


    @Override
    public boolean writeTransactions(List<Transaction> transactions) {
        String transactionOutputDirectory = AppConfig.getConfigInstance().getTransactionOutputDirectory();
        String fileType = AppConfig.getConfigInstance().getTransactionOutputType().toLowerCase();
        Path outputDir = Paths.get(transactionOutputDirectory);
        Path outputFile = outputDir.resolve("transactions_" + System.currentTimeMillis() + "." + fileType);
        if (!Files.exists(outputDir)) {
            try {
                Files.createDirectory(outputDir);
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
            return writeTransactionsInFile(transactions, outputFile);
    }

    protected abstract boolean writeTransactionsInFile(List<Transaction> transactions, Path outputFile);
}
