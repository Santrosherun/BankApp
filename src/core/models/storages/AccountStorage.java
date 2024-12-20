/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storages;

import core.models.Account;
import java.util.ArrayList;

/**
 *
 * @author sddva
 */
public class AccountStorage extends Storage {
    private static AccountStorage instance;
    private ArrayList<Account> accounts;
    
    private AccountStorage() {
        this.accounts = new ArrayList<>();
    }
    
    public static AccountStorage getInstance() {
        if (instance == null) {
            instance = new AccountStorage();
        }
        return instance;
    }
    
    @Override
    public void addItem(Object account){
        if(!this.accounts.contains((Account)account)){
            this.accounts.add((Account)account);
        }
    }
    
    public ArrayList<Account> getAccounts() {
        return accounts;
    }
    
}
