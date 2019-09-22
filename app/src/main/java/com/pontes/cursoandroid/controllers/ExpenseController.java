package com.pontes.cursoandroid.controllers;

import android.provider.CalendarContract;

import com.pontes.cursoandroid.helpers.ConvertDate;
import com.pontes.cursoandroid.models.Expense;

import java.util.Calendar;

public class ExpenseController {

    public static void newExpense(float value, String description, String dateTEXT, String category, boolean consolidated){
        Calendar date = ConvertDate.stringToDate(dateTEXT);

        Expense expense = new Expense(description, category, value, date.get(Calendar.DAY_OF_MONTH),
                date.get(Calendar.MONTH) + 1, date.get(Calendar.YEAR), consolidated);
        expense.save();
    }
}
