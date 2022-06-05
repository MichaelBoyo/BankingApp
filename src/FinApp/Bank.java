package FinApp;

import java.util.ArrayList;
import java.util.List;

public class Bank {
    public List<Customer> allCustomers() {

        return customers;
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    private List<Customer> customers = new ArrayList<>();





}
