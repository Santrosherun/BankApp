/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers.transactions;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Account;
import core.models.Storage;
import core.models.transactions.Withdraw;
import java.util.ArrayList;

/**
 *
 * @author sddva
 */
public class WithdrawController {
    public static Response createWithdraw(String sourceAccountId, String amount){
        try {
            Storage storage = Storage.getInstance();
            ArrayList<Account> accounts = storage.getAccounts();
            Account sourceAccount = null;
            
            double doubleAmount;
            
            doubleAmount = Double.parseDouble(amount);
            
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
                return new Response("Not enough founds", Status.BAD_REQUEST);
            }
            
            Withdraw withdraw = new Withdraw("WITHDRAW", sourceAccount,null ,doubleAmount);
            withdraw.execute(doubleAmount, sourceAccount);
            storage.addTransaction(withdraw);
            return new Response("Withdraw accepted", Status.OK);
        } catch (NumberFormatException e) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }
}
