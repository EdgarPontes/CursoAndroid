package com.pontes.cursoandroid.views;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pontes.cursoandroid.R;
import com.pontes.cursoandroid.controllers.BankController;
import com.pontes.cursoandroid.models.Bank;


/**
 * A simple {@link Fragment} subclass.
 */
public class BalanceCurrentFragment extends Fragment {

    public BalanceCurrentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_balance_current, container, false);

        TextView balanceResultTXT = view.findViewById(R.id.balanceResultTXT);
        balanceResultTXT.setText(getString(R.string.coin) + " " +String.format("%.2f", BankController.getBAlance()));

        return view;
    }

}
