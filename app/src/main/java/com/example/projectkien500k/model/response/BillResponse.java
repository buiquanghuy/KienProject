package com.example.projectkien500k.model.response;

import com.example.projectkien500k.model.data.Bill;
import com.example.projectkien500k.model.data.Client;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BillResponse extends BaseResponse{
    @SerializedName("data")
    @Expose
    private List<Bill> data;
    public BillResponse(String status, String mess, List<Bill> data) {
        super(status, mess);
        this.data=data;
    }

    public List<Bill> getData() {
        return data;
    }

    public void setData(List<Bill> data) {
        this.data = data;
    }
}

