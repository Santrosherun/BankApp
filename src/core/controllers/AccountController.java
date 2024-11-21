/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import java.util.ArrayList;
import core.models.Account;
import core.models.User;
import core.models.storages.AccountStorage;
import core.models.storages.UserStorage;
import java.util.Random;

/**
 *
 * @author sddva
 */
public class AccountController {
    public static Response createAccount(String userId, String balance){
        try {
            Random random = new Random();
            int first = random.nextInt(1000);
            int second = random.nextInt(1000000);
            int third = random.nextInt(100);

            String accountId = String.format("%03d", first) + "-" + String.format("%06d", second) + "-" + String.format("%02d", third);
            
            double doubleBalance;
            int intUserId, userCount = 0;
            User owner = null;
            
            doubleBalance = Double.parseDouble(balance);
            intUserId = Integer.parseInt(userId);
            AccountStorage accountStorage = AccountStorage.getInstance();
            UserStorage userStorage = UserStorage.getInstance();
            ArrayList<Account> accounts = accountStorage.getAccounts();
            ArrayList<User> users = userStorage.getUsers();
            
            if (userId.equals("")) {
                return new Response("Id must be not empty.", Status.BAD_REQUEST);
            }
            
            if (balance.equals("")) {
                return new Response("Initial balance must be not empty.", Status.BAD_REQUEST);
            }
            
            if(doubleBalance < 0){
                return new Response("Account cannot be create with negative balance", Status.BAD_REQUEST);
            }
            
            for(Account account : accounts){
                if(account.getId().equals(accountId)){
                    return new Response("An account with this id is already registered", Status.BAD_REQUEST);
                }
            }
            
            for (User user : users) {
                if(intUserId != user.getId()){
                    userCount = userCount + 1;
                }
            }
            
            if(userCount == users.size()){
                return new Response("User must be registered", Status.BAD_REQUEST);
            }
            
            if(intUserId < 0 || intUserId > 999999999){
                return new Response("The id must be a number between 0 and 999999999", Status.BAD_REQUEST);
            }
            
            for(User user : users){
                if(user.getId() == intUserId){
                    owner = user;
                }
            }
            
            if(owner == null){
                return new Response("User not found", Status.BAD_REQUEST);
            }
            
            accountStorage.addItem(new Account(accountId, owner, doubleBalance));
            return new Response("Account created succesfully", Status.OK);
        } catch (NumberFormatException e) {
            return new Response("Must be numeric", Status.INTERNAL_SERVER_ERROR);
        }   
        
    }
}
