//package FinApp;
//
//import java.util.Scanner;
//
//import static FinApp.BankMain.*;
//
//
//public class BoyoBank {
//    public static Gender gender;
//    public static Account account;
//    public static Scanner scanner = new Scanner(System.in);
//    public static Bank bank;
//    public static String email;
//
//    public static void main(String[] args) {
//
//        System.out.println("""
//                WELCOME TO BOYO BANK
//                enter your email to create account:
//                """);
//        email = scanner.next();
//        System.out.println("enter your pin ");
//        String pin = scanner.next();
//        account = new Account(email, pin);
//        bank = new Bank();
//        bank.addAccount(account);
//
//        int sentinel = 0;
//        while (sentinel != -1) {
//            System.out.println("""
//                    press 1 to deposit
//                    press 2 to withdraw
//                    press 3 to check Balance
//                    press 4 to view your account details
//                    press 5 to upgrade account
//                    press 6 to view user details
//                    enter 0 to exit
//                    """);
//            int input = scanner.nextInt();
//            switch (input) {
//                case 1 -> deposit(account);
//                case 2 -> withdraw(account);
//                case 3 -> balance(account,pin);
//                case 4 -> System.out.println(bank.viewAcctDetails(email));
//                case 5 -> upgrade();
//                case 6 -> System.out.println(bank.viewProfile(email));
//                case 0 -> sentinel = -1;
//            }
//        }
//        System.out.println("THANKS FOR BANKING WITH US");
//    }
//
//    private static void upgrade() {
//        System.out.println("Enter your first name:");
//        String firstName = scanner.next();
//
//        System.out.println("Enter your last name:");
//        String lastName = scanner.next();
//
//        System.out.println("Enter your phone Num:");
//        String phone = scanner.next();
//
//        System.out.println("Enter your date of birth (format: YY MM DD) \"separated by space\":");
//        int year = scanner.nextInt();
//        int month = scanner.nextInt();
//        int day = scanner.nextInt();
//
//        System.out.printf("""
//                Select your gender
//                1. %s
//                2. %s
//                """, Gender.MALE, Gender.FEMALE);
//        int choice = scanner.nextInt();
//        switch (choice) {
//            case 1 -> gender = Gender.MALE;
//            case 2 -> gender = Gender.FEMALE;
//        }
//
//        Customer customer = new Customer(firstName, lastName, email, phone, year, month, day, gender);
//        bank.addCustomer(customer);
//        System.out.println("account upgraded successfully");
//    }
//
//}
