/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package core.models.utils;

import core.models.Account;

/**
 *
 * @author Allison Ruiz
 */
public interface TwoAccountStrategy extends TransactionStrategy{
    public void execute(double amount, Account account1, Account account2);
}
