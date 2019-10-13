package com.pontes.cursoandroid;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pontes.cursoandroid.controllers.BillController;
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
        Bill bill = billList.get(position);
        holder.description.setText(bill.getDescription());
        holder.value.setText("R$ " + String.format("%.2f", bill.getValue()));
        holder.date.setText(bill.getDay() + "/" + bill.getMonth() + "/" + bill.getYear());
        holder.billID = bill.getCodigo();

        holder.payBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BillController.setPayed(holder.billID);
                int id = holder.getAdapterPosition();
                billList.remove(id);
                notifyItemRemoved(id);
                notifyItemRangeChanged(id, billList.size());
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.billList.size();
    }
}
