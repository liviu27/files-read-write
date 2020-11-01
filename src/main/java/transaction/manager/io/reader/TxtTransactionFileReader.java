package transaction.manager.io.reader;

import transaction.manager.model.Transaction;
import transaction.manager.model.TransactionReadResults;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static transaction.config.AppConfig.getConfigInstance;
import static transaction.manager.util.TransactionMapper.mapTransactionStringToObject;

public class TxtTransactionFileReader extends TransactionAbstractFileReader {

    TxtTransactionFileReader(String fileName) {
        super(fileName);

    }

    @Override
    protected TransactionReadResults readTransactions(Path filePath) throws IOException {
        System.out.println("I am reading from .txt files");
        TransactionReadResults transactionReadResults = new TransactionReadResults();
        List<String> txLines = Files.readAllLines(filePath);
        for (String txLine : txLines) {
            readTransactions(transactionReadResults, txLine);
        }
        return transactionReadResults;
    }

    private void readTransactions(TransactionReadResults transactionReadResults, String txLine) {
        Transaction transaction = mapTransactionStringToObject(txLine, getConfigInstance().getTransactionSeparatorTxt());
        transactionReadResults.getTransactions().add(transaction);
    }


}


