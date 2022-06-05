package FinApp;

import java.nio.channels.IllegalBlockingModeException;
import java.util.Objects;

public class Account {

    public Account(String name, String pin) {
        this.name = name;
        this.pin = pin;
        accountNumber = ++uid;
        accountType = String.valueOf(accountTypes.SAVINGS);
    }

    public Account(Customer customer,String pin) {
        this.pin = pin;
        this.name =customer.getName();
        accountNumber = ++uid;
        accountType = String.valueOf(accountTypes.SAVINGS);

    }

    public static void resetAccountNumber() {
        uid = 1000;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String type) {
        if (type.equalsIgnoreCase("Current")) {
            accountType = String.valueOf(accountTypes.CURRENT);
        } else accountType = String.valueOf(accountTypes.SAVINGS);
    }

    public int getAccountNumber(String pinNim) {
        int number = 0;
        if (Objects.equals(pin, pinNim)) {
            number = accountNumber;
        }
        return number;
    }

    public String getAccountName() {
        return name;
    }

    public void deposit(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("negative amount not accepted");
        }
        balance += amount;
    }

    public double getBalance(String pin) {

        if (!Objects.equals(this.pin, pin)) {
           throw new IllegalArgumentException("wrong pin");
        }
        return balance;
    }

    public void withdraw(String pin, int amount) {



        if (!Objects.equals(this.pin, pin)) {
            throw new IllegalArgumentException("Wrong pin");
        }
        if (amount > balance) {
            throw new IllegalArgumentException("invalid amount");
        }


        balance -= amount;
    }

    public void transfer(int receiverAccountNo, int amount, String pin) {
        if (!Objects.equals(this.pin, pin)) {
            throw new IllegalArgumentException("Wrong pin");
        }
        if (amount > balance) {
            throw new IllegalArgumentException("invalid amount");
        }
        balance -=amount;
    }

    private enum accountTypes {SAVINGS, CURRENT}
    private static int uid = 1000;
    protected int accountNumber;
    protected String pin;
    protected double balance;
    private final String name;
    private String accountType;


}
