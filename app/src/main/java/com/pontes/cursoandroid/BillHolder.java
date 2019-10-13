package com.pontes.cursoandroid;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BillHolder extends RecyclerView.ViewHolder {

    public String billID;
    public TextView description, value, date;
    public Button payBTN;

    public BillHolder(@NonNull View itemView) {
        super(itemView);

        description  = itemView.findViewById(R.id.billItemDescription);
        value        = itemView.findViewById(R.id.billItemValue);
        date         = itemView.findViewById(R.id.billItemDate);
        payBTN       = itemView.findViewById(R.id.billItemPay);
    }
}
