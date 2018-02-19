package com.example.company.sabborah.responses.tutorAvailability;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Mohamed Sayed on 2/18/2018.
 */

public class StudentReservation {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("reservationId")
    @Expose
    private Integer reservationId;
    @SerializedName("studentId")
    @Expose
    private String studentId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("rating")
    @Expose
    private Object rating;
    @SerializedName("comment")
    @Expose
    private Object comment;
    @SerializedName("note")
    @Expose
    private Object note;
    @SerializedName("studentToken")
    @Expose
    private String studentToken;
    @SerializedName("studentUUID")
    @Expose
    private String studentUUID;
    @SerializedName("studentHasProfile")
    @Expose
    private Boolean studentHasProfile;
    @SerializedName("complaint")
    @Expose
    private Object complaint;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getReservationId() {
        return reservationId;
    }

    public void setReservationId(Integer reservationId) {
        this.reservationId = reservationId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getRating() {
        return rating;
    }

    public void setRating(Object rating) {
        this.rating = rating;
    }

    public Object getComment() {
        return comment;
    }

    public void setComment(Object comment) {
        this.comment = comment;
    }

    public Object getNote() {
        return note;
    }

    public void setNote(Object note) {
        this.note = note;
    }

    public String getStudentToken() {
        return studentToken;
    }

    public void setStudentToken(String studentToken) {
        this.studentToken = studentToken;
    }

    public String getStudentUUID() {
        return studentUUID;
    }

    public void setStudentUUID(String studentUUID) {
        this.studentUUID = studentUUID;
    }

    public Boolean getStudentHasProfile() {
        return studentHasProfile;
    }

    public void setStudentHasProfile(Boolean studentHasProfile) {
        this.studentHasProfile = studentHasProfile;
    }

    public Object getComplaint() {
        return complaint;
    }

    public void setComplaint(Object complaint) {
        this.complaint = complaint;
    }

}
