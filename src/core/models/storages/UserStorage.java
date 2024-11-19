/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storages;

import core.models.User;
import java.util.ArrayList;

/**
 *
 * @author sddva
 */
public class UserStorage extends Storage {
    private static UserStorage instance;
    private ArrayList<User> users;
    
    private UserStorage() {
        this.users = new ArrayList<>();
    }
    
    public static UserStorage getInstance() {
        if (instance == null) {
            instance = new UserStorage();
        }
        return instance;
    }
    
    public void addUser(User user){
        if(!this.users.contains(user)){
            this.users.add(user);
        }
    }
    
    public ArrayList<User> getUsers() {
        return users;
    }
    
}
