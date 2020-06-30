package com.example.projectkien500k.application;

import com.example.projectkien500k.model.data.DetailBill;
import com.example.projectkien500k.model.data.Product;

public interface onEventAdapter {
    public void onChangeQualityProduct(DetailBill detail , String type,int position);
    public void onRemoveProduct(DetailBill detail,int position);
}
