package com.pontes.cursoandroid;

import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class InstallmentHolder extends RecyclerView.ViewHolder {

    public TextView description, value, current, max;
    public ProgressBar progressBar;
    public Button payBTN;
    public String id;
    public int progressValue;

    public InstallmentHolder(@NonNull View itemView) {
        super(itemView);

        description = itemView.findViewById(R.id.installmentDescriptionParc);
        value       = itemView.findViewById(R.id.installmentValueParc);
        progressBar = itemView.findViewById(R.id.progress);
        current     = itemView.findViewById(R.id.courrentAmauntTXT);
        max         = itemView.findViewById(R.id.maxAmountTXT);
        payBTN      = itemView.findViewById(R.id.installmentPayBTN);


    }
}
