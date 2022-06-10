package FinApp;

import java.math.BigDecimal;
import java.util.Objects;

public class Account {

    protected static int uid = 1000;
    protected int accountNumber;
    protected String pin;
    private final String email;
    private BigDecimal balance;
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

    public static void resetAccountNumber() {
        uid = 1000;
    }

    public String getEmail() {
        return email;
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
    }
    public boolean validatePin(String pin){
        return this.pin.equalsIgnoreCase(pin);
    }

    @Override
    public String toString() {
        return String.format("""
                Account name: %s
                Account num: %d
                balance: %.2f
                """,getAccountName(), getAccountNumber(), getBalance(pin)) ;
    }
    protected enum accountTypes {SAVINGS, CURRENT}


}
