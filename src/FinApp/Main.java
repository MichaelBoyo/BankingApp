package FinApp;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Bank boyoBank = new Bank();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("*".repeat(5) + "KOKO Bank" + "*".repeat(5));
        int sentinel = 0;

        try {
            main(sentinel);
        } catch (InputMismatchException | IllegalArgumentException | NullPointerException e) {
            System.out.println("error: " + e);
        } finally {
            System.out.println("Thanks for Banking with us!");
        }

    }

    private static void main(int sentinel) {
        while (sentinel != -1) {
            System.out.println("""
                    1 -> SignUp
                    2 -> SignIn to existing Account
                    3 -> view all Customers
                    4 -> delete profile
                    0 -> End
                    """);
            int choice = scanner.nextInt();
            switch (choice) {
                default -> System.out.println("invalid selection.. try again");
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
                    if (customers.size() == 0) {
                        System.out.println("No customers registered yet");
                    } else {
                        for (Customer customer : customers) {
                            System.out.println(customer);
                        }
                    }
                }
                case 4 -> {
                    System.out.println("""
                            Are you sure
                            1. yes
                            2. no, i changed my mind
                            """);
                    int input = scanner.nextInt();
                    switch (input) {
                        case 1 -> {
                            System.out.println("Alright..Enter email");
                            String email = scanner.next();
                            System.out.println(boyoBank.removeCustomer(email));
                        }
                        case 2 -> System.out.println("Smiles");
                    }
                }
                case 0 -> {
                    System.out.println("please, rate our app on a scale of 1 -10");
                    int rate = scanner.nextInt();
                    int i = 1;
                    while (i <= rate) {
                        try {
                            System.out.print("*");
                            Thread.sleep(400);
                        } catch (Exception ignored) {
                        }
                        i++;
                    }
                    System.out.println();

                    sentinel = -1;
                }
            }
        }
    }

    private static void sleep() {
        try {
            Thread.sleep(700);
        } catch (Exception ignored) {
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
        System.out.print("creating account");
        for (int i = 0; i <8 ; i++) {
            System.out.print(".");
            sleep();
        }
        System.out.println();
        System.out.println("Savings account created successfully");
        System.out.print("logging in");
        for (int i = 0; i <8 ; i++) {
            System.out.print(".");
            sleep();
        }
        System.out.println();

        logIn(email, pin);
    }

    public static void logIn(String email, String pi) {
        Customer customer = boyoBank.getACustomer(email);
        Account account = customer.getAnAccount(email);
        List<Account> accounts = customer.getAccounts();
        if (accounts.size() > 1) {
            System.out.println("you have " + accounts.size() + " accounts");
            System.out.println("choose account you want to logIn to:");
            int currentAcNum = 0;
            Account currentAC = customer.getAnAccount(email);
            for (Account acct : accounts) {
                if (acct.getAccountType().equals(String.valueOf(Account.accountTypes.CURRENT))) {
                    currentAcNum = acct.getAccountNumber();
                    currentAC = acct;
                    break;
                }
            }
            System.out.printf("""
                    1 -> Savings Account: %d
                    2 -> Current Account: %d
                    %n""", account.getAccountNumber(), currentAcNum);
            int type = scanner.nextInt();
            if (type == 2) {
                account = currentAC;
            }
        }

        System.out.println(customer.validatePin(email, pi));
        Scanner scanner = new Scanner(System.in);

        int sentinel = 0;
        while (sentinel != -1) {
            System.out.printf("you are currently logged into your %s account..\n" +
                            "account no: %d%n",
                    account.getAccountType(), account.getAccountNumber());
            System.out.printf("""
                    Hi, %s!
                    Balance: %s
                    1 -> Add Money+
                    2 -> Withdraw-
                    3 -> Transfer-
                    4 -> TransactionHistory
                    5 -> View All Accounts %n""", customer.getFirstName(), account.getBalance(pi));
            if (accounts.size() < 2) {
                System.out.println("6 -> create Current account");
            }
            System.out.println("0 -> Exit");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1 -> {
                    System.out.println("enter amount:");
                    int amount = scanner.nextInt();
                    account.deposit(amount);
                }
                case 2 -> {
                    System.out.println("enter amount:");
                    int amount = scanner.nextInt();

                    System.out.println("enter pin:");
                    String pin = scanner.next();
                    account.withdraw(pin, amount);
                }
                case 3 -> {
                    System.out.println("Enter account number");
                    int accountNumber = scanner.nextInt();
                    System.out.println("enter amount:");
                    int amount = scanner.nextInt();
                    System.out.println("enter pin:");
                    String pin = scanner.next();
                    String status = boyoBank.transfer(account, accountNumber, amount, pin);
                    System.out.println(status);
                }

                case 4 -> {
                    List<TransactionHistory> list = account.getTransactionHistoryList();
                    for (TransactionHistory history : list) {
                        System.out.println(history);
                    }
                }
                case 5 -> {
                    List<Account> a = customer.getAccounts();
                    for (Account acts : a) {
                        System.out.println(acts);
                    }
                }
                case 6 -> {
                    System.out.println("enter initial deposit, minimum of 100_000");
                    int initialDeposit = scanner.nextInt();
                    System.out.println("enter pin");
                    String pin = scanner.next();
                    CurrentAccount currentAccount = new CurrentAccount(customer.getName(),
                            customer.getEmail(), pin, initialDeposit);
                    customer.createAccount(currentAccount);
                    System.out.println("Current account created successfully");
                }
                case 0 -> sentinel = -1;
            }
        }
    }
}