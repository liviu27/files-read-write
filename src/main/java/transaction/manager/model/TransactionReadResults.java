package transaction.manager.model;

import java.util.ArrayList;
import java.util.List;

public class TransactionReadResults {
    private final List<Transaction> transaction;
    private final List<String> rejectedTransaction;

    public TransactionReadResults() {
        transaction = new ArrayList<>();
        rejectedTransaction = new ArrayList<>();
    }

    public List<Transaction> getTransactions() {
        return transaction;
    }

    public List<String> getRejectedTransaction() {
        return rejectedTransaction;
    }
}
