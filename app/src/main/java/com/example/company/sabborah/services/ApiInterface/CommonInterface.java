package com.example.company.sabborah.services.ApiInterface;

import com.example.company.sabborah.models.User;
import com.example.company.sabborah.responses.CommonResponse;
import com.example.company.sabborah.responses.CountryResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Mohamed Sayed on 1/4/2018.
 */

public interface CommonInterface {
    @GET("countries")
    Observable<List<CountryResponse>> getCountries();

}
