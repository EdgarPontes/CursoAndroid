package com.pontes.cursoandroid.views;


import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.pontes.cursoandroid.R;
import com.pontes.cursoandroid.controllers.BillController;
import com.pontes.cursoandroid.helpers.ConvertDate;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class BillFormFragment extends Fragment {

    @BindView(R.id.billNewBTN)
    Button billNewBTN;
    @BindView(R.id.billCancelBTN)
    Button billCancelBTN;

    TextView billdescriptionTXT, billvalueTXT, billdateTXT;
    private DatePickerDialog.OnDateSetListener dateListener;

    public BillFormFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bill_form, container, false);

        ButterKnife.bind(this, view);

        billdescriptionTXT = view.findViewById(R.id.billDescription);
        billvalueTXT       = view.findViewById(R.id.billvalueTXT);
        billdateTXT        = view.findViewById(R.id.billdateTXT);

        setDateField();

        return view;
    }

    public void getData(){
        String description = billdescriptionTXT.getText().toString();
        double value       = Double.parseDouble(billvalueTXT.getText().toString());
        String date        = billdateTXT.getText().toString();

        BillController.createNew(description, value, date);
        Toast.makeText(getActivity().getApplicationContext(), "Conta criada", Toast.LENGTH_LONG).show();
    }

    @OnClick(R.id.billNewBTN)
    public void billNewBTN(){
        getData();
        back();
    }

    @OnClick(R.id.billCancelBTN)
    public void billCancelBTN(){
        back();
    }

    private void back() {
        getActivity().
                getSupportFragmentManager().
                beginTransaction().
                replace(R.id.fragmentContainer, new BillListFragment()).
                commit();
    }

    private void setDateField() {
        Calendar cal = Calendar.getInstance();

        final int day = cal.get(Calendar.DAY_OF_MONTH);
        final int month = cal.get(Calendar.MONTH);
        final int year = cal.get(Calendar.YEAR);

        String dateString = Integer.toString(day) + " / " +
                Integer.toString(month+1) + " / " +
                Integer.toString(year);
        billdateTXT.setText(dateString);

        billdateTXT.setKeyListener(null);

        billdateTXT.setOnClickListener(new View.OnClickListener() {
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
                billdateTXT.setText(dateString);
            }
        };
    }
}
