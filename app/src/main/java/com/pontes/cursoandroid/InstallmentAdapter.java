package com.pontes.cursoandroid;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pontes.cursoandroid.controllers.InstallmentController;
import com.pontes.cursoandroid.models.Installment;

import java.util.List;

public class InstallmentAdapter extends RecyclerView.Adapter<InstallmentHolder> {

    private List<Installment> installmentList;

    public InstallmentAdapter(List<Installment> list) {
        this.installmentList = list;
    }

    @NonNull
    @Override
    public InstallmentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.installment_list, parent, false);

        return new InstallmentHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InstallmentHolder holder, int position) {

        Installment element = installmentList.get(position);
        holder.id = element.getCodigo();
        holder.description.setText(element.getDescription());
        holder.value.setText("R$ " + String.format("%.2f", element.getValue()));
        holder.current.setText(String.valueOf(element.getCurrentPart()));
        holder.max.setText(String.valueOf(element.getTotalPart()));
        holder.progressValue = element.getCurrentPart();
        holder.progressBar.setMax(element.getCurrentPart());
        holder.progressBar.setProgress(element.getCurrentPart());

        holder.payBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.progressValue++;
                holder.progressBar.setProgress(holder.progressValue);
                holder.current.setText(String.valueOf(holder.progressValue));
                if (InstallmentController.incrementInstallment(holder.id)){
                    int id = holder.getAdapterPosition();
                    installmentList.remove(id);
                    notifyItemRemoved(id);
                    notifyItemRangeChanged(id, installmentList.size());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return installmentList.size();
    }
}
