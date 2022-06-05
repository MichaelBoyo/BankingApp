package FinApp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BankTest {
    Bank bank;
    @BeforeEach void setUp(){
        bank = new Bank();
    }
    @Test void thatWeHavaABank(){
        assertNotNull(bank);
    }
    @Test void thatWeCanHaveCustomers(){
        Customer customer = new Customer("Michael","Boyo",
                "boyomichaelbidemi@gmail.com","08103297538",
                1996,10,9,
                Gender.MALE);
        bank.addCustomer(customer);

         customer = new Customer("Odogwu","MAlay",
                "odogwu@gmail.com","08103297538",
                1996,10,9,
                Gender.MALE);
        bank.addCustomer(customer);



        List<Customer> customerList;
        customerList = bank.allCustomers();
        for(Customer custome1 : customerList){
            System.out.println(custome1);
        }




    }

}
