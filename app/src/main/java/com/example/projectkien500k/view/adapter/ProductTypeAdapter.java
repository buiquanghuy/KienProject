package com.example.projectkien500k.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectkien500k.databinding.ItemProductBinding;
import com.example.projectkien500k.databinding.ItemProductTypeBinding;
import com.example.projectkien500k.model.data.Product;
import com.example.projectkien500k.model.data.TypeProduct;
import com.example.projectkien500k.model.viewmodel.ViewProduct;
import com.example.projectkien500k.utils.RoundedTransformation;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ProductTypeAdapter extends RecyclerView.Adapter<ProductTypeAdapter.ViewHolder> {
    Context context;
    List<TypeProduct> list;
    OnClickItemProductType onClickItemProductType;

    public ProductTypeAdapter(Context context, List<TypeProduct> list, OnClickItemProductType onClickItemProductType) {
        this.context = context;
        this.list = list;
        this.onClickItemProductType = onClickItemProductType;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =
                LayoutInflater.from(parent.getContext());
        ;
     //   return new ViewHolder((ItemProductBinding) DataBindingUtil.inflate(layoutInflater, R.layout.item_product, parent, false));
        return new ViewHolder(ItemProductTypeBinding.inflate(layoutInflater,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Picasso.get()
                .load(list.get(position).getImage())
                .fit()
                .transform(new RoundedTransformation(16, 0))
                .centerCrop()
                .into( holder.binding.bookImage);
        holder.binding.setProductType(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ItemProductTypeBinding binding;

        public ViewHolder(@NonNull ItemProductTypeBinding itemProductTypeBinding) {
            super(itemProductTypeBinding.getRoot());
            itemView.setOnClickListener(this);
            binding=itemProductTypeBinding;
        }

        @Override
        public void onClick(View v) {
            onClickItemProductType.onCLick(list.get(getAdapterPosition()));
        }
    }
    public interface OnClickItemProductType
    {
        void onCLick(TypeProduct typeProduct);
    }
}
