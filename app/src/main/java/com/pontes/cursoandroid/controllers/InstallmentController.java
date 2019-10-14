package com.pontes.cursoandroid.controllers;

import com.pontes.cursoandroid.models.Installment;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class InstallmentController {

    public static void createNew(String description, double value, int amount){

        Installment inst = new Installment(description, value, amount, 0, false);
        inst.save();
    }

    public static List<Installment> getAll(){
        List<Installment> installmentList = new ArrayList<Installment>();

        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        RealmResults<Installment> realmResults = realm.where(Installment.class).equalTo("finished", false).findAll();

        for (int i =0; i < realmResults.size(); i++){
            Installment tmpInsttallment = new Installment(
                    realmResults.get(i).getCodigo(),
                    realmResults.get(i).getDescription(),
                    realmResults.get(i).getValue(),
                    realmResults.get(i).getTotalPart(),
                    realmResults.get(i).getCurrentPart(),
                    realmResults.get(i).isFinished()
            );
            installmentList.add(tmpInsttallment);
        }

        realm.commitTransaction();
        realm.close();

        return installmentList;
    }

    public static boolean incrementInstallment(String id){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        Installment iTemp = realm.where(Installment.class).equalTo("codigo", id).findFirst();
        int actual = iTemp.getCurrentPart();

        if (actual+1 >= iTemp.getTotalPart()){
            iTemp.setCurrentPart(iTemp.getCurrentPart()+1);
            iTemp.setFinished(true);
            realm.commitTransaction();
            realm.close();
            return true;
        }else {
            iTemp.setCurrentPart(iTemp.getCurrentPart()+1);
            realm.commitTransaction();
            realm.close();
            return false;
        }
    }
}