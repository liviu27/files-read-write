package transaction.manager.io.writer;

import com.fasterxml.jackson.databind.ObjectMapper;
import transaction.manager.model.Transaction;
import transaction.manager.model.TransactionOutput;
import transaction.manager.util.TransactionMapper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static transaction.manager.util.TransactionMapper.mapTransactionToTransactionOutput;

public class JsonTransactionFileWriter extends TransactionAbstractFileWriter {
    @Override
    protected boolean writeTransactionsInFile(List<Transaction> transactions, Path outputFile) {
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(outputFile)) {
            ObjectMapper objectMapper = new ObjectMapper();
            List<TransactionOutput> transactionOutputs = new ArrayList<>();
            for (Transaction tx : transactions) {
                transactionOutputs.add(mapTransactionToTransactionOutput(tx));
            }
            objectMapper.writeValue(bufferedWriter, transactionOutputs);

        } catch (IOException exception) {
            exception.printStackTrace();
            return false;
        }

        return true;
    }
}
