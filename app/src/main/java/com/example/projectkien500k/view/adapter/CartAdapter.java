package com.example.projectkien500k.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectkien500k.application.onEventAdapter;
import com.example.projectkien500k.databinding.ItemCartBinding;
import com.example.projectkien500k.model.data.DetailBill;
import com.example.projectkien500k.utils.RoundedTransformation;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    List<DetailBill> list;
    Context context;
    onEventAdapter monEvent;

    public CartAdapter(List<DetailBill> list, Context context, onEventAdapter onEvent) {
        this.list = list;
        this.context = context;
        this.monEvent = onEvent;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =
                LayoutInflater.from(parent.getContext());
        return  new ViewHolder(ItemCartBinding.inflate(layoutInflater,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.itemCartBinding.nameItemCart.setText(""+list.get(position).getName());
        holder.itemCartBinding.priceItemCart.setText(format(list.get(position).getPrice())+"VND");
        holder.itemCartBinding.qualityItemCart.setText(""+list.get(position).getQuantity());
       // holder.itemCartBinding.imageItemCart.setText(""+list.get(position).getName());
        Picasso.get()
                .load(list.get(position).getImage())
                .fit()
                .transform(new RoundedTransformation(16, 0))
                .centerCrop()
                .into( holder.itemCartBinding.imageItemCart);

        holder.itemCartBinding.buttonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                monEvent.onChangeQualityProduct(list.get(position),"ADD",position);
            }
        });
        holder.itemCartBinding.buttonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                monEvent.onChangeQualityProduct(list.get(position),"MINUS",position);
            }
        });
        holder.itemCartBinding.buttonRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                monEvent.onRemoveProduct(list.get(position),position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list!=null ? list.size():0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemCartBinding itemCartBinding;
        public ViewHolder(@NonNull ItemCartBinding itemCartBinding) {
            super(itemCartBinding.getRoot());
            this.itemCartBinding=itemCartBinding;
        }
    }
    public String format(double number) {
        NumberFormat formatter = new DecimalFormat("#,###,###");
        return formatter.format(number);
    }
}
