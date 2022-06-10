package FinApp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Customer {
    private final int year;
    private final int month;
    private final int day;
    private final String firstName;
    private final String lastName;
    private final Gender gender;
    private final String email;
    private final String phoneNumber;
    private int age;
    private List<Account> accounts = new ArrayList<>();

    public Customer(String firstName, String lastName, String email, String phoneNumber, int yearOfBirth, int monthOfBirth, int dayOFBirth, Gender gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.year = yearOfBirth;
        this.month = monthOfBirth;
        this.day = dayOFBirth;
        this.gender = gender;
        if(!email.contains("@") || !email.contains(".")){
            throw new IllegalArgumentException("enter valid email");
        }
        this.email = email;
        if(phoneNumber.length() < 11){
            throw new IllegalArgumentException("invalid phone number");
        }
        this.phoneNumber = phoneNumber;
        setAge();
    }

    public void createAccount(Account account) {
        accounts.add(account);
    }

    public void closeAccount(int accountNumber) {
        Account ac = null;
        for (Account account : accounts) {
            if (Objects.equals(accountNumber, account.getAccountNumber())) {
                ac = account;
            }

        }
        accounts.remove(ac);
        if(ac ==null){
            throw new IllegalArgumentException(String.format("account number \"%s\" not found",accountNumber));
        }


    }
    public int getAccountNo(String email){
        int acctNo = 0;
        for (Account account: accounts){
            if(account.getEmail().equalsIgnoreCase(email)){
                acctNo = account.getAccountNumber();
            }
        }
        if(acctNo ==0){
            throw new IllegalArgumentException(String.format("account with email \"%s\" not found",email));
        }
        return acctNo;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public String getName() {
        return firstName + " " + lastName;
    }

    public int getAge() {
        return age;
    }

    private void setAge() {
        LocalDate today = LocalDate.now();
        LocalDate birthday = LocalDate.of(year, month, day);
        Period getAge = Period.between(birthday, today);
        this.age = getAge.getYears();
    }

    public String getDateOfBirth() {
        return year + " " + month + " " + day;
    }

    @Override
    public String toString() {
        return String.format("""
                CUSTOMER DETAILS
                Account name: %s
                email Address: %s
                phone: %s
                gender: %s
                """, getName(), getEmail(), getPhoneNumber(), getGender());
    }

    public String getGender() {
        return String.valueOf(gender);
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public  void deposit(int accountNo, int amount){
        Account account = getAnAccount(accountNo);
        account.deposit(amount);
    }
    public void withdraw(int accountNo, int amount, String pin){
        Account account = getAnAccount(accountNo);
        account.withdraw(pin,amount);
    }
    public BigDecimal balance(int accountNo, String pin){
        Account account = getAnAccount(accountNo);
        return account.getBalance(pin);
    }

    public Account getAnAccount(int accountNo) {
        for (Account account: accounts){
            if(account.getAccountNumber() == accountNo){
                return account;
            }
        }
        throw new IllegalArgumentException("Account with accountNo %d does not exist".formatted(accountNo));
    }
    public Account getAnAccount(String email) {
        for (Account account: accounts){
            if(account.getEmail().equalsIgnoreCase(email)){
                return account;
            }
        }
        throw new IllegalArgumentException("Account with accountNo %s does not exist".formatted(email));
    }
    public String validatePin(String email,String pin){
        for (Account account: accounts){
            if(account.getEmail().equalsIgnoreCase(email)){
                if(account.validatePin(pin)){
                    return "logged in successfully";
                }
            }
        }
        throw new IllegalArgumentException("Incorrect pin");

    }


}
