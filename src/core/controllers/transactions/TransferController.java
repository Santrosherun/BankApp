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
import java.util.ArrayList;

/**
 *
 * @author sddva
 */
public class TransferController {
    public static Response createTransfer(String sourceAccountId, String destinationAccountId, String amount){
        try {
            Storage storage = Storage.getInstance();
            ArrayList<Account> accounts = storage.getAccounts();
            Account sourceAccount = null;
            Account destinationAccount = null;
            
            double doubleAmount;
            
            doubleAmount = Double.parseDouble(amount);
            for (Account account : accounts) {
                if(account.getId().equals(sourceAccountId)){
                    sourceAccount = account;
                }
            }
            
            if(doubleAmount <= 0){
                return new Response("The transfer amount must be positive", Status.BAD_REQUEST);
            }
            
            for (Account account : accounts) {
                if(account.getId().equals(destinationAccountId)){
                    destinationAccount = account;
                }
            }
            
            if(sourceAccount == null){
                return new Response("The source account is not registered", Status.BAD_REQUEST);
            }
            
            if(destinationAccount == null){
                return new Response("The destination account is not registered", Status.BAD_REQUEST);
            }
            
            if(sourceAccount.getBalance() < doubleAmount){
                return new Response("The transfer amount cannot be greater than the source balance", Status.BAD_REQUEST);
            }
            
            if(sourceAccount == destinationAccount){
                return new Response("You can not send money to your own account.", Status.BAD_REQUEST);
            }
            
            double temp1 = sourceAccount.getBalance();
            double temp2 = destinationAccount.getBalance();
            sourceAccount.setBalance(temp1 - doubleAmount);
            destinationAccount.setBalance(temp2 + doubleAmount);
            
            storage.addTransaction(new Transaction(TransactionType.TRANSFER, sourceAccount ,destinationAccount ,doubleAmount));
            return new Response("Transfer accepted", Status.OK);
        } catch (NumberFormatException e) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }
}
