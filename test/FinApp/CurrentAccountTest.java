package FinApp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CurrentAccountTest {
    CurrentAccount currentAccount;
    Customer customer;

    @BeforeEach
    void setUp() {
        customer = new Customer("Michael", "Boyo",
                "boyomichaelbidemi@gmail.com", "08103297538",
                1996, 10, 9,
                Gender.MALE);
        currentAccount = new CurrentAccount(customer.getName(),customer.getEmail(),"1234",100_000);
    }
    @Test
    void getCurrentAccountNumber() {

        assertEquals(currentAccount.getAccountNumber(),2001);
    }
    @Test
    void getCurrentAccountNumbers() {
        assertEquals(currentAccount.getAccountNumber(),2001);
        customer = new Customer("Michael", "Boyo",
                "boyomichaelbidemi@gmail.com", "08103297538",
                1996, 10, 9,
                Gender.MALE);
        currentAccount = new CurrentAccount(customer.getName(),customer.getEmail(),"1234",100_000);
        assertEquals(currentAccount.getAccountNumber(),2002);
    }
    @Test void getBalance(){
        assertEquals(currentAccount.getBalance("1234"), BigDecimal.valueOf(100000));
        assertEquals(currentAccount.getAccountType(),String.valueOf(Account.accountTypes.CURRENT));
    }

}