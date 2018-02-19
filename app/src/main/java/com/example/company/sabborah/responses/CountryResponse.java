package com.example.company.sabborah.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Mohamed Sayed on 1/15/2018.
 */

public class CountryResponse {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("ar_name")
    @Expose
    private String arName;
    @SerializedName("en_name")
    @Expose
    private String enName;
    @SerializedName("code")
    @Expose
    private String code;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
