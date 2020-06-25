package com.example.projectkien500k.model.response;

import com.example.projectkien500k.model.data.Product;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductResponse  extends BaseResponse{
    @SerializedName("data")
    @Expose
    private List<Product> data;
    public ProductResponse(String status, String mess,List<Product> data) {
        super(status, mess);
        this.data=data;
    }

    public List<Product> getData() {
        return data;
    }

    public void setData(List<Product> data) {
        this.data = data;
    }
}

