package com.example.projectkien500k.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectkien500k.application.onEventAdapter;
import com.example.projectkien500k.databinding.ItemCartBinding;
import com.example.projectkien500k.databinding.ItemDetailbillBinding;
import com.example.projectkien500k.model.data.DetailBill;
import com.example.projectkien500k.utils.RoundedTransformation;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class BillDetailAdapter extends RecyclerView.Adapter<BillDetailAdapter.ViewHolder> {
    List<DetailBill> list;
    Context context;


    public BillDetailAdapter(List<DetailBill> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =
                LayoutInflater.from(parent.getContext());
        return  new ViewHolder(ItemDetailbillBinding.inflate(layoutInflater,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.itemDetailbillBinding.nameItemCart.setText(""+list.get(position).getName());
        holder.itemDetailbillBinding.priceItemCart.setText(format(list.get(position).getPrice())+"VND");
        holder.itemDetailbillBinding.txtquatity.setText("Số lượng:"+list.get(position).getQuantity());
        holder.itemDetailbillBinding.txtdes.setText(""+list.get(position).getDescribe());
       // holder.itemCartBinding.imageItemCart.setText(""+list.get(position).getName());
        Picasso.get()
                .load(list.get(position).getImage())
                .fit()
                .transform(new RoundedTransformation(16, 0))
                .centerCrop()
                .into( holder.itemDetailbillBinding.imageItemCart);

//        holder.itemCartBinding.buttonPlus.setVisibility(View.VISIBLE);
//        holder.itemCartBinding.buttonMinus.setVisibility(View.VISIBLE);
//        holder.itemCartBinding.buttonRemove.setVisibility(View.VISIBLE);
    }

    @Override
    public int getItemCount() {
        return list!=null ? list.size():0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemDetailbillBinding itemDetailbillBinding;
        public ViewHolder(@NonNull ItemDetailbillBinding itemCartBinding) {
            super(itemCartBinding.getRoot());
            this.itemDetailbillBinding=itemCartBinding;
        }
    }
    public String format(double number) {
        NumberFormat formatter = new DecimalFormat("#,###,###");
        return formatter.format(number);
    }
}
