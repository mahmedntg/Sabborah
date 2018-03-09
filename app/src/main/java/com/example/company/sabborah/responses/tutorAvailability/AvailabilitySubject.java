package com.example.company.sabborah.responses.tutorAvailability;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Mohamed Sayed on 2/18/2018.
 */

public class AvailabilitySubject {
    @SerializedName("levelId")
    @Expose
    private Integer levelId;
    @SerializedName("gradeId")
    @Expose
    private Integer gradeId;
    @SerializedName("subjectId")
    @Expose
    private Integer subjectId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("singleRate")
    @Expose
    private Integer singleRate;
    @SerializedName("groupRate")
    @Expose
    private Integer groupRate;
    @SerializedName("minRate")
    @Expose
    private Integer minRate;

    public Integer getLevelId() {
        return levelId;
    }

    public void setLevelId(Integer levelId) {
        this.levelId = levelId;
    }

    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSingleRate() {
        return singleRate;
    }

    public void setSingleRate(Integer singleRate) {
        this.singleRate = singleRate;
    }

    public Integer getGroupRate() {
        return groupRate;
    }

    public void setGroupRate(Integer groupRate) {
        this.groupRate = groupRate;
    }

    public Integer getMinRate() {
        return minRate;
    }

    public void setMinRate(Integer minRate) {
        this.minRate = minRate;
    }
}
