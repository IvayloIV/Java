package com.company.Bank_Account;

public class BankAccount {
    public static final double DEFAULT_INTEREST_RATE = 0.02;
    public static int idCount = 1;

    private int id;
    private double balance;
    private static double interestRate = DEFAULT_INTEREST_RATE;

    public BankAccount() {
        this.id = BankAccount.idCount++;
    }

    public int getId() {
        return this.id;
    }

    public static void setInterestRate(double interest) {
        BankAccount.interestRate = interest;
    }

    public double getInterest(int years) {
        return BankAccount.interestRate * years * this.balance;
    }

    public void deposit(double amount) {
        this.balance += amount;
    }

}
