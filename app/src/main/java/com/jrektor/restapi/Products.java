package com.jrektor.restapi;

import com.google.gson.annotations.SerializedName;

public class Products {

    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("brand")
    private String brand;
    @SerializedName("type")
    private String type;
    @SerializedName("price")
    private String price;
    @SerializedName("date")
    private String date;
    @SerializedName("image")
    private String image;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
