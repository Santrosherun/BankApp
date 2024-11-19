/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models;

import java.util.ArrayList;

/**
 *
 * @author Allison Ruiz
 */
public class Storage {
    
    private static Storage instance;
    private ArrayList<Account> accounts;
    private ArrayList<Transaction> transactions;
    private ArrayList<User> users;
    
    private Storage() {
        this.accounts = new ArrayList<>();
        this.transactions = new ArrayList<>();
        this.users = new ArrayList<>();
    }
    
    public void addUser(User user){
        if(!this.users.contains(user)){
            this.users.add(user);
        }
    }
    
    public void addTransaction(Transaction transaction){
        if(!this.transactions.contains(transaction)){
            this.transactions.add(transaction);
        }
    }
    
    public void addAccount(Account account){
        if(!this.accounts.contains(account)){
            this.accounts.add(account);
        }
    }
    
    public static Storage getInstance() {
        if (instance == null) {
            instance = new Storage();
        }
        return instance;
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public ArrayList<User> getUsers() {
        return users;
    }
    
    
}
