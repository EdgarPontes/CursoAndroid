package com.pontes.cursoandroid.views;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pontes.cursoandroid.R;
import com.pontes.cursoandroid.controllers.CreditCardController;
import com.pontes.cursoandroid.models.CreditCard;

/**
 * A simple {@link Fragment} subclass.
 */
public class LimitCreditCardFragment extends Fragment {


    public LimitCreditCardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_limit_credit_card, container, false);

        TextView currentlimitTXT = view.findViewById(R.id.currentlimitTXT);

        CreditCard cc = CreditCardController.get();

        if (cc != null){
            currentlimitTXT.setText(String.format("%.2f", cc.getCurrentLimit()));
        }else{
            currentlimitTXT.setText(String.format("%.2f", 0.0));
        }
        
        return view;
    }

}
