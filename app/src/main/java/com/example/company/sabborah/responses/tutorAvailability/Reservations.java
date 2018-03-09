package com.example.company.sabborah.responses.tutorAvailability;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Mohamed Sayed on 2/18/2018.
 */

public class Reservations {
    @SerializedName("reservationDetails")
    @Expose
    private List<ReservationDetails> reservationDetails = null;

    public List<ReservationDetails> getReservationDetails() {
        return reservationDetails;
    }

    public void setReservationDetails(List<ReservationDetails> reservationDetails) {
        this.reservationDetails = reservationDetails;
    }
}
