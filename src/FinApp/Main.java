package FinApp;

import java.util.List;
import java.util.Scanner;

public class Main {
    static Bank boyoBank = new Bank();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("*".repeat(5) + "BUGA BANK" + "*".repeat(5));
        int sentinel = 0;
        try {
            while (sentinel != -1) {
                System.out.println("""
                        Enter 1 to Create Account
                        Enter 2 to SignIn to existing Account
                        Enter 3 view all Customers
                        Enter 4 to close an Account
                        Enter 0 to End
                        """);
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1 -> createAccount();
                    case 2 -> {
                        System.out.println("enter email");
                        String email = scanner.next();
                        System.out.println("enter pin");
                        String pin = scanner.next();
                        logIn(email, pin);
                    }
                    case 3 -> {
                        List<Customer> customers = boyoBank.allCustomers();
                        for(Customer customer: customers){
                            System.out.println(customer);
                        }
                    }
                    case 4 -> {
                        System.out.println("""
                                Are you sure
                                1. yes
                                2. no, i changed my mind
                                """);
                        int input = scanner.nextInt();
                        switch (input){
                            case 1->{
                                System.out.println("Alright..Enter email");
                                String email = scanner.next();
                                System.out.println(boyoBank.removeCustomer(email));
                            }
                            case 2-> System.out.println("Smiles");
                        }
                    }
                    case 0 -> sentinel = -1;
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void createAccount() {
        System.out.println("enter first Name: ");
        String firstName = scanner.next();

        System.out.println("enter last Name: ");
        String lastname = scanner.next();

        System.out.println("enter email");
        String email = scanner.next();

        System.out.println("enter phoneNumber");
        String phoneNumber = scanner.next();

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

        Customer customer = new Customer(firstName, lastname,
                email, phoneNumber,
                year, month, day,
                gender);

        System.out.println("enter pin to activate Account: ");
        String pin = scanner.next();

        Account account = new Account(firstName, email, pin);
        customer.createAccount(account);

        boyoBank.addCustomer(customer);
        System.out.println("account created successfully");
    }

    public static void logIn(String email, String pi) {
        Customer customer = boyoBank.getACustomer(email);
        List<Account> accounts = customer.getAccounts();
        for (Account acct : accounts) {
            if (!acct.getEmail().equalsIgnoreCase(email)) {
                throw new IllegalArgumentException("email %s doesnt exist".formatted(email));
            }
        }
        System.out.println(customer.validatePin(email, pi));
        Scanner scanner = new Scanner(System.in);

        int sentinel = 0;
        while (sentinel != -1) {
            System.out.println("""
                    Enter 1 to Deposit
                    Enter 2 to Withdraw
                    Enter 3 to checkBalance
                    Enter 4 to View Account details
                    Enter 0 to Exit
                    """);
            int choice = scanner.nextInt();
            switch (choice) {
                case 1 -> {
                    System.out.println("enter accountNo");
                    int accountNo = scanner.nextInt();

                    System.out.println("enter amount:");
                    int amount = scanner.nextInt();
                    customer.deposit(accountNo, amount);
                }
                case 2 -> {
                    System.out.println("enter accountNo");
                    int accountNo = scanner.nextInt();

                    System.out.println("enter amount:");
                    int amount = scanner.nextInt();

                    System.out.println("enter pin:");
                    String pin = scanner.next();
                    customer.withdraw(accountNo, amount, pin);
                }
                case 3 -> {
                    System.out.println("enter accountNo");
                    int accountNo = scanner.nextInt();

                    System.out.println("enter pin");
                    String pin = scanner.next();
                    System.out.println(customer.balance(accountNo, pin));

                }
                case 4 -> {
                    System.out.println(customer.getAnAccount(email));

                }
                case 0 -> sentinel = -1;
            }
        }
    }
}