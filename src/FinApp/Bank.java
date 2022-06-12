package FinApp;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Bank {
    private final List<Customer> customers = new ArrayList<>();

    public List<Customer> allCustomers() {
        return customers;
    }
    private void addMoney(Account account, int amount){
        BigDecimal amountBigDecimal = new BigDecimal(amount);
        account.balance = account.balance.add(amountBigDecimal);
    }
    private void removeMoney(Account account, int amount){
        BigDecimal amountBigDecimal = new BigDecimal(amount);
        account.balance = account.balance.subtract(amountBigDecimal);
    }

    public String transfer(Account account,int accountNumber, int amount, String pin){
        if(account.validatePin(pin)){
            removeMoney(account,amount);
            for (Customer customer: customers){
                List<Account> accounts = customer.getAccounts();
                for (Account acct : accounts){
                    if (accountNumber == acct.getAccountNumber()){
                        addMoney(acct,amount);

                        //transaction history
                        Date date = new Date();
                        TransactionHistory transaction = new TransactionHistory(amount,date,String.valueOf(account.getAccountNumber()),
                                String.valueOf(accountNumber), TransactionHistory.TransactionType.TRANSFER);
                        account.receiveTransferred(transaction);
                        acct.receiveTransferred(transaction);
                        return "transfer success";
                    }
                }
            }
        }

        throw new IllegalArgumentException("Account number is not valid");
    }
    public Customer getACustomer(String email){
        for(Customer customer: customers){
            if(customer.getEmail().equalsIgnoreCase(email)){
                return customer;
            }
        }
        throw new IllegalArgumentException(String.format("Customer with \"%s\"not found", email));

    }
    public String removeCustomer(String email){
        Customer customerToBeRemoved = null;
        for(Customer customer: customers){
            if(customer.getEmail().equalsIgnoreCase(email)){
                customerToBeRemoved = customer;
                customer.closeAccount(customer.getAccountNo(customer.getEmail()));
            }
        }
        if(customerToBeRemoved== null){
            throw new IllegalArgumentException(String.format("Account with email \"%s\" not found",email));
        }
        customers.remove(customerToBeRemoved);
        return "we'll miss you";
    }

    public void addCustomer(Customer newCustomer) {
        for(Customer customer: customers){
           if(customer.getEmail().equalsIgnoreCase(newCustomer.getEmail())){
                throw new IllegalArgumentException("Customer already exists");
            }
        }
        customers.add(newCustomer);
    }

    public Customer viewProfile(String email) {
        for (Customer customer : customers) {
            if (email == customer.getEmail()) {
                return customer;
            }
        }
        return null;
    }
}