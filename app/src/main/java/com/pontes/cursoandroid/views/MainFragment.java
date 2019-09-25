package com.pontes.cursoandroid.views;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pontes.cursoandroid.R;
import com.pontes.cursoandroid.controllers.EarningController;
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
        String totalExpensesTXT = String.format("%.2f", totalExpenses);
        expensesTXT.setText(getString(R.string.coin )+ " " + totalExpensesTXT);

        TextView earningTXT = view.findViewById(R.id.earningTXT);
        float totalEarning = EarningController.getMonthTotal();
        String totalEarningTXT = String.format("%.2f", totalEarning);
        earningTXT.setText(getString(R.string.coin )+ " " + totalEarningTXT);

        TextView economyTXT = view.findViewById(R.id.economyTXT);
        float totalEconomy = totalEarning - totalExpenses;
        String totalEconomyTXT = String.format("%.2f", totalEconomy);
        economyTXT.setText(getString(R.string.coin )+ " " +totalEconomyTXT);

        if (totalEconomy > 0){
            economyTXT.setTextColor(getResources().getColor(R.color.economyPositive));
        }else if(totalEconomy < 0){
            economyTXT.setTextColor(getResources().getColor(R.color.economyNegative));
        }

        return view;
    }

}
