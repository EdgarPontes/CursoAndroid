package com.pontes.cursoandroid.views;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.pontes.cursoandroid.R;
import com.pontes.cursoandroid.controllers.CreditCardController;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreditCardFormFragment extends Fragment {

    @BindView(R.id.saveCreditCardBTN)
    Button saveCreditCardBTN;
    @BindView(R.id.cancelCreditCardBTN)
    Button cancelCreditCardBTN;

    TextView creditcardTXT, creditcardownerTXT, creditcardlimitTXT;

    public CreditCardFormFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_credit_card_form, container, false);

        creditcardTXT      = view.findViewById(R.id.creditcardTXT);
        creditcardownerTXT = view.findViewById(R.id.creditcardownerTXT);
        creditcardlimitTXT = view.findViewById(R.id.creditcardlimitTXT);

        ButterKnife.bind(this, view);


        return view;
    }

    @OnClick({R.id.saveCreditCardBTN})
    public void saveCreditCardBTN(){
        createCreditCard();
        showInfoCreditCard();
    }

    @OnClick(R.id.cancelCreditCardBTN)
    public void cancelCreditCardBTN(){
        cancelCreateCreditCard();
    }

    public void createCreditCard(){
        String flag  = creditcardTXT.getText().toString();
        String owner = creditcardownerTXT.getText().toString();
        double limit = Double.parseDouble(creditcardlimitTXT.getText().toString());

        CreditCardController.create(flag, owner, limit, limit);
    }

    public void showInfoCreditCard(){
        getActivity().
                getSupportFragmentManager().
                beginTransaction().
                replace(R.id.fragmentContainer, new CreditCardInfoFragment()).
                commit();
    }

    public void cancelCreateCreditCard(){
        getActivity().
                getSupportFragmentManager().
                beginTransaction().
                replace(R.id.fragmentContainer, new NoCreditCardFragment()).
                commit();
    }

}
