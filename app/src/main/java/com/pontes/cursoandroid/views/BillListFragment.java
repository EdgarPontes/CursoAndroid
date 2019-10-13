package com.pontes.cursoandroid.views;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.pontes.cursoandroid.R;

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
