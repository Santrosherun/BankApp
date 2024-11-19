/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers.tableLists;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Account;
import core.models.Storage;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Allison Ruiz
 */
public class AccountsListController {
    public static Response updateAccountsList(DefaultTableModel model){
        try {
            model.setRowCount(0);
            Storage storage = Storage.getInstance();
            ArrayList<Account> accounts = storage.getAccounts();
            
            if (accounts == null || accounts.isEmpty()) {
                return new Response("The list is empty.", Status.NO_CONTENT);
            }
            
            accounts.sort((obj1, obj2) -> (obj1.getId().compareTo(obj2.getId())));
            for (Account account : accounts) {
                model.addRow(new Object[]{account.getId(), account.getOwner().getId(), account.getBalance()});
            }
            
            return new Response("List updated succesfully.", Status.OK);
        } catch (Exception e) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }
}
