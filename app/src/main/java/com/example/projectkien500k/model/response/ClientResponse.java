package com.example.projectkien500k.model.response;

import com.example.projectkien500k.model.data.Client;
import com.example.projectkien500k.model.data.Product;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ClientResponse extends BaseResponse{
    @SerializedName("data")
    @Expose
    private Client data;
    public ClientResponse(String status, String mess, Client data) {
        super(status, mess);
        this.data=data;
    }

    public Client getData() {
        return data;
    }

    public void setData(Client data) {
        this.data = data;
    }
}

