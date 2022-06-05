package FinApp;

import java.time.DateTimeException;
import java.util.Scanner;

public class BankMain {
    static Scanner scanner = new Scanner(System.in);
    static Account account;
    static Customer customer;

    public static void main(String[] args) {
        bank();


    }

    private static void bank() {
        Bank bank = new Bank();
        System.out.println("WELCOME TO SEMICOLON Bank");

        String email ="";

        try {
            System.out.println("Enter your first name:");
            String firstName = scanner.next();


            System.out.println("Enter your last name:");
            String lastName = scanner.next();

            System.out.println("Enter your email address:");
             email = scanner.next();

            System.out.println("Enter your phone Number:");
            String phone = scanner.next();

            System.out.println("Enter your date of birth (format: YY MM DD) \"separated by space\":");
            int year = scanner.nextInt();
            int month = scanner.nextInt();
            int day = scanner.nextInt();

            Gender gender = null;

            System.out.printf("""
                    Select your gender
                    1. %s
                    2. %s
                    """, Gender.MALE, Gender.FEMALE);
            int choice = scanner.nextInt();
            switch (choice) {
                case 1 -> gender = Gender.MALE;
                case 2 -> gender = Gender.FEMALE;
            }
            customer = new Customer(firstName, lastName, email, phone, year, month, day, gender);

        } catch (IllegalArgumentException | DateTimeException e) {
            System.out.println(e.getMessage());
        }


        try{
            bank.addCustomer(customer);

            System.out.println("enter pin to create your account");
            String pin = scanner.next();

            account = new Account(customer, pin);
            int sentinel = 0;
            while (sentinel != -1) {
                System.out.println("""
                    press 1 to deposit
                    press 2 to withdraw
                    press 3 to check Balance
                    press 4 to view your profile
                    press 5 to exit
                    """);
                int input = scanner.nextInt();
                switch (input) {
                    case 1 -> deposit();
                    case 2 -> withdraw();
                    case 3 -> balance();
                    case 4 -> System.out.println("Account No: " + account
                            .getAccountNumber(pin)+"\n"+bank.viewProfile(email));
                    case 5 -> sentinel = -1;
                }
            }
        }catch(NullPointerException g){
            System.out.println(g.getMessage());
        }


        System.out.println("Thanks for Banking with us");

    }

    private static void deposit() {
        System.out.println("enter amount");
        int amount = scanner.nextInt();

        try {
            account.deposit(amount);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("deposit successful");

    }

    public static void withdraw() {
        System.out.println("Enter amount");
        int amount = scanner.nextInt();
        System.out.println("enter your pin");
        String pin = scanner.next();

        String message ="withdraw Successful";

        try {
            account.withdraw(pin, amount);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            message = "withdraw unsuccessful";

        }
        System.out.println(message);
    }

    public static void balance() {
        System.out.println("enter your pin");
        String pin = scanner.next();

        try {
            System.out.println("Account Balance: " + account.getBalance(pin));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

    }
}
