package com.example.company.sabborah.responses.tutorAvailability;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Mohamed Sayed on 2/18/2018.
 */

public class AvailabilityDetails {
    @SerializedName("id")
    @Expose
    private long id;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("timeSlot")
    @Expose
    private int timeSlot;
    @SerializedName("tutorId")
    @Expose
    private String tutorId;
    @SerializedName("reservationId")
    @Expose
    private long reservationId;
    @SerializedName("startTime")
    @Expose
    private String startTime;
    @SerializedName("subjectId")
    @Expose
    private int subjectId;
    @SerializedName("groupMax")
    @Expose
    private int groupMax;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(int timeSlot) {
        this.timeSlot = timeSlot;
    }

    public String getTutorId() {
        return tutorId;
    }

    public void setTutorId(String tutorId) {
        this.tutorId = tutorId;
    }

    public long getReservationId() {
        return reservationId;
    }

    public void setReservationId(long reservationId) {
        this.reservationId = reservationId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public int getGroupMax() {
        return groupMax;
    }

    public void setGroupMax(int groupMax) {
        this.groupMax = groupMax;
    }
}
