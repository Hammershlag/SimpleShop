package com.example.testapk.product;

import android.media.Image;
import android.os.CpuUsageInfo;

public class ProductDTO {

    //TODO immage, name, desription, price, rating, number of products

    private byte[] image;
    private String name, desription;
    private double price, rating;
    private int number_of_products, id;

    public ProductDTO(){}
    public ProductDTO(int id, byte[] image, String name, String desription, double price, double rating, int number_of_products){

        this.id = id;
        this.image = image;
        this.name = name;
        this.desription = desription;
        this.price = price;
        this.rating = rating;
        this.number_of_products = number_of_products;
    }

    public ProductDTO(byte[] image, String name, String desription, double price, double rating, int number_of_products){

        this.image = image;
        this.name = name;
        this.desription = desription;
        this.price = price;
        this.rating = rating;
        this.number_of_products = number_of_products;
    }

    public ProductDTO(String name, String desription, double price, double rating, int number_of_products){

        this.name = name;
        this.desription = desription;
        this.price = price;
        this.rating = rating;
        this.number_of_products = number_of_products;
    }

    public double getPrice() {
        return price;
    }

    public double getRating() {
        return rating;
    }

    public byte[] getImage() {
        return image;
    }

    public int getId() {
        return id;
    }

    public int getNumber_of_products() {
        return number_of_products;
    }

    public String getDesription() {
        return desription;
    }

    public String getName() {
        return name;
    }

    public void setDesription(String desription) {
        this.desription = desription;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber_of_products(int number_of_products) {
        this.number_of_products = number_of_products;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}

