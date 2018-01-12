package com.example.company.sabborah.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Mohamed Sayed on 1/4/2018.
 */

public class User {
    @SerializedName("name")
    private String name;
    @SerializedName("mobile")
    private String mobile;
    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String password;
    @SerializedName("isTutor")
    private boolean isTutor;
    @SerializedName("countryId")
    private String countryId;

    public User() {
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(String name, String mobile, String email, String password, boolean isTutor) {
        this.name = name;
        this.mobile = mobile;
        this.email = email;
        this.password = password;
        this.isTutor = isTutor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
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

    public boolean isTutor() {
        return isTutor;
    }

    public void setTutor(boolean tutor) {
        isTutor = tutor;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }
}
