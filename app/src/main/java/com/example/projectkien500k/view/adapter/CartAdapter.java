package com.example.projectkien500k.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectkien500k.databinding.ItemCartBinding;
import com.example.projectkien500k.databinding.ItemProductBinding;
import com.example.projectkien500k.model.data.Product;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    List<Product> list;
    Context context;
    onEventCartAdapter onEventCartAdapter;

    public CartAdapter(List<Product> list, Context context, CartAdapter.onEventCartAdapter onEventCartAdapter) {
        this.list = list;
        this.context = context;
        this.onEventCartAdapter = onEventCartAdapter;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =
                LayoutInflater.from(parent.getContext());
        return  new ViewHolder(ItemCartBinding.inflate(layoutInflater,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {



    }
    public void OnClickEvent(ItemCartBinding itemCartBinding)
    {

    }
    public int getTotalPrice()
    {
       int total=0;
       return total;
    }

    @Override
    public int getItemCount() {
        return list!=null ? list.size():0;
    }
    public interface onEventCartAdapter
    {
        public void onChangeQualityProduct();
        public void onRemoveProduct();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemCartBinding itemCartBinding;
        public ViewHolder(@NonNull ItemCartBinding itemCartBinding) {
            super(itemCartBinding.getRoot());
            this.itemCartBinding=itemCartBinding;
        }
    }
}
