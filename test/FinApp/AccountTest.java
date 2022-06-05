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
        account.deposit(3500);

        assertEquals(3500, account.getBalance("1234"));
    }
    @Test void Withdrawal(){
        Account account = new Account("Michael Boyo","1234");
        account.deposit(3500);
        account.withdraw("1234",2500);
        assertEquals(1000, account.getBalance("1234"));
    }
    @Test void accountTypeSavings(){
        Account account = new Account("Michael Boyo","1234");
        assertEquals(account.getAccountType(),"SAVINGS");
    }

    @Test void accountTypeCurrent(){
        Account account = new Account("Michael Boyo","1234");
        account.setAccountType("current");
        assertEquals(account.getAccountType(),"CURRENT");
    }
    @Test void negativeAmountDeposit(){
        Account account = new Account("Michael Boyo","1234");
        assertThrows(IllegalArgumentException.class,
                ()->  account.deposit(-1200));
        assertEquals(account.getBalance("1234"),0);
    }
    @Test void wrongAmountWithdrawal(){
        Account account = new Account("Michael Boyo","1234");
        account.deposit(1200);
       IllegalArgumentException e = assertThrows(IllegalArgumentException.class ,
                ()-> account.withdraw("1234",12000));
       assertEquals(e.getMessage(),"invalid amount");


    }
    @Test void wrongPinWithdrawal(){
        Account account = new Account("Michael Boyo","1234");
        account.deposit(1200);

        IllegalArgumentException f = assertThrows(IllegalArgumentException.class ,
                ()-> account.withdraw("1233",12000));
        assertEquals(f.getMessage(),"Wrong pin");

        assertEquals(account.getBalance("1234"),1200);

    }
    @Test void transfer(){
        Account account = new Account("Michael Boyo","1234");
        account.deposit(1200);

        account.transfer(1002,1000,"1234");
    }

}