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
import com.pontes.cursoandroid.models.Bank;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class BankInfoFragment extends Fragment {

    @BindView(R.id.depositBtn)
    Button depositBTN;
    @BindView(R.id.withdrawBtn)
    Button withdrawBTN;

    public BankInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bank_info, container, false);

        ButterKnife.bind(this, view);

        TextView bankinfo   = view.findViewById(R.id.bankinfo);
        TextView ownerinfo  = view.findViewById(R.id.ownerinfo);
        TextView agencyinfo = view.findViewById(R.id.agencyinfo);

        Bank bankTMP = BankController.get();

        bankinfo.setText(bankTMP.getName());
        ownerinfo.setText(bankTMP.getOwner());
        agencyinfo.setText(bankTMP.getAgency());

        return view;
    }

    public void changeBalance(boolean isDeposit){
        Dialog myDialog = new Dialog(getActivity());
        myDialog.setContentView(R.layout.valuedialog);
        TextView titleDialog = myDialog.findViewById(R.id.titledialog);

        if (isDeposit){
            titleDialog.setText("Deposito");
        }else {
            titleDialog.setText("Saque");
        }

        final EditText valueTXT = myDialog.findViewById(R.id.valuedialog);
        Button confirmDialogBTN = myDialog.findViewById(R.id.confirmdialogBtn);

        confirmDialogBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double value = Double.parseDouble(valueTXT.getText().toString());

                if (isDeposit){
                    BankController.deposit(value);
                }else{
                    BankController.withdraw(value);
                }

                myDialog.cancel();
                getActivity().
                        getSupportFragmentManager().
                        beginTransaction().
                        replace(R.id.fragmentContainer, new BankInfoFragment()).
                        commit();
            }
        });

        myDialog.show();
    }

    @OnClick(R.id.depositBtn)
    public void depositBTN(){
        changeBalance(true);
    }
    @OnClick(R.id.withdrawBtn)
    public void withdrawBTN(){
        changeBalance(false);
    }

}
