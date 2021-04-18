package com.example.homeproject.dto.account;

public class RegisterDTO {
    private String email;
    private String password;
    private String displayName;
    private String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public RegisterDTO(String email, String password, String displayName, String phone) {
        this.email = email;
        this.password = password;
        this.displayName = displayName;
        this.phone = phone;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
