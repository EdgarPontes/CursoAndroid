package com.pontes.cursoandroid.controllers;

import com.pontes.cursoandroid.helpers.ConvertDate;
import com.pontes.cursoandroid.models.Bill;

import java.util.Calendar;

public class BillController {

    public static void createNew(String description, double value, String date){
        Calendar cDate = ConvertDate.stringToDate(date);

        Bill bill = new Bill(description, cDate.get(Calendar.DAY_OF_MONTH),
                cDate.get(Calendar.MONTH+1),
                cDate.get(Calendar.YEAR),
                value, false);

        bill.save();
    }


}
