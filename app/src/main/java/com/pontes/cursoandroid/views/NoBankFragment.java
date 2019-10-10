package com.pontes.cursoandroid.views;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.pontes.cursoandroid.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NoBankFragment extends Fragment {


    public NoBankFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_no_bank, container, false);

        Button newBankBTN = view.findViewById(R.id.newBankBTN);
        newBankBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().
                        getSupportFragmentManager().
                        beginTransaction().replace(R.id.fragmentContainer, new BankFormFragment()).
                        commit();
            }
        });
        return view;
    }

}
