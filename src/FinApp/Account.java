package FinApp;

import java.util.Objects;

public class Account {
    private String name;
    protected int accountNumber;
    private static int uid = 1000;
    protected String pin;
    protected double balance;
    public Account(String name, String pin) {
        this.name = name;
        this.pin = pin;
        accountNumber = ++uid;
    }

    public static void resetAccountNumber() {
        uid = 1000;
    }

    public int getAccountNumber(String pinNim) {
        int number = 0;
        if(Objects.equals(pin, pinNim)){
            number =accountNumber;
        }
        return number;
    }

    public String getAccountName() {
        return name;
    }

    public void deposit(int accountNumber, int amount) {
        if(Objects.equals(this.accountNumber,accountNumber)){
            balance+=amount;
        }
    }

    public double getBalance(String pin) {
        double amount = 0;
        if(Objects.equals(this.pin,pin)){
            amount = balance;
        }
        return  amount;
    }

    public void withdraw(String pin, int amount) {
        if(Objects.equals(this.pin,pin)){
            balance-=amount;
        }
    }
}
