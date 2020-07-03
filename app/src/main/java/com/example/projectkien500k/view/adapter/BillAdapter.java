package com.example.projectkien500k.view.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectkien500k.application.onEventAdapter;
import com.example.projectkien500k.application.onEventBillAdapter;
import com.example.projectkien500k.databinding.ItemBillBinding;
import com.example.projectkien500k.databinding.ItemCartBinding;
import com.example.projectkien500k.model.data.Bill;
import com.example.projectkien500k.model.data.DetailBill;
import com.example.projectkien500k.utils.RoundedTransformation;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class BillAdapter extends RecyclerView.Adapter<BillAdapter.ViewHolder> {
    List<Bill> list;
    Context context;
    onEventBillAdapter monEvent;

    public BillAdapter(List<Bill> list, Context context, onEventBillAdapter onEvent) {
        this.list = list;
        this.context = context;
        this.monEvent = onEvent;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =
                LayoutInflater.from(parent.getContext());
        return new ViewHolder(ItemBillBinding.inflate(layoutInflater, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        // holder.itemCartBinding.imageItemCart.setText(""+list.get(position).getName());
        holder.itemBillBinding.setBill(list.get(position));
        if(position % 2 == 0){
            holder.itemBillBinding.itembill.setBackgroundColor(Color.parseColor("#e4e6e8"));
        }else{
            holder.itemBillBinding.itembill.setBackgroundColor(Color.parseColor("#e1ecf4"));
        }
        holder.itemBillBinding.itembill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                monEvent.onClickItem(list.get(position),position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemBillBinding itemBillBinding;

        public ViewHolder(@NonNull ItemBillBinding itemBillBinding) {
            super(itemBillBinding.getRoot());
            this.itemBillBinding = itemBillBinding;
        }
    }

}
