package com.example.projectkien500k.model.response;

import com.example.projectkien500k.model.data.Bill;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BillObjectResponse extends BaseResponse{
    @SerializedName("data")
    @Expose
    private Bill data;
    public BillObjectResponse(String status, String mess, Bill data) {
        super(status, mess);
        this.data=data;
    }

    public Bill getData() {
        return data;
    }

    public void setData(Bill data) {
        this.data = data;
    }
}

