//package FinApp;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.nio.channels.IllegalBlockingModeException;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//
//public class SavingsAccountTest {
//    SavingsAccount savings;
//    @BeforeEach void setUp(){
//        Customer customer = new Customer("Michael","Boyo",
//                "boyomichaelbidemi@gmail.com","08103297538",
//                1996,10,9,
//                Gender.MALE);
//        savings = new SavingsAccount(customer,"1234");
//    }
//    @Test void deposit(){
//        savings.deposit(500);
//        assertEquals(savings.getBalance("1234"),500);
//    }
//    @Test void accountLimit(){
//        savings.deposit(5_100_000);
//        assertEquals(savings.getBalance("1234"), 5_100_000);
//
//        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
//                ()-> savings.withdraw("1234",100));
//        assertEquals(e.getMessage(),"Account Balance limit exceeded, upgrade to current account");
//    }
//    @Test void dailyLimit(){
//        savings.deposit(2_000_000);
//        savings.withdraw("1234",600000);
//        savings.withdraw("1234",200000);
//        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
//                ()->  savings.withdraw("1234", 300000));
//        assertEquals(e.getMessage(),"Daily limit exceeded, upgrade to current account");
//
//
//        assertEquals(savings.getBalance("1234"),1_200_000);
//
//    }
//}