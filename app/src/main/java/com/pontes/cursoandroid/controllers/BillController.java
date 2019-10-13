package com.pontes.cursoandroid.controllers;

import com.pontes.cursoandroid.helpers.ConvertDate;
import com.pontes.cursoandroid.models.Bill;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class BillController {

    public static void createNew(String description, double value, String date){
        Calendar cDate = ConvertDate.stringToDate(date);

        Bill bill = new Bill(description, cDate.get(Calendar.DAY_OF_MONTH),
                cDate.get(Calendar.MONTH)+1,
                cDate.get(Calendar.YEAR),
                value, false);

        bill.save();
    }

    public static List<Bill> getAll(){
        List<Bill> billList = new ArrayList<Bill>();

        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        RealmResults<Bill> tmpList = realm.where(Bill.class).equalTo("payed", false).findAll();

        for (int i = 0; i < tmpList.size(); i++){
            Bill tmpBill = new Bill(
              tmpList.get(i).getCodigo(),
              tmpList.get(i).getDescription(),
              tmpList.get(i).getDay(),
              tmpList.get(i).getMonth(),
              tmpList.get(i).getYear(),
              tmpList.get(i).getValue(),
              tmpList.get(i).isPayed()
            );
            billList.add(tmpBill);
        }

        realm.commitTransaction();
        realm.close();

        return billList;

    }

    public static void setPayed(String codigo){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        Bill bill = realm.where(Bill.class).equalTo("codigo", codigo).findFirst();
        bill.setPayed(true);

        realm.commitTransaction();
        realm.close();
    }
}
