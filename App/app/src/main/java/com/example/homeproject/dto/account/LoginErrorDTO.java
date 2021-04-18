package com.example.homeproject.dto.account;

import com.google.gson.annotations.SerializedName;

public class LoginErrorDTO {
    @SerializedName("Email")
    private String [] email;
    @SerializedName("Password")
    private String [] password;

    @SerializedName("Invalid")
    private String [] invalid;

    public String[] getEmail() {
        return email;
    }

    public void setEmail(String[] email) {
        this.email = email;
    }

    public String[] getPassword() {
        return password;
    }

    public void setPassword(String[] password) {
        this.password = password;
    }

    public String[] getInvalid() {
        return invalid;
    }

    public void setInvalid(String[] invalid) {
        this.invalid = invalid;
    }
}
