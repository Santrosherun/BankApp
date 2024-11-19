/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers.tableLists;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Storage;
import core.models.Transaction;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Allison Ruiz
 */
public class TransactionsListController {
    public static Response updateTransactionsList(DefaultTableModel model){
        try {
            model.setRowCount(0);
            Storage storage = Storage.getInstance();
            ArrayList<Transaction> transactions = storage.getTransactions();
            
            if (transactions == null || transactions.isEmpty()) {
                return new Response("The list is empty.", Status.NO_CONTENT);
            }
            
            ArrayList<Transaction> transactionsCopy = (ArrayList<Transaction>) transactions.clone();
            Collections.reverse(transactionsCopy);

            for (Transaction transaction : transactionsCopy) {
                model.addRow(new Object[]{transaction.getType().name(), (transaction.getSourceAccount() != null ? transaction.getSourceAccount().getId() : "None"), (transaction.getDestinationAccount()!= null ? transaction.getDestinationAccount().getId() : "None"), transaction.getAmount()});
            }
            
            return new Response("List updated succesfully.", Status.OK);
        } catch (Exception e) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }
}
