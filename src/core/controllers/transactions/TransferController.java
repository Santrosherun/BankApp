/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers.transactions;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Account;
import core.models.storages.AccountStorage;
import core.models.storages.TransactionStorage;
import core.models.transactions.Transfer;
import java.util.ArrayList;

/**
 *
 * @author sddva
 */
public class TransferController {
    public static Response createTransfer(String sourceAccountId, String destinationAccountId, String amount){
        try {
            TransactionStorage transactionStorage = TransactionStorage.getInstance();
            AccountStorage accountStorage = AccountStorage.getInstance();
            ArrayList<Account> accounts = accountStorage.getAccounts();
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
            
            Transfer transfer = new Transfer("TRANSFER", sourceAccount ,destinationAccount ,doubleAmount);
            transfer.execute(doubleAmount, destinationAccount, sourceAccount);
            transactionStorage.addTransaction(transfer);
            return new Response("Transfer accepted", Status.OK);
        } catch (NumberFormatException e) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }
}
