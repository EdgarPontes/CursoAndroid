package com.pontes.cursoandroid.views;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pontes.cursoandroid.R;
import com.pontes.cursoandroid.controllers.ExpenseController;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {


    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        TextView expensesTXT = view.findViewById(R.id.expenseTXT);
        float totalExpenses = ExpenseController.getMonthTotal();
        String totalExpensesTXT = Float.toString(totalExpenses);
        expensesTXT.setText(getString(R.string.coin )+ " " + totalExpensesTXT);

        return view;
    }

}
