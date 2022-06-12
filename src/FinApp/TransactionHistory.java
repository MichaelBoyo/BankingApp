package FinApp;

import java.time.LocalDateTime;
import java.util.Date;

public class TransactionHistory {
    public TransactionHistory(int amount, Date date, String sender,
                              String receiver, TransactionType type) {
        this.amount = amount;
        this.date = date;
        this.sender = sender;
        this.receiver = receiver;
        this.type = String.valueOf(type);
    }

    public int getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }

    public Date getDate() {
        return date;
    }

    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }

    enum TransactionType {DEPOSIT,WITHDRAW,TRANSFER}
    private int amount;
    private String type;
    private Date date;
    private String sender;
    private String  receiver;

    @Override
    public String toString() {
        return String.format("""
               TransactionType: %s
               amount: %d
               date: %s
               sender_AccountNo: %s
               receiver_AccountNo: %s
                """,getType(),getAmount(),getDate(),getSender(),getReceiver());
    }
}
