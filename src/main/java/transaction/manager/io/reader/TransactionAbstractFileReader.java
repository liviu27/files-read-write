package transaction.manager.io.reader;

import transaction.manager.model.TransactionReadResults;
import transaction.manager.util.FileUtils;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static transaction.config.AppConfig.getConfigInstance;

public abstract class TransactionAbstractFileReader implements TransactionFileReader {
    private final String fileName;

    TransactionAbstractFileReader(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public TransactionReadResults readTransactions() {
        String transactionInputDir = getConfigInstance().getTransactionInputDirectory();
        Path filePath = Paths.get(transactionInputDir).resolve(fileName);
        try {
            TransactionReadResults transactionReadResults = readTransactions(filePath);
            if (transactionReadResults.getRejectedTransaction().isEmpty()) {
                FileUtils.copyFileAfterRead(filePath, false);
            }
            return transactionReadResults;
        } catch (Exception e) {
            FileUtils.copyFileAfterRead(filePath, true);
            e.printStackTrace();
        }

        return new TransactionReadResults();
    }

    protected abstract TransactionReadResults readTransactions(Path filePath) throws IOException;
}
