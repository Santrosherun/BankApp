/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.transactions;

import core.models.Account;
import core.models.utils.TwoAccountStrategy;

/**
 *
 * @author Allison Ruiz
 */
public class Transfer extends Transaction implements TwoAccountStrategy{
    
    public Transfer(String type, Account sourceAccount, Account destinationAccount, double amount) {
        super(type, sourceAccount, destinationAccount, amount);
    }

    @Override
    public void execute(double amount, Account destinationAccount, Account sourceAccount) {
        double temp1 = sourceAccount.getBalance();
        double temp2 = destinationAccount.getBalance();
        sourceAccount.setBalance(temp1 - amount);
        destinationAccount.setBalance(temp2 + amount);
    }
    
}
