package FinApp;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Bank {
    public List<Customer> allCustomers() {
        return customers;
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }
    public Customer viewProfile(String email){
        int id = 0;
        for (int i = 0; i <customers.size() ; i++) {
            Customer customer = customers.get(i);
            if(Objects.equals(email, customer.getEmail())){
                id = i;
            }
        }

        return customers.get(id);
    }

    private List<Customer> customers = new ArrayList<>();


}
