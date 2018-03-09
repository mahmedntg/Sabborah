package com.example.company.sabborah.responses.tutorAvailability;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Mohamed Sayed on 2/18/2018.
 */

public class TutorReservation {
    @SerializedName("subjects")
    @Expose
    private List<AvailabilitySubject> subjects = null;
    private Map<String, List<AvailabilityDetails>> availability;
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
    private Map<String, List<ReservationDetails>> reservations;
    @SerializedName("studentReservations")
    @Expose
    private List<StudentReservation> studentReservations = null;

    public List<AvailabilitySubject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<AvailabilitySubject> subjects) {
        this.subjects = subjects;
    }

    public Map<String, List<AvailabilityDetails>> getAvailability() {
        return availability;
    }

    public void setAvailability(Map<String, List<AvailabilityDetails>> availability) {
        this.availability = availability;
    }

    public Boolean getTutor() {
        return isTutor;
    }

    public void setTutor(Boolean tutor) {
        isTutor = tutor;
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

    public Map<String, List<ReservationDetails>> getReservations() {
        return reservations;
    }

    public void setReservations(Map<String, List<ReservationDetails>> reservations) {
        this.reservations = reservations;
    }

    public List<StudentReservation> getStudentReservations() {
        return studentReservations;
    }

    public void setStudentReservations(List<StudentReservation> studentReservations) {
        this.studentReservations = studentReservations;
    }
}
