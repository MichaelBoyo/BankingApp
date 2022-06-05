package FinApp;

import java.util.Objects;

public class SavingsAccount extends Account {
    public SavingsAccount(String name, String pin) {
        super(name, pin);
    }
    public SavingsAccount(Customer customer, String pin) {
        super(customer,pin);
    }
    @Override
    public void withdraw(String pin, int amount){
        if (!Objects.equals(this.pin, pin)) {
            throw new IllegalArgumentException("Wrong pin");
        }
        if (amount > balance) {
            throw new IllegalArgumentException("invalid amount");
        }
        if (balance > balanceLimit){
            throw new IllegalArgumentException("Account Balance limit exceeded, upgrade to current account");
        }
        dailyWithdrawal+=amount;
        if(dailyWithdrawal>dailyTransferLimit){
            throw new IllegalArgumentException("Daily limit exceeded, upgrade to current account");
        }

        balance -= amount;
    }
    private final int balanceLimit = 5_000_000;
    private final int dailyTransferLimit = 1_000_000;
    private int dailyWithdrawal;
}
