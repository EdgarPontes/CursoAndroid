package com.pontes.cursoandroid.views;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.pontes.cursoandroid.BillsAdapter;
import com.pontes.cursoandroid.R;
import com.pontes.cursoandroid.controllers.BillController;
import com.pontes.cursoandroid.models.Bill;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class BillListFragment extends Fragment {

    @BindView(R.id.billLisNewBTN)
    Button billListNewBTN;

    public BillListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_bill_list, container, false);

        ButterKnife.bind(this, view);

        RecyclerView billRecycler = view.findViewById(R.id.billsList);
        billRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        BillsAdapter billsAdapter = new BillsAdapter(BillController.getAll());
        billRecycler.setAdapter(billsAdapter);

       return view;
    }

    @OnClick(R.id.billLisNewBTN)
    public void billListNewBTN(){
        getActivity().
                getSupportFragmentManager().
                beginTransaction().
                replace(R.id.fragmentContainer, new BillFormFragment()).
                commit();
    }
}
