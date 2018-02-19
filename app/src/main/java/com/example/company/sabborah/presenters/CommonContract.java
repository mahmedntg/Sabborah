package com.example.company.sabborah.presenters;

import com.example.company.sabborah.BaseView;
import com.example.company.sabborah.models.Country;
import com.example.company.sabborah.responses.CommonResponse;

import java.util.List;

/**
 * Created by Mohamed Sayed on 5/12/2017
 */

public interface CommonContract {

    interface View extends BaseView {
        void initializeCountryList(List<Country> countries);
        void setLoaderVisibility(boolean isVisible);
        void onSuccess(CommonResponse response);
        void onFailure(CommonResponse response);
    }

    interface Presenter {
        void getCountries();

        void unSubscribe();

    }
}
