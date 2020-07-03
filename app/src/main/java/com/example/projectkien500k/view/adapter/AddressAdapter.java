package com.example.projectkien500k.view.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectkien500k.databinding.ItemAddressBinding;
import com.example.projectkien500k.model.data.Address;
import com.example.projectkien500k.model.data.Product;
import com.example.projectkien500k.model.viewmodel.ViewProduct;
import com.example.projectkien500k.utils.RoundedTransformation;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.ViewHolder> {
    Context context;
    List<Address> list;
    OnClickItemAddress onClickItemAddress;

    public AddressAdapter(Context context, List<Address> list, OnClickItemAddress onClickItemAddress) {
        this.context = context;
        this.list = list;
        this.onClickItemAddress = onClickItemAddress;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =
                LayoutInflater.from(parent.getContext());
        return new ViewHolder(ItemAddressBinding.inflate(layoutInflater,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.binding.setAddress(list.get(position));
        if(position % 2 == 0){
            holder.binding.itemAddress.setBackgroundColor(Color.parseColor("#e4e6e8"));
        }else{
            holder.binding.itemAddress.setBackgroundColor(Color.parseColor("#e1ecf4"));
        }
        holder.binding.itemAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickItemAddress.onCLick(list.get(position),position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemAddressBinding binding;

        public ViewHolder(@NonNull ItemAddressBinding itemAddressBinding) {
            super(itemAddressBinding.getRoot());
            binding=itemAddressBinding;
        }
    }
    public interface OnClickItemAddress
    {
         void onCLick(Address address,int position);
    }
}
