package com.example.projectkien500k;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.projectkien500k.databinding.ActivityProductBinding;

public class ProductActivity extends AppCompatActivity {

    ActivityProductBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.toolbar.setTitleTextColor(getColor(R.color.white));
        binding.toolbar.setTitle("Chi tiết sản phẩm");
    }
}