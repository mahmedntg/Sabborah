package com.example.company.sabborah.responses.tutorAvailability;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

/**
 * Created by Mohamed Sayed on 2/18/2018.
 */

public class Availability {
    @Expose
    private Map<String, List<AvailabilityDetails>> av1 = null;

    public Map<String, List<AvailabilityDetails>> getAv1() {
        return av1;
    }

    public void setAv1(Map<String, List<AvailabilityDetails>> av1) {
        this.av1 = av1;
    }

   }
