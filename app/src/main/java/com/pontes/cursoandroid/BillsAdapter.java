package com.pontes.cursoandroid;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pontes.cursoandroid.models.Bill;

import java.util.List;

public class BillsAdapter extends RecyclerView.Adapter<BillHolder> {

    private List<Bill> billList;

    public BillsAdapter(List<Bill> billList){
        this.billList = billList;
    }


    @NonNull
    @Override
    public BillHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bill_item, parent, false);

        return new BillHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BillHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return this.billList.size();
    }
}
