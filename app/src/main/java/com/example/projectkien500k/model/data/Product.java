package com.example.projectkien500k.model.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import retrofit2.http.GET;
public class  Product {
    @SerializedName("id_product")
    @Expose
    private Integer idProduct;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("describe")
    @Expose
    private String describe;
    @SerializedName("price")
    @Expose
    private Integer price;
    @SerializedName("id_type")
    @Expose
    private Integer idType;
    @SerializedName("love")
    @Expose
    private Integer love;
    @SerializedName("quantity")
    @Expose
    private Integer quantity;

    @SerializedName("producer")
    @Expose
    private String producer;

    @SerializedName("size")
    @Expose
    private String size;

    @SerializedName("trade")
    @Expose
    private String trade;
    @SerializedName("quantitysale")
    @Expose
    private Integer quantitysale;
    @SerializedName("rate")
    @Expose
    private Integer rate;


    public Product(Integer idProduct, String name, String image, String describe, Integer price, Integer idType, Integer love, Integer quantity) {
        this.idProduct = idProduct;
        this.name = name;
        this.image = image;
        this.describe = describe;
        this.price = price;
        this.idType = idType;
        this.love = love;
        this.quantity = quantity;
    }

    public Product(String name, int price, int id, String description, String image) {
        this.idProduct = id;
        this.name = name;
        this.image = image;
        this.describe = description;
        this.price = price;
    }


    public Integer getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Integer idProduct) {
        this.idProduct = idProduct;
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

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getIdType() {
        return idType;
    }

    public void setIdType(Integer idType) {
        this.idType = idType;
    }

    public Integer getLove() {
        return love;
    }

    public void setLove(Integer love) {
        this.love = love;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getTrade() {
        return trade;
    }

    public void setTrade(String trade) {
        this.trade = trade;
    }

    public Integer getQuantitysale() {
        return quantitysale;
    }

    public void setQuantitysale(Integer quantitysale) {
        this.quantitysale = quantitysale;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }
}
