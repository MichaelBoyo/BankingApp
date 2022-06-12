package FinApp;

import java.math.BigDecimal;

public class CurrentAccount extends Account{
    private static int acctNo = 2000;


    public CurrentAccount(String name, String email, String pin, int initialDeposit) {
        super(name, email, pin);
        if(initialDeposit <100_000){
            throw new IllegalArgumentException("initialDeposit must be greater than 100_000");
        }
        deposit(initialDeposit);
        setAccountType("Current");
        ++acctNo;
        setAccountNumber(acctNo);
    }

}
