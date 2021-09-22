package com.example.testapk.userData;

public class UserDTO {

    private String username, password, email, role;
    private int id;
    private int balance = 500;
    private String products_owned = "";

    public UserDTO() {}
    public UserDTO(int id, String username, String password, String email)
    {
        this.username = username;
        this.id = id;
        this.password = password;
        this.email = email;
        role = "USER";
    }

    public UserDTO(int id, String username, String password, String email, String role, int balance, String products_owned)
    {
        this.username = username;
        this.id = id;
        this.password = password;
        this.email = email;
        this.role = role;
        this.balance = balance;
        this.products_owned = products_owned;
    }

    public UserDTO(String username, String password, String email)
    {
        this.username = username;
        this.password = password;
        this.email = email;
        role = "USER";
    }

    public String getUsername() {
        return this.username;
    }

    public int getId() {
        return this.id;
    }

    public String getPassword() {
        return this.password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPassword(String description) {
        this.password = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getProducts_owned() {
        return products_owned;
    }

    public void setProducts_owned(String products_owned) {
        this.products_owned = products_owned;
    }

    public void banUser(String banReason)
    {
        role = "BANNED - " + banReason;
    }
}
