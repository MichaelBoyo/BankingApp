package FinApp;

import java.util.ArrayList;
import java.util.List;

public class Bank {
    private final List<Customer> customers = new ArrayList<>();

    public List<Customer> allCustomers() {
        return customers;
    }
    public Customer getACustomer(String email){
        for(Customer customer: customers){
            if(customer.getEmail().equalsIgnoreCase(email)){
                return customer;
            }
        }
        return null;

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

    public void addCustomer(Customer customer) {
        customers.add(customer);
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