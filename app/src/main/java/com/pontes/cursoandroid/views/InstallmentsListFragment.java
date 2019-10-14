package com.pontes.cursoandroid.views;


import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.pontes.cursoandroid.InstallmentAdapter;
import com.pontes.cursoandroid.R;
import com.pontes.cursoandroid.controllers.InstallmentController;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class InstallmentsListFragment extends Fragment {

    @BindView(R.id.installmentsListBTN)
    Button installmentsListBTN;

    public InstallmentsListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_installments_list, container, false);

        ButterKnife.bind(this, view);

        RecyclerView installmentRec = view.findViewById(R.id.installmentsList);
        installmentRec.setLayoutManager(new LinearLayoutManager(getContext()));
        InstallmentAdapter adapter = new InstallmentAdapter(InstallmentController.getAll());
        installmentRec.setAdapter(adapter);

        return view;
    }

    @OnClick(R.id.installmentsListBTN)
    public void installmentsListBTN(){
       createDialog();
    }

    public void createDialog(){
        final Dialog myDialog = new Dialog(getActivity());
        myDialog.setContentView(R.layout.installment_dialog);
        myDialog.show();

        final TextView descriptionField = myDialog.findViewById(R.id.installmentDescriptionDialog);
        final TextView valueField       = myDialog.findViewById(R.id.installmentValueDialog);
        final TextView amountField      = myDialog.findViewById(R.id.installmentAmountDialog);
        Button confirmInstallmentBTN    = myDialog.findViewById(R.id.installmentsConfirmBTN);

        confirmInstallmentBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String description = descriptionField.getText().toString();
                int amount         = Integer.parseInt(amountField.getText().toString());
                double value       = Double.parseDouble(valueField.getText().toString());

                InstallmentController.createNew(description, value, amount);
                Toast.makeText(getContext().getApplicationContext(), "Parcelamento criado com sucesso", Toast.LENGTH_LONG).show();
                getActivity().
                        getSupportFragmentManager().
                        beginTransaction().
                        replace(R.id.fragmentContainer, new InstallmentsListFragment()).
                        commit();

                myDialog.cancel();
            }
        });


    }

}
