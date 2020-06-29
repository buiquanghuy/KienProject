package com.example.projectkien500k.model.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TypeProduct {

    @SerializedName("id_type")
    @Expose
    private Integer idType;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("image")
    @Expose
    private String image;

    public Integer getIdType() {
        return idType;
    }

    public void setIdType(Integer idType) {
        this.idType = idType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
