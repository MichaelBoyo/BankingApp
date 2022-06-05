package FinApp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {
    @BeforeEach void resetAccountNumber(){
        Account.resetAccountNumber();
    }
    @Test void createAccount(){
        Account account = new Account("Michael Boyo","1234");

        assertEquals(1001,account.getAccountNumber("1234"));
        assertEquals("Michael Boyo",account.getAccountName());

    }
    @Test void deposit(){
        Account account = new Account("Michael Boyo","1234");
        account.deposit(1001,3500);

        assertEquals(3500, account.getBalance("1234"));
    }
    @Test void Withdrawal(){
        Account account = new Account("Michael Boyo","1234");
        account.deposit(1001,3500);
        account.withdraw("1234",2500);
        assertEquals(1000, account.getBalance("1234"));
    }
}