package com.example.company.sabborah.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Mohamed Sayed on 2/9/2018.
 */

public class SubjectDiff {
    @SerializedName("subjectId")
    @Expose
    private Integer subjectId;
    @SerializedName("selected")
    @Expose
    private Boolean selected;
    @SerializedName("singleRate")
    @Expose
    private Double singleRate;
    @SerializedName("groupRate")
    @Expose
    private Double groupRate;

    public SubjectDiff(Integer subjectId, Boolean selected, Double singleRate, Double groupRate) {
        this.subjectId = subjectId;
        this.selected = selected;
        this.singleRate = singleRate;
        this.groupRate = groupRate;
    }


    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    public Double getSingleRate() {
        return singleRate;
    }

    public void setSingleRate(Double singleRate) {
        this.singleRate = singleRate;
    }

    public Double getGroupRate() {
        return groupRate;
    }

    public void setGroupRate(Double groupRate) {
        this.groupRate = groupRate;
    }
}
