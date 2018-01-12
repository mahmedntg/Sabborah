package com.example.company.sabborah.services.ApiInterface;

import com.example.company.sabborah.models.User;
import com.example.company.sabborah.responses.CommonResponse;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Mohamed Sayed on 1/4/2018.
 */

public interface UserInterface {
    @POST("accounts/register")
    Observable<CommonResponse> register(@Body User user);

    @POST("accounts/login")
    Observable<CommonResponse> login(@Body User user);
}
