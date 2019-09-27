package com.pontes.cursoandroid.views;


import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.pontes.cursoandroid.R;
import com.pontes.cursoandroid.controllers.ExpenseController;

import java.util.Calendar;

public class ExpenseFormFragment extends Fragment implements View.OnClickListener{

    private TextView valueField;
    private TextView descriptionField;
    private CheckBox consolidatedField;
    private Spinner categoriesList;
    private EditText dateField;
    private Button saveBtn;
    private DatePickerDialog.OnDateSetListener dateListener;
    private Button cancelBtn;

    public ExpenseFormFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_expense_form, container, false);

        valueField = view.findViewById(R.id.valueField);
        descriptionField = view.findViewById(R.id.descriptionField);
        consolidatedField = view.findViewById(R.id.consolidatedField);
        saveBtn = view.findViewById(R.id.saveBtn);
        cancelBtn = view.findViewById(R.id.cancelBtn);
        cancelBtn.setOnClickListener(this);

        categoriesList = view.findViewById(R.id.categoriesList);
        setCategorySpiner(view.getContext());

        dateField = view.findViewById(R.id.dateField);
        setDateField();
        saveBtn.setOnClickListener(this);

        return view;
    }

    private void setDateField() {
        Calendar cal = Calendar.getInstance();

        final int day = cal.get(Calendar.DAY_OF_MONTH);
        final int month = cal.get(Calendar.MONTH);
        final int year = cal.get(Calendar.YEAR);

        String dateString = Integer.toString(day) + " / " +
                Integer.toString(month + 1) + " / " +
                Integer.toString(year);
        dateField.setText(dateString);

        dateField.setKeyListener(null);

        dateField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(
                        getContext(),
                        android.R.style.Theme_Holo_Light_Dialog_NoActionBar_MinWidth,
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
    }

    private void setCategorySpiner(Context context) {
        ArrayAdapter<String> categoriesAdapter = new ArrayAdapter<String>(context,
                android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.expense_category)
        );

        categoriesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categoriesList.setAdapter(categoriesAdapter);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.saveBtn){
           if (!valueField.getText().toString().isEmpty() && !descriptionField.getText().toString().isEmpty()) {
               getFieldData();
               Toast.makeText(v.getContext(), "Despesa salva com sucesso", Toast.LENGTH_LONG).show();
               backToHome();
           }else{
               Snackbar.make(v, "Nenhum dos campos pode se vazio...", Snackbar.LENGTH_LONG).setAction("OK",
                       new View.OnClickListener() {
                           @Override
                           public void onClick(View v) {

                           }
                       }).show();
           }
        }else if (v.getId() == R.id.cancelBtn){
            backToHome();
        }
    }

    private void backToHome(){
       /* ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setShowHideAnimationEnabled(false);*/
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.app_name);
        getActivity().getSupportFragmentManager().
                beginTransaction().
                replace(R.id.fragmentContainer, new MainFragment()).
                commit();
    }

    private void getFieldData() {
        float value = Float.parseFloat(valueField.getText().toString());
        String description = descriptionField.getText().toString();
        String date = dateField.getText().toString();
        String category = categoriesList.getSelectedItem().toString();
        boolean consolidated = consolidatedField.isChecked();
        ExpenseController.newExpense(value, description, date, category, consolidated);
    }
}
