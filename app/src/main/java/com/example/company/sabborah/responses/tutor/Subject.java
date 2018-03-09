package com.example.company.sabborah.responses.tutor;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Mohamed Sayed on 2/3/2018.
 */

public class Subject {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("minRate")
    @Expose
    private Integer minRate;
    private boolean checked;
    private double singleRate;
    private double groupRate;
    private boolean clicked;

    public boolean isClicked() {
        return clicked;
    }

    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getMinRate() {
        return minRate;
    }

    public void setMinRate(Integer minRate) {
        this.minRate = minRate;
    }


    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public double getSingleRate() {
        return singleRate == 0 ? 5.0 : singleRate;
    }

    public void setSingleRate(double singleRate) {
        this.singleRate = singleRate;
    }

    public double getGroupRate() {
        return groupRate == 0 ? 5.0 : groupRate;
    }

    public void setGroupRate(double groupRate) {
        this.groupRate = groupRate;
    }

    @Override
    public String toString() {
        return name;
    }
}