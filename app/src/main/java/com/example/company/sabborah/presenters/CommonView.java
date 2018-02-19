package com.example.company.sabborah.presenters;

import com.example.company.sabborah.models.Country;
import com.example.company.sabborah.responses.CommonResponse;
import com.example.company.sabborah.responses.CountryResponse;

import java.util.List;

/**
 * Created by Mohamed Sayed on 1/7/2018.
 */

public interface CommonView {
    void showWait();

    void removeWait();

    void onFailure(String errorMessage);

    void loadCountries(List<Country> countryResponse);

}
