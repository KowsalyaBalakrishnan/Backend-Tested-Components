package PracticalUseCase.Performance.CompletableFuture;

public class CustomerTransaction {
    private int transactionId;
    private String transactionDesc;

    public CustomerTransaction() {
    }

    public CustomerTransaction(int transactionId, String transactionDesc) {
        this.transactionId = transactionId;
        this.transactionDesc = transactionDesc;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransactionDesc() {
        return transactionDesc;
    }

    public void setTransactionDesc(String transactionDesc) {
        this.transactionDesc = transactionDesc;
    }

    @Override
    public String toString() {
        return "CustomerTransaction{" +
                "transactionId=" + transactionId +
                ", transactionDesc='" + transactionDesc + '\'' +
                '}';
    }
}
