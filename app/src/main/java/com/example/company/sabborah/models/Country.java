package com.example.company.sabborah.models;

/**
 * Created by Mohamed Sayed on 1/15/2018.
 */

public class Country {
    private Integer id;
    private String arName;
    private String enName;

    public Country(Integer id, String enName) {
        this.id = id;
        this.enName = enName;
    }

    public Country(Integer id, String arName, String enName) {
        this.id = id;
        this.arName = arName;
        this.enName = enName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getArName() {
        return arName;
    }

    public void setArName(String arName) {
        this.arName = arName;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    @Override
    public String toString() {
        return enName + " - " + arName;
    }
}
