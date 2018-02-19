package com.example.company.sabborah.services.ApiInterface;

import com.example.company.sabborah.models.TutorSubjectRequest;
import com.example.company.sabborah.responses.CommonResponse;
import com.example.company.sabborah.responses.tutorAvailability.TutorReservation;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Mohamed Sayed on 1/4/2018.
 */

public interface TutorInterface {
    @GET("levels")
    Observable<CommonResponse> getLevels();

    @POST("tutors/{tutorId}/subjects")
    Observable<ResponseBody> addSubject(@Path("tutorId") String tutorId, @Body TutorSubjectRequest tutorSubjectRequest);

    @GET("tutors/{tutorId}")
    Observable<TutorReservation> getTutorInformation(@Path("tutorId") String tutorId);

}
