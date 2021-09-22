package com.example.testapk.product;

public class ProductDTO {

    private byte[] image;
    private String name, desription, author, status;
    private double price, rating;
    private int number_of_products, id;
    private long time_created, time_checked;

    public ProductDTO(){}
    public ProductDTO(int id, byte[] image, String name, String desription, double price, double rating, int number_of_products, String author, String status, long time_created, long time_checked){

        this.id = id;
        this.image = image;
        this.name = name;
        this.desription = desription;
        this.price = price;
        this.rating = rating;
        this.number_of_products = number_of_products;
        this.author = author;
        this.status = status;
        this.time_checked = time_checked;
        this.time_created = time_created;
    }

    public ProductDTO(int id, String name, String desription, double price, double rating, int number_of_products, String author, String status, long time_created, long time_checked){

        this.id = id;
        this.name = name;
        this.desription = desription;
        this.price = price;
        this.rating = rating;
        this.number_of_products = number_of_products;
        this.author = author;
        this.status = status;
        this.time_checked = time_checked;
        this.time_created = time_created;
    }

    public ProductDTO(byte[] image, String name, String desription, double price, double rating, int number_of_products, String author){

        this.image = image;
        this.name = name;
        this.desription = desription;
        this.price = price;
        this.rating = rating;
        this.number_of_products = number_of_products;
        this.author = author;
        status = "WAITING";
        time_created = System.currentTimeMillis();
        time_checked = -1L;
    }

    public ProductDTO(String name, String desription, double price, double rating, int number_of_products, String author){

        this.name = name;
        this.desription = desription;
        this.price = price;
        this.rating = rating;
        this.number_of_products = number_of_products;
        this.author = author;
        status = "WAITING";
        time_created = System.currentTimeMillis();
        time_checked = -1L;
    }

    public ProductDTO(String name, String desription, double price, String author){

        this.name = name;
        this.desription = desription;
        this.price = price;
        this.rating = 5;
        this.number_of_products = 0;
        this.author = author;
        status = "WAITING";
        time_created = System.currentTimeMillis();
        time_checked = -1L;
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

    public String getDescription() {
        return desription;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String desription) {
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {this.author = author;}

    public String getStatus() {
        return status;
    }

    public long getTime_created() {
        return time_created;
    }

    public long getTime_checked() {
        return time_checked;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTime_checked(long time_checked) {
        this.time_checked = time_checked;
    }

    public void setTime_created(long time_created) {
        this.time_created = time_created;
    }

}

