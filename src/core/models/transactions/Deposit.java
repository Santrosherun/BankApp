/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.transactions;

import core.models.Account;
import core.models.utils.OneAccountStrategy;

/**
 *
 * @author Allison Ruiz
 */
public class Deposit extends Transaction implements OneAccountStrategy{
    
    public Deposit(String type, Account sourceAccount, Account destinationAccount, double amount) {
        super(type, sourceAccount, destinationAccount, amount);
    }

    @Override
    public void execute(double amount, Account destinationAccount) {
        double balance;
        balance = destinationAccount.getBalance();
        balance = balance + amount;

        destinationAccount.setBalance(balance);
    }
    
}
