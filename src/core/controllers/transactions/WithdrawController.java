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
import core.models.transactions.Withdraw;
import java.util.ArrayList;

/**
 *
 * @author sddva
 */
public class WithdrawController {
    public static Response createWithdraw(String sourceAccountId, String amount){
        try {
            TransactionStorage transactionStorage = TransactionStorage.getInstance();
            AccountStorage accountStorage = AccountStorage.getInstance();
            ArrayList<Account> accounts = accountStorage.getAccounts();
            Account sourceAccount = null;
            
            double doubleAmount;
            
            doubleAmount = Double.parseDouble(amount);
            
            if (sourceAccountId.equals("")) {
                return new Response("Source account id must be not empty.", Status.BAD_REQUEST);
            }
            
            if (amount.equals("")) {
                return new Response("Amount must be not empty.", Status.BAD_REQUEST);
            }
            
            for (Account account : accounts) {
                if(account.getId().equals(sourceAccountId)){
                    sourceAccount = account;
                }
            }
            
            if(doubleAmount <= 0){
                return new Response("The withdraw amount must be positive", Status.BAD_REQUEST);
            }
            
            if(sourceAccount == null){
                return new Response("The account is not registered", Status.BAD_REQUEST);
            }
            
            if(doubleAmount > sourceAccount.getBalance()){
                return new Response("Not enough funds", Status.BAD_REQUEST);
            }
            
            Withdraw withdraw = new Withdraw("WITHDRAW", sourceAccount,null ,doubleAmount);
            withdraw.execute(doubleAmount, sourceAccount);
            transactionStorage.addItem(withdraw);
            return new Response("Withdraw accepted", Status.OK);
        } catch (NumberFormatException e) {
            return new Response("Must be numeric", Status.INTERNAL_SERVER_ERROR);
        }
    }
}
