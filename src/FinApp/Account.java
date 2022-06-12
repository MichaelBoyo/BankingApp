package FinApp;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Account {

    protected static int uid = 1000;
    private List<TransactionHistory> transactionHistoryList = new ArrayList<>();
    protected int accountNumber;
    private final String pin;
    private final String email;
    protected BigDecimal balance;
    private final String accountName;
    private String accountType;

    public Account(String name,String email, String pin) {
        this.email = email;
        this.pin = pin;
        accountNumber += ++uid;
        this.accountName = name;
        accountType = String.valueOf(accountTypes.SAVINGS);
        balance = new BigDecimal(0);
    }
    public void setAccountNumber(int num){
        this.accountNumber = num;
    }

    public static void resetAccountNumber() {
        uid = 1000;
    }

    public String getEmail() {
        return email;
    }

    public List<TransactionHistory> getTransactionHistoryList() {
        return transactionHistoryList;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String type) {
        if (type.equalsIgnoreCase("Current")) {
            accountType = String.valueOf(accountTypes.CURRENT);
        } else accountType = String.valueOf(accountTypes.SAVINGS);
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getAccountName() {
        return accountName;
    }

    public void deposit(int amount) {
        BigDecimal bigDecimal = new BigDecimal(amount);
        if (amount < 0) {
            throw new IllegalArgumentException("negative amount not accepted");
        }
        System.out.println("deposit successful");
        System.out.println();
        balance = balance.add(bigDecimal);

        //transaction history
        Date date = new Date();
        TransactionHistory transaction = new TransactionHistory(amount,date,"self",
                "self", TransactionHistory.TransactionType.DEPOSIT);
        transactionHistoryList.add(transaction);
    }

    public BigDecimal getBalance(String pin) {
        if (!Objects.equals(this.pin, pin)) {
            throw new IllegalArgumentException("wrong pin");
        }
        return balance;
    }

    public void withdraw(String pin, int amount) {
        BigDecimal BigD_Amount = new BigDecimal(amount);
        if (!Objects.equals(this.pin, pin)) {
            throw new IllegalArgumentException("Wrong pin");
        }
        if (amount > Integer.parseInt(String.valueOf(balance))) {
            throw new IllegalArgumentException("invalid amount");
        }
        balance = balance.subtract(BigD_Amount);

        //transaction history
        Date date = new Date();
        TransactionHistory transaction = new TransactionHistory(amount,date,"self",
                "self", TransactionHistory.TransactionType.WITHDRAW);
        transactionHistoryList.add(transaction);

    }
    public boolean validatePin(String pin){
        if(!this.pin.equalsIgnoreCase(pin)){
            throw new IllegalArgumentException("Incorrect pin");
        }
        return this.pin.equalsIgnoreCase(pin);
    }

    @Override
    public String toString() {
        return String.format("""
                Account type: %s
                Account name: %s
                Account num: %d
                balance: %.2f
                """,getAccountType(),getAccountName(), getAccountNumber(), getBalance(pin)) ;
    }

    public void receiveTransferred(TransactionHistory transaction) {
        transactionHistoryList.add(transaction);
    }

    protected enum accountTypes {SAVINGS, CURRENT}

}