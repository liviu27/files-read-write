package transaction.manager.model;

import com.opencsv.bean.CsvBindByPosition;

public class Transaction {

    @CsvBindByPosition(position = 0)
    private long id;
    @CsvBindByPosition(position = 1)
    private String fromIban;
    @CsvBindByPosition(position = 2)
    private String toIban;
    @CsvBindByPosition(position = 3)
    private double amount;
    @CsvBindByPosition(position = 4)
    private String fromBank;
    @CsvBindByPosition(position = 5)
    private String toBank;


    public static BuildTransaction newBuild() {
        return new BuildTransaction();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFromIban() {
        return fromIban;
    }

    public void setFromIban(String fromIban) {
        this.fromIban = fromIban;
    }

    public String getToIban() {
        return toIban;
    }

    public void setToIban(String toIban) {
        this.toIban = toIban;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getFromBank() {
        return fromBank;
    }

    public void setFromBank(String fromBank) {
        this.fromBank = fromBank;
    }

    public String getToBank() {
        return toBank;
    }

    public void setToBank(String toBank) {
        this.toBank = toBank;
    }

    @Override
    public String toString() {
        return "id = " + id + "; " +
                "from Iban = " + fromIban + "; " +
                "to Iban = " + toIban + "; " +
                "amount =" + amount + "; " +
                "fromBank = " + fromBank + "; " +
                "toBank = " + toBank + "\n";
    }

    public static class BuildTransaction {
        private long id;
        private String fromIban;
        private String toIban;
        private double amount;
        private String fromBank;
        private String toBank;

        public BuildTransaction id(long id) {
            this.id = id;
            return this;
        }

        public BuildTransaction fromIban(String fromIban) {
            this.fromIban = fromIban;
            return this;
        }

        public BuildTransaction toIban(String toIban) {
            this.toIban = toIban;
            return this;
        }

        public BuildTransaction amount(double amount) {
            this.amount = amount;
            return this;
        }

        public BuildTransaction fromBank(String fromBank) {
            this.fromBank = fromBank;
            return this;
        }

        public BuildTransaction toBank(String toBank) {
            this.toBank = toBank;
            return this;
        }

        public Transaction build() {
            Transaction transaction = new Transaction();
            transaction.id = this.id;
            transaction.fromIban = this.fromIban;
            transaction.toIban = this.toIban;
            transaction.amount = this.amount;
            transaction.fromBank = this.fromBank;
            transaction.toBank = this.toBank;
            return transaction;
        }
    }

}
