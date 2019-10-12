package com.pontes.cursoandroid.controllers;

import com.pontes.cursoandroid.models.Bank;

import io.realm.Realm;

public class BankController {

    public static void create(String owner, double balange, String name, String agency){
        Bank newBAnk = new Bank(owner, balange, name, agency);
        newBAnk.save();
    }

    public static Bank get(){
        Bank info;

        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        Bank tmpBank = realm.where(Bank.class).findFirst();

        if (tmpBank != null){
            info = new Bank(tmpBank.getOwner(), tmpBank.getBalance(), tmpBank.getName(), tmpBank.getAgency());
        }else{
            info = null;
        }
        realm.commitTransaction();
        realm.close();

        return info;
    }

    public static double getBAlance(){
        Bank bank = get();

        if (bank != null){
            return bank.getBalance();
        }

        return 0.0 ;
    }

    public static void deposit(double value){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        Bank bank = realm.where(Bank.class).findFirst();
        bank.setBalance(bank.getBalance()+value);

        realm.commitTransaction();
        realm.close();
    }

    public static void withdraw(double value){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        Bank bank = realm.where(Bank.class).findFirst();
        bank.setBalance(bank.getBalance()-value);

        realm.commitTransaction();
        realm.close();
    }
}
