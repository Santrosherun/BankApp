/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers.transactions;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Account;
import core.models.Storage;
import core.models.Transaction;
import core.models.utils.TransactionType;

/**
 *
 * @author Allison Ruiz
 */
public class DepositController {
    public static Response createDeposit(String destinationAccountId, String amount){
        double amount1;
        try {
            amount1 = Double.parseDouble(amount);
            
            if(amount1 < 0){
                return new Response("The deposit amount must be positive.", Status.BAD_REQUEST);
            }
            Storage storage = Storage.getInstance();
            Account destinationAccount = null;
            for (Account account : storage.getAccounts()) {
                if (account.getId().equals(destinationAccountId)) {
                    destinationAccount = account;
                }
            }
            if(destinationAccount == null){
                return new Response("The account is not registered.", Status.BAD_REQUEST);
            }
            
            double balance;
            balance = destinationAccount.getBalance();
            balance = balance + amount1;
            
            destinationAccount.setBalance(balance);

            storage.addTransaction(new Transaction(TransactionType.DEPOSIT, null, destinationAccount, amount1));
            return new Response("Deposit accepted.", Status.OK);
            
        } catch (Exception e) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }
}
