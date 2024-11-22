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
import core.models.transactions.Deposit;

/**
 *
 * @author Allison Ruiz
 */
public class DepositController {
    public static Response createDeposit(String destinationAccountId, String amount){
        double amount1;
        try {
           
            if (destinationAccountId.equals("")) {
                return new Response("Destination account id must be not empty.", Status.BAD_REQUEST);
            }
            
            if (amount.equals("")) {
                return new Response("Amount must be not empty.", Status.BAD_REQUEST);
            }
            
            try {
                amount1 = Double.parseDouble(amount);
            } catch (NumberFormatException e) {
                return new Response("The amount must be numeric", Status.BAD_REQUEST);
            }
            
            if(amount1 <= 0){
                return new Response("The deposit amount must be positive.", Status.BAD_REQUEST);
            }
            TransactionStorage transactionStorage = TransactionStorage.getInstance();
            AccountStorage accountStorage = AccountStorage.getInstance();
            Account destinationAccount = null;
            for (Account account : accountStorage.getAccounts()) {
                if (account.getId().equals(destinationAccountId)) {
                    destinationAccount = account;
                }
            }
            if(destinationAccount == null){
                return new Response("The account is not registered.", Status.BAD_REQUEST);
            }
            Deposit deposit = new Deposit("DEPOSIT", null, destinationAccount, amount1);
            deposit.execute(amount1, destinationAccount);
            transactionStorage.addItem(deposit);
            return new Response("Deposit accepted.", Status.OK);
            
        } catch (Exception e) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }
}
