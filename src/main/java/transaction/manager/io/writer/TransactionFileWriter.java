package transaction.manager.io.writer;

import transaction.manager.model.Transaction;

import java.util.List;

public interface TransactionFileWriter {
    boolean writeTransactions(List<Transaction> transactions);

}
