package com.pontes.cursoandroid.controllers;

import com.pontes.cursoandroid.models.CreditCard;

import io.realm.Realm;

public class CreditCardController {


    public static void create(String flag, String owner, double limit, double currentLimite) {

        CreditCard creditCard = new CreditCard(flag, owner, limit, currentLimite);
        creditCard.save();

    }

    public static CreditCard get(){
        CreditCard cc;

        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        CreditCard ccTMP = realm.where(CreditCard.class).findFirst();

        if (ccTMP != null){
            cc = new CreditCard(ccTMP.getFlag(), ccTMP.getOwner(), ccTMP.getLimit(), ccTMP.getCurrentLimit());
        }else {
            cc = null;
        }

        realm.commitTransaction();
        realm.close();

        return cc;
    }

    public static void changeLimit(double value){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        CreditCard cc = realm.where(CreditCard.class).findFirst();
        cc.setCurrentLimit(cc.getCurrentLimit() - value);

        realm.commitTransaction();
        realm.close();
    }

    public static void resetLimit(){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        CreditCard cc = realm.where(CreditCard.class).findFirst();
        cc.setCurrentLimit(cc.getLimit());

        realm.commitTransaction();
        realm.close();
    }
}
