package com.example.homeproject.dto.account;

import com.google.gson.annotations.SerializedName;

public class RegisterErrorDTO {
    @SerializedName("Email")
    private String [] email;
    @SerializedName("Password")
    private String [] password;
    @SerializedName("DisplayName")
    private String [] displayName;
    @SerializedName("Phone")
    private String [] phone;

    public String[] getPhone() {
        return phone;
    }

    public void setPhone(String[] phone) {
        this.phone = phone;
    }

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

    public String[] getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String[] displayName) {
        this.displayName = displayName;
    }
}
