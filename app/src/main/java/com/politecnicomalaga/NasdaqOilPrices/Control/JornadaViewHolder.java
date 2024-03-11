package com.politecnicomalaga.NasdaqOilPrices.Control;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.politecnicomalaga.NasdaqOilPrices.R;

public class JornadaViewHolder extends RecyclerView.ViewHolder {

    //ESTADO

    //TODO: declare attributes to hold two textviews, day and money
    final JornadaAdapter mAdapter;
    private TextView tvPrice;
    private TextView tvDay;

    private ImageView ivTrend;


    //COMPORTAMIENTO
    public JornadaViewHolder(View itemView, JornadaAdapter adapter) {
        super(itemView);

        tvDay = itemView.findViewById(R.id.day);
        tvPrice = itemView.findViewById(R.id.price);
        ivTrend = itemView.findViewById(R.id.trend);
        this.mAdapter = adapter;
    }

    public void setDay(String data) {
        tvDay.setText(data);
    }

    public void setPrice(String data) {
        tvPrice.setText(data);
    }

    public void setTrend(Drawable d) {ivTrend.setImageDrawable(d);}
}

