public interface Transaction {
    int getId();

    TransactionStatus getStatus();

    String getFrom();

    String getTo();

    double getAmount();

    void setStatus(TransactionStatus status);

    void setAmount(double amount);

    void setFrom(String from);

    void setTo(String to);
}
