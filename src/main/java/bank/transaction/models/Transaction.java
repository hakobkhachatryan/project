package bank.transaction.models;


import java.time.LocalDate;

public class Transaction {
    private int id;
    private String price;
    private String TransactionType;
    private String TransactionStatus;
    private LocalDate localDate = LocalDate.now() ;
    private String date = String.valueOf(localDate);

    public Transaction() {
    }

    public Transaction(int id,String price, String transactionType, String transactionStatus) {
        this.id = id;
        this.price = price;
        TransactionType = transactionType;
        TransactionStatus = transactionStatus;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTransactionType() {
        return TransactionType;
    }

    public void setTransactionType(String transactionType) {
        TransactionType = transactionType;
    }

    public String getTransactionStatus() {
        return TransactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        TransactionStatus = transactionStatus;
    }
}


