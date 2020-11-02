package transaction.manager.io.reader;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import transaction.manager.model.Transaction;
import transaction.manager.model.TransactionReadResults;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class JsonTransactionFileReader extends TransactionAbstractFileReader {

    public JsonTransactionFileReader(String fileName) {
        super(fileName);
    }


    @Override
    protected TransactionReadResults readTransactions(Path filePath) throws IOException {
        TransactionReadResults transactionReadResults = new TransactionReadResults();
        try (BufferedReader bufferedReader = Files.newBufferedReader(filePath)) {
            ObjectMapper objectMapper = new ObjectMapper();
            List<Transaction> transactions = objectMapper.readValue(bufferedReader, new TypeReference<List<Transaction>>() {
            });

            transactionReadResults.getTransactions().addAll(transactions);


        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return transactionReadResults;

    }
}
