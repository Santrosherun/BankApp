/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.User;
import core.models.storages.UserStorage;
import java.util.ArrayList;

/**
 *
 * @author Allison Ruiz
 */
public class UserController {
    
    public static Response createUser(String id, String firstname, String lastname, String age){
        int id1, age1;
        try {
            
            UserStorage userStorage = UserStorage.getInstance();
            ArrayList<User> users = userStorage.getUsers();
            
            if (id.equals("")) {
                return new Response("Id must be not empty.", Status.BAD_REQUEST);
            }
            
            if (firstname.equals("")) {
                return new Response("Firstname must be not empty.", Status.BAD_REQUEST);
            }
            
            if (lastname.equals("")) {
                return new Response("Lastname must be not empty.", Status.BAD_REQUEST);
            }
            
            if (age.equals("")) {
                return new Response("Age must be not empty.", Status.BAD_REQUEST);
            }
            
            try {
                
                if (id.length() > 9) {
                    return new Response("The ID must have a maximum of 9 digits.", Status.BAD_REQUEST);
                }
                
                id1 = Integer.parseInt(id);
                age1 = Integer.parseInt(age);
            } catch (NumberFormatException e) {
                return new Response("The id and the age must be numeric", Status.BAD_REQUEST);
            }
            
            if(id1 < 0 || id1 > 999999999){
                return new Response("The ID must be a number between 0 and 999999999.", Status.BAD_REQUEST);
            }
            
            //Unique usersId
            for(User user : users){
                if(user.getId() == id1){
                    return new Response("A user with this ID is already registered.", Status.BAD_REQUEST);
                }
            }
            
            if(age1 < 18){
                return new Response("The minimun age is 18.", Status.BAD_REQUEST);
            }

            userStorage.addItem(new User(id1, firstname, lastname, age1));
            return new Response("User added successfully.", Status.OK);
            
        } catch (NumberFormatException e) {
            return new Response("Must be numeric", Status.INTERNAL_SERVER_ERROR);
        }
    }
}
