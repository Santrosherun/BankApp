/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers.tableLists;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.User;
import core.models.storages.UserStorage;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Allison Ruiz
 */
public class UsersListController {
    public static Response updateUsersList(DefaultTableModel model){
        try {
            model.setRowCount(0);
            UserStorage userStorage = UserStorage.getInstance();
            ArrayList<User> users = userStorage.getUsers();
            
            if (users == null || users.isEmpty()) {
                return new Response("The list is empty.", Status.NO_CONTENT);
            }
            
            users.sort((obj1, obj2) -> (obj1.getId() - obj2.getId()));
            for (User user : users) {
                model.addRow(new Object[]{user.getId(), user.getFirstname() + " " + user.getLastname(), user.getAge(), user.getNumAccounts()});
            }
            
            return new Response("List updated succesfully.", Status.OK);
        } catch (Exception e) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }
}
