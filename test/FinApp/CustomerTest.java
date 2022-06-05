package FinApp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CustomerTest {
    Customer customer;

    @BeforeEach
    void setUp(){
        customer = new Customer("Michael","Boyo",
                "boyomichaelbidemi@gmail.com","08103297538",
                1996,10,9,
                Gender.MALE);
    }
    @Test void thatWeCAnHAveCustomer(){
        assertNotNull(customer);
    }
    @Test void getCustomerName(){
        assertEquals("Michael Boyo", customer.getName());
    }
    @Test void getAge(){
        assertEquals(25,customer.getAge());
    }
    @Test void getGender(){
        assertEquals("MALE", customer.getGender());
    }
    @Test void dateOfBirth(){
        assertEquals(customer.getDateOfBirth(), "1996 10 9");
    }
    @Test void getEmail(){
        assertEquals("boyomichaelbidemi@gmail.com", customer.getEmail());
    }

}