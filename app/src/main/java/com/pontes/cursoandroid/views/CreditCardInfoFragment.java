package com.pontes.cursoandroid.views;


import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.pontes.cursoandroid.R;
import com.pontes.cursoandroid.controllers.BankController;
import com.pontes.cursoandroid.controllers.CreditCardController;
import com.pontes.cursoandroid.models.CreditCard;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreditCardInfoFragment extends Fragment {

    @BindView(R.id.changeLimitBTN)
    Button changeLimitBTN;
    @BindView(R.id.resetLimitBTN)
    Button resertLimitBTN;

    public CreditCardInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_credit_card_info, container, false);

        ButterKnife.bind(this, view);

        TextView flagInfoTXT  = view.findViewById(R.id.flagInfoTXT);
        TextView ownerInfoTXT = view.findViewById(R.id.ownerInfoTXT);

        CreditCard cc = CreditCardController.get();

        flagInfoTXT.setText(cc.getFlag());
        ownerInfoTXT.setText(cc.getOwner());

        return view;
    }

    public void manipulateLimit(){
        final Dialog myDialog = new Dialog(getActivity());
        myDialog.setContentView(R.layout.valuedialog);
        TextView titleDialog = myDialog.findViewById(R.id.titledialog);

        titleDialog.setText("Manipular limite");

        final EditText valueTXT = myDialog.findViewById(R.id.valuedialog);
        Button confirmDialogBTN = myDialog.findViewById(R.id.confirmdialogBtn);

        confirmDialogBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double value = Double.parseDouble(valueTXT.getText().toString());

                CreditCardController.changeLimit(value);

                myDialog.cancel();
                getActivity().
                        getSupportFragmentManager().
                        beginTransaction().
                        replace(R.id.fragmentContainer, new CreditCardInfoFragment()).
                        commit();
            }
        });

        myDialog.show();
    }

    @OnClick(R.id.changeLimitBTN)
    public void changeLimitBTN(){
        manipulateLimit();
    }

    @OnClick(R.id.resetLimitBTN)
    public void resetLimitBTN(){ CreditCardController.resetLimit();
        getActivity().
                getSupportFragmentManager().
                beginTransaction().
                replace(R.id.fragmentContainer, new CreditCardInfoFragment()).
                commit();
    }
}