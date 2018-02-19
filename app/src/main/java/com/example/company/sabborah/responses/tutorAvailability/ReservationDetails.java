package com.example.company.sabborah.responses.tutorAvailability;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Mohamed Sayed on 2/18/2018.
 */

public class ReservationDetails {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("firstTimeSlot")
    @Expose
    private Integer firstTimeSlot;
    @SerializedName("availabilityIds")
    @Expose
    private List<Integer> availabilityIds = null;
    @SerializedName("subjectId")
    @Expose
    private Integer subjectId;
    @SerializedName("subjectNames")
    @Expose
    private List<String> subjectNames = null;
    @SerializedName("sessionId")
    @Expose
    private String sessionId;
    @SerializedName("tutorId")
    @Expose
    private String tutorId;
    @SerializedName("note")
    @Expose
    private Object note;
    @SerializedName("tutorName")
    @Expose
    private String tutorName;
    @SerializedName("tutorToken")
    @Expose
    private String tutorToken;
    @SerializedName("tutorUUID")
    @Expose
    private String tutorUUID;
    @SerializedName("rate")
    @Expose
    private Integer rate;
    @SerializedName("isGroup")
    @Expose
    private Boolean isGroup;
    @SerializedName("groupMax")
    @Expose
    private Object groupMax;
    @SerializedName("groupCount")
    @Expose
    private Integer groupCount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getFirstTimeSlot() {
        return firstTimeSlot;
    }

    public void setFirstTimeSlot(Integer firstTimeSlot) {
        this.firstTimeSlot = firstTimeSlot;
    }

    public List<Integer> getAvailabilityIds() {
        return availabilityIds;
    }

    public void setAvailabilityIds(List<Integer> availabilityIds) {
        this.availabilityIds = availabilityIds;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public List<String> getSubjectNames() {
        return subjectNames;
    }

    public void setSubjectNames(List<String> subjectNames) {
        this.subjectNames = subjectNames;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getTutorId() {
        return tutorId;
    }

    public void setTutorId(String tutorId) {
        this.tutorId = tutorId;
    }

    public Object getNote() {
        return note;
    }

    public void setNote(Object note) {
        this.note = note;
    }

    public String getTutorName() {
        return tutorName;
    }

    public void setTutorName(String tutorName) {
        this.tutorName = tutorName;
    }

    public String getTutorToken() {
        return tutorToken;
    }

    public void setTutorToken(String tutorToken) {
        this.tutorToken = tutorToken;
    }

    public String getTutorUUID() {
        return tutorUUID;
    }

    public void setTutorUUID(String tutorUUID) {
        this.tutorUUID = tutorUUID;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public Boolean getIsGroup() {
        return isGroup;
    }

    public void setIsGroup(Boolean isGroup) {
        this.isGroup = isGroup;
    }

    public Object getGroupMax() {
        return groupMax;
    }

    public void setGroupMax(Object groupMax) {
        this.groupMax = groupMax;
    }

    public Integer getGroupCount() {
        return groupCount;
    }

    public void setGroupCount(Integer groupCount) {
        this.groupCount = groupCount;
    }
}
