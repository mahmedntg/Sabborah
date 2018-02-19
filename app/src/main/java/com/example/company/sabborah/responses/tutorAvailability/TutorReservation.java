package com.example.company.sabborah.responses.tutorAvailability;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Mohamed Sayed on 2/18/2018.
 */

public class TutorReservation {
    @SerializedName("subjects")
    @Expose
    private List<AvailablilitySubject> subjects = null;
    @SerializedName("availability")
    @Expose
    private Availability availability;
    @SerializedName("singleRate")
    @Expose
    private Integer singleRate;
    @SerializedName("groupRate")
    @Expose
    private Integer groupRate;
    @SerializedName("nextAvailableTime")
    @Expose
    private Integer nextAvailableTime;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("balance")
    @Expose
    private Double balance;
    @SerializedName("rating")
    @Expose
    private Integer rating;
    @SerializedName("isTutor")
    @Expose
    private Boolean isTutor;
    @SerializedName("lastUpdate")
    @Expose
    private Long lastUpdate;
    @SerializedName("uuid")
    @Expose
    private String uuid;
    @SerializedName("hasProfile")
    @Expose
    private Boolean hasProfile;
    @SerializedName("reservations")
    @Expose
    private Reservations reservations;
    @SerializedName("studentReservations")
    @Expose
    private List<StudentReservation> studentReservations = null;

    public List<AvailablilitySubject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<AvailablilitySubject> subjects) {
        this.subjects = subjects;
    }

    public Availability getAvailability() {
        return availability;
    }

    public void setAvailability(Availability availability) {
        this.availability = availability;
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

    public Integer getNextAvailableTime() {
        return nextAvailableTime;
    }

    public void setNextAvailableTime(Integer nextAvailableTime) {
        this.nextAvailableTime = nextAvailableTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Boolean getIsTutor() {
        return isTutor;
    }

    public void setIsTutor(Boolean isTutor) {
        this.isTutor = isTutor;
    }

    public Long getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Long lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Boolean getHasProfile() {
        return hasProfile;
    }

    public void setHasProfile(Boolean hasProfile) {
        this.hasProfile = hasProfile;
    }

    public Reservations getReservations() {
        return reservations;
    }

    public void setReservations(Reservations reservations) {
        this.reservations = reservations;
    }

    public List<StudentReservation> getStudentReservations() {
        return studentReservations;
    }

    public void setStudentReservations(List<StudentReservation> studentReservations) {
        this.studentReservations = studentReservations;
    }

}
