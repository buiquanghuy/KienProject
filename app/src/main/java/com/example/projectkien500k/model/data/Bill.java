package com.example.projectkien500k.model.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Bill {

    @SerializedName("id_bill")
    @Expose
    private Integer idBill;
    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("id_address")
    @Expose
    private Integer idAddress;
    @SerializedName("id_client")
    @Expose
    private Integer idClient;
    @SerializedName("status_bill")
    @Expose
    private String statusBill;

    public Integer getIdBill() {
        return idBill;
    }

    public void setIdBill(Integer idBill) {
        this.idBill = idBill;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getIdAddress() {
        return idAddress;
    }

    public void setIdAddress(Integer idAddress) {
        this.idAddress = idAddress;
    }

    public Integer getIdClient() {
        return idClient;
    }

    public void setIdClient(Integer idClient) {
        this.idClient = idClient;
    }

    public String getStatusBill() {
        return statusBill;
    }

    public void setStatusBill(String statusBill) {
        this.statusBill = statusBill;
    }

}
