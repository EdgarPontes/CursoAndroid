package com.pontes.cursoandroid.controllers;

import android.provider.CalendarContract;

import com.pontes.cursoandroid.helpers.ConvertDate;
import com.pontes.cursoandroid.models.Expense;

import java.util.Calendar;

import io.realm.Realm;
import io.realm.RealmResults;

public class ExpenseController {

    public static void newExpense(float value, String description, String dateTEXT, String category, boolean consolidated){
        Calendar date = ConvertDate.stringToDate(dateTEXT);

        Expense expense = new Expense(description, category, value, date.get(Calendar.DAY_OF_MONTH),
                date.get(Calendar.MONTH) + 1, date.get(Calendar.YEAR), consolidated);
        expense.save();
    }

    public static float getMonthTotal(){
        Calendar date = Calendar.getInstance();
        int month = date.get(Calendar.MONTH)  + 1;
        int year = date.get(Calendar.YEAR);
        float total = 0.0f;

        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        RealmResults<Expense> expenses = realm.where(Expense.class).
                equalTo("month", month).
                equalTo("year", year).
                equalTo("consolidated", true).findAll();

        for (int i = 0; i < expenses.size(); i++) {
            total += expenses.get(i).getValue();
        }

        realm.commitTransaction();
        realm.close();

        return total;
    }
}
