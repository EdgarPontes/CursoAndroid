package com.pontes.cursoandroid.views;


import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.pontes.cursoandroid.R;

import java.util.Calendar;

public class ExpenseFormFragment extends Fragment {

    private EditText dateField;
    private DatePickerDialog.OnDateSetListener dateListener;

    public ExpenseFormFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_expense_form, container, false);

        Spinner categoriesList = view.findViewById(R.id.categoriesList);

        ArrayAdapter<String> categoriesAdapter = new ArrayAdapter<String>(view.getContext(),
                android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.expense_category)
        );

        categoriesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categoriesList.setAdapter(categoriesAdapter);

        Calendar cal = Calendar.getInstance();

        final int day = cal.get(Calendar.DAY_OF_MONTH);
        final int month = cal.get(Calendar.MONTH);
        final int year = cal.get(Calendar.YEAR);

        String dateString = Integer.toString(day) + " / " +
                            Integer.toString(month + 1) + " / " +
                            Integer.toString(year);

        dateField = view.findViewById(R.id.dateField);

        dateField.setText(dateString);

        dateField.setKeyListener(null);

        dateField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(
                        getContext(),
                        android.R.style.Theme_DeviceDefault_Dialog_MinWidth,
                        dateListener,
                        year,
                        month,
                        day
                );
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        dateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                String dateString = Integer.toString(day) + " / " +
                        Integer.toString(month + 1) + " / " +
                        Integer.toString(year);
                dateField.setText(dateString);
            }
        };

        return view;
    }

}
