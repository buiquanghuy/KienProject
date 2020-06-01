package com.example.projectkien500k.model.viewmodel;

import com.example.projectkien500k.model.data.Product;

import lombok.Getter;
import lombok.Setter;

public class ViewProduct extends Product {

    @Setter @Getter
    int typeView;
    public ViewProduct(String name, int price, int id, String description,String image) {
        super(name, price, id, description,image);
    }
}
