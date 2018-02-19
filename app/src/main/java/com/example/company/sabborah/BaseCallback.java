package com.example.company.sabborah;

import com.example.company.sabborah.models.Country;
import com.example.company.sabborah.responses.CommonResponse;
import com.example.company.sabborah.responses.tutorAvailability.TutorReservation;

import java.util.List;

/**
 * Created by ahmedeltaher on 3/22/17.
 */

public interface BaseCallback {
    void onSuccess(CommonResponse commonResponse);
    void onError(CommonResponse commonResponse);
}
