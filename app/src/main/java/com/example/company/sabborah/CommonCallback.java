package com.example.company.sabborah;

import com.example.company.sabborah.models.Country;

import java.util.List;

/**
 * Created by ahmedeltaher on 3/22/17.
 */

public interface CommonCallback {
    void onSuccess(List<Country> countryResponse);

    void onError(String errorMessage);
}
