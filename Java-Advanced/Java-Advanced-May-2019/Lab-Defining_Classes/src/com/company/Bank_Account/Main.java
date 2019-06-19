package com.company.Bank_Account;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        Map<Integer, BankAccount> accounts = new HashMap<>();
        String line;
        while (!"End".equals(line = bufferedReader.readLine())) {
            String[] tokens = line.split("\\s+");

            if (tokens.length == 1) {
                BankAccount account = new BankAccount();
                accounts.put(account.getId(), account);
                System.out.println(String.format("Account ID%d created", account.getId()));
            } else if (tokens.length == 2) {
                double interest = Double.parseDouble(tokens[1]);
                BankAccount.setInterestRate(interest);
            } else {
                String command = tokens[0];
                int id = Integer.parseInt(tokens[1]);

                if (!accounts.containsKey(id)) {
                    System.out.println("Account does not exist");
                    continue;
                }

                if ("Deposit".equals(command)) {
                    double amount = Double.parseDouble(tokens[2]);
                    accounts.get(id).deposit(amount);
                    System.out.println(String.format("Deposited %.0f to ID%d", amount, id));
                } else {
                    int years = Integer.parseInt(tokens[2]);
                    double interesting = accounts.get(id).getInterest(years);
                    System.out.println(String.format("%.2f", interesting));
                }
            }
        }
    }
}
