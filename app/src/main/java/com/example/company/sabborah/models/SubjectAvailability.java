package com.example.company.sabborah.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Mohamed Sayed on 2/24/2018.
 */

public class SubjectAvailability {
    @SerializedName("timeslot")
    @Expose
    private int timeslot;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("subjectId")
    @Expose
    private int subjectId;
    @SerializedName("groupMax")
    @Expose
    private int groupMax;
    @SerializedName("reservationId")
    @Expose
    private long reservationId;
    @SerializedName("tutorId")
    @Expose
    private String tutorId;
    @SerializedName("id")
    @Expose
    private Long availabilityId;

    public int getTimeslot() {
        return timeslot;
    }

    public void setTimeslot(int timeslot) {
        this.timeslot = timeslot;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public long getReservationId() {
        return reservationId;
    }

    public void setReservationId(long reservationId) {
        this.reservationId = reservationId;
    }

    public String getTutorId() {
        return tutorId;
    }

    public void setTutorId(String tutorId) {
        this.tutorId = tutorId;
    }

    public Long getAvailabilityId() {
        return availabilityId;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public void setAvailabilityId(Long availabilityId) {
        this.availabilityId = availabilityId;
    }
}
