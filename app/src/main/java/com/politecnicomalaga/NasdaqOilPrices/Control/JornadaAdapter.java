package com.politecnicomalaga.NasdaqOilPrices.Control;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.politecnicomalaga.NasdaqOilPrices.Model.Price;
import com.politecnicomalaga.NasdaqOilPrices.R;

import java.util.List;
import java.util.Map;

public class JornadaAdapter extends RecyclerView.Adapter<JornadaViewHolder> {

    private List<Price> mList;
    private LayoutInflater mInflater;

    private Map<String, Drawable> imageBank;

    public JornadaAdapter(Context context,
                          List<Price> list, Map<String,Drawable> imageBank) {
        mInflater = LayoutInflater.from(context);
        this.mList = list;
        this.imageBank = imageBank;
    }


    @NonNull
    @Override
    public JornadaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.day_list_item,
                parent, false);
        return new JornadaViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull JornadaViewHolder holder, int position) {
        //TODO: fill data
       Price p = this.mList.get(position);

       holder.setDay(p.getDay());
       holder.setPrice(String.valueOf(p.getPrice()));
       holder.setTrend(imageBank.get(p.getTrend()));

    }



    @Override
    public int getItemCount() {
        return mList.size();
    }

}
