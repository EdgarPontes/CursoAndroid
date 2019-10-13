package com.pontes.cursoandroid.controllers;

import com.pontes.cursoandroid.models.Bill;

public class BillController {

    public static void save(String description, int day, int month, int year,double value, boolean payed){
        Bill bill = new Bill(description, day, month, year, value, payed);
        bill.save();
    }


}
