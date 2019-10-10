package com.pontes.cursoandroid.views;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.pontes.cursoandroid.R;
import com.pontes.cursoandroid.controllers.BankController;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * A simple {@link Fragment} subclass.
 */
public class BankFormFragment extends Fragment {

    private EditText bankTXT, ownerTXT, agencyTXT, balanceTXT;

    @BindView(R.id.saveBankBTN)
    Button saveBankBTN;
    @BindView(R.id.cancelBankBTN)
    Button cancelBankBTN;

    public BankFormFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bank_form, container, false);

        bankTXT    = view.findViewById(R.id.bankTXT);
        ownerTXT   = view.findViewById(R.id.ownerTXT);
        agencyTXT  = view.findViewById(R.id.agencyTXT);
        balanceTXT = view.findViewById(R.id.balanceTXT);

        ButterKnife.bind(this, view);

        return view;
    }

    @OnClick({R.id.saveBankBTN})
    public void saveBankBTN(View view){

        if(!(bankTXT.getText().toString().isEmpty() && ownerTXT.getText().toString().isEmpty()
        && agencyTXT.getText().toString().isEmpty() && balanceTXT.getText().toString().isEmpty())){
            saveBankAcount();
        }else{
            Snackbar.make(view, "Nenhum dos campos pode ser vazio...", Snackbar.LENGTH_LONG).setAction("OK",
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    }).show();
        }
    }

    @OnClick(R.id.cancelBankBTN)
    public void cancelBankBTN(View view){
        cancel();
    }

    public void saveBankAcount(){
        String bank = bankTXT.getText().toString();
        String owner = ownerTXT.getText().toString();
        String agecy = agencyTXT.getText().toString();
        double balance = Double.parseDouble(balanceTXT.getText().toString());
        BankController.create(owner,balance,bank,agecy);
        Toast.makeText(getContext().getApplicationContext(), "Banco criado", Toast.LENGTH_LONG).show();

        getActivity().getSupportFragmentManager().
                beginTransaction().
                replace(R.id.fragmentContainer, new BankInfoFragment()).
                commit();
    }

    public void cancel(){
        getActivity().getSupportFragmentManager().
                beginTransaction().
                replace(R.id.fragmentContainer, new NoBankFragment()).
                commit();
    }

}
