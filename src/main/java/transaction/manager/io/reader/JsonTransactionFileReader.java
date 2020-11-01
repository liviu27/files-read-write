package transaction.manager.io.reader;

import transaction.manager.model.TransactionReadResults;

public class JsonTransactionFileReader implements TransactionFileReader {
    private final String fileName;

    public JsonTransactionFileReader(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public TransactionReadResults readTransactions() {
        System.out.println("I am reading .json files: " +fileName);
        return new TransactionReadResults();
    }
}
