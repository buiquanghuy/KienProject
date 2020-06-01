package com.example.projectkien500k;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.projectkien500k.databinding.ActivityCartBinding;
import com.example.projectkien500k.model.data.Product;
import com.example.projectkien500k.utils.FakeDataUtil;
import com.example.projectkien500k.view.adapter.CartAdapter;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity implements CartAdapter.onEventCartAdapter {

    ActivityCartBinding binding;
    List<Product> list;
    CartAdapter cartAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        list=new ArrayList<>();
        list= FakeDataUtil.getListProduct();
        initCartAdapter();
        binding.toolbar.setTitleTextColor(getColor(R.color.white));
        binding.toolbar.setTitle("Giỏ hàng ("+list.size()+")");
    }
    public void initCartAdapter()
    {
        cartAdapter=new CartAdapter(list,this,this);
        binding.recyclerViewCart.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        binding.recyclerViewCart.setAdapter(cartAdapter);
    }


    @Override
    public void onChangeQualityProduct() {

    }

    @Override
    public void onRemoveProduct() {

    }
}