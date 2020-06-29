package com.example.projectkien500k.model.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Comment {
    @SerializedName("id_coment")
    @Expose
    private Integer idComent;
    @SerializedName("id_product")
    @Expose
    private Integer idProduct;
    @SerializedName("id_client")
    @Expose
    private Integer idClient;
    @SerializedName("content")
    @Expose
    private String content;

    public Integer getIdComent() {
        return idComent;
    }

    public void setIdComent(Integer idComent) {
        this.idComent = idComent;
    }

    public Integer getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Integer idProduct) {
        this.idProduct = idProduct;
    }

    public Integer getIdClient() {
        return idClient;
    }

    public void setIdClient(Integer idClient) {
        this.idClient = idClient;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
