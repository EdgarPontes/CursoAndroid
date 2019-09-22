package com.pontes.cursoandroid.helpers;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ConvertDate {

    public static Calendar stringToDate(String stringDate){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();

        try {
            cal.setTime(sdf.parse(stringDate));
        } catch (ParseException e) {
            Log.d("CursoAndroid", e.getMessage());
        }

        return cal;
    }
}
