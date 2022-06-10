//package FinApp;
//
//import java.time.DateTimeException;
//import java.util.List;
//import java.util.Scanner;
//
//public class BankMain {
//    static Scanner scanner = new Scanner(System.in);
//
//    static Customer customer;
//    static Bank bank = new Bank();
//    static Account account;
//
//    public static void main(String[] args) {
//        System.out.println("WELCOME TO SEMICOLON Bank");
//
//        int sent = 0;
//        while (sent != -1) {
//            System.out.println("""
//                    press 1 to create new account
//                    press 2 to request account closure
//                    press 3 to to sign in to existing account
//                    press 4 to view allAccounts
//                    press 5 to end
//                    """);
//            int input = scanner.nextInt();
//            switch (input) {
//                case 1 -> createAccount();
//                case 2 -> accountClosure();
//                case 3 -> existingAccount();
//                case 4 -> allActs();
//                case 5 -> sent = -1;
//            }
//        }
//
//    }
//
//    private static void allActs() {
//        List<Account> acts = bank.viewAllAccounts();
//        for (Account account1 : acts) {
//            System.out.println(account1);
//        }
//    }
//
//    public static void accountClosure() {
//        System.out.println("enter your email:");
//        String email = scanner.next();
//        System.out.println(bank.closeAccount(email));
//    }
//
//    public static void existingAccount() {
//
//        System.out.println("enter email");
//        String email = scanner.next();
//        System.out.println("enter pin");
//        String pin = scanner.next();
//        Account ac = bank.viewAccount(email);
//        if (ac != null) {
//            int sentinel = 0;
//            while (sentinel != -1) {
//                System.out.println("""
//                        press 1 to deposit
//                        press 2 to withdraw
//                        press 3 to check Balance
//                        press 4 to view your profile
//                        press 5 to exit
//                        """);
//                int input = scanner.nextInt();
//                switch (input) {
//                    case 1 -> deposit(account);
//                    case 2 -> withdraw(account);
//                    case 3 -> balance(account,pin);
//                    case 4 -> System.out.println("Account No: " + account
//                            .getAccountNumber(pin) + "\n" + bank.viewProfile(email));
//                    case 5 -> sentinel = -1;
//                }
//            }
//        }
//    }
//
//    private static void createAccount() {
//        String email;
//        try {
//            System.out.println("Enter your first name:");
//            String firstName = scanner.next();
//
//            System.out.println("Enter your last name:");
//            String lastName = scanner.next();
//
//            System.out.println("Enter your email address:");
//            email = scanner.next();
//
//            System.out.println("Enter your phone Number:");
//            String phone = scanner.next();
//
//            System.out.println("Enter your date of birth (format: YY MM DD) \"separated by space\":");
//            int year = scanner.nextInt();
//            int month = scanner.nextInt();
//            int day = scanner.nextInt();
//
//            Gender gender = null;
//
//            System.out.printf("""
//                    Select your gender
//                    1. %s
//                    2. %s
//                    """, Gender.MALE, Gender.FEMALE);
//            int choice = scanner.nextInt();
//            switch (choice) {
//                case 1 -> gender = Gender.MALE;
//                case 2 -> gender = Gender.FEMALE;
//            }
//            customer = new Customer(firstName, lastName, email, phone, year, month, day, gender);
//            bank.addCustomer(customer);
//
//            System.out.println("enter pin to create your account");
//            String pin = scanner.next();
//
//            account = new Account(customer, pin);
//
//            bank.addAccount(account);
//
//            System.out.println("""
//                    account successfully crated
//                    your account Details are:
//                    """);
//            System.out.println("Account No: " + account
//                    .getAccountNumber(pin) + "\n" + bank.viewProfile(email));
//
//        } catch (IllegalArgumentException | DateTimeException | NullPointerException e) {
//            System.out.println(e.getMessage());
//        }
//    }
//
//
//    static void deposit(Account account) {
//        System.out.println("enter amount");
//        int amount = scanner.nextInt();
//
//        try {
//            account.deposit(amount);
//        } catch (IllegalArgumentException e) {
//            System.out.println(e.getMessage());
//        }
//        System.out.println("deposit successful");
//
//    }
//
//    public static void withdraw(Account account) {
//        System.out.println("Enter amount");
//        int amount = scanner.nextInt();
//        System.out.println("enter your pin");
//        String pin = scanner.next();
//
//        String message = "withdraw Successful";
//
//        try {
//            account.withdraw(pin, amount);
//        } catch (IllegalArgumentException e) {
//            System.out.println(e.getMessage());
//            message = "withdraw unsuccessful";
//
//        }
//        System.out.println(message);
//    }
//
//    public static void balance(Account account, String pin) {
//
//        try {
//            System.out.println("Account Balance: " + account.getBalance(pin));
//        } catch (IllegalArgumentException e) {
//            System.out.println(e.getMessage());
//        }
//
//    }
//}
