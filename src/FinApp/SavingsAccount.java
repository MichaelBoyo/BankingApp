//package FinApp;
//
//import java.math.BigDecimal;
//import java.util.Objects;
//
//public class SavingsAccount extends Account {
//    public SavingsAccount(String name, String pin) {
//        super(name, pin);
//    }
//    public SavingsAccount(Customer customer, String pin) {
//        super(customer,pin);
//    }
////    @Override
////    public void withdraw(String pin, int amount){
//////        BigDecimal balance =super.getBalance();
////
////
////        if (Integer.parseInt(String.valueOf(balance)) > balanceLimit){
////            throw new IllegalArgumentException("Account Balance limit exceeded, upgrade to current account");
////        }
////        dailyWithdrawal+=amount;
////        if(dailyWithdrawal>dailyTransferLimit){
////            throw new IllegalArgumentException("Daily limit exceeded, upgrade to current account");
////        }
////
//////        balance -= amount;
//////        setBalance(balance);
////    }
//
////    @Override
////    public String toString() {
////        return """
////                Account name: %s
////                Account num: %d
////                balance: %f
////                """.formatted(getAccountName(),getAccountNumber(pin),getBalance(pin));
////    }
//
//    private final int balanceLimit = 5_000_000;
//    private final int dailyTransferLimit = 1_000_000;
//    private int dailyWithdrawal;
//}
