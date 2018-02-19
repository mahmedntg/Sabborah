package com.example.company.sabborah.presenters;


import com.example.company.sabborah.CommonCallback;
import com.example.company.sabborah.Presenter;
import com.example.company.sabborah.models.Country;
import com.example.company.sabborah.responses.CommonResponse;
import com.example.company.sabborah.services.ApiService.CommonService;

import java.util.List;

/**
 * Created by Mohamed Sayed on 1/15/2018.
 */

public class CommonPresenter extends Presenter<CommonContract.View> implements CommonContract.Presenter {
    private static CommonPresenter mInstance;
    private static CommonService commonService;

    public static synchronized CommonPresenter getInstance() {
        if (mInstance == null) {
            mInstance = new CommonPresenter();
            commonService = CommonService.getInstance();
        }
        return mInstance;
    }

    private CommonPresenter() {
    }

    @Override
    public void getCountries() {
        getView().setLoaderVisibility(true);
        commonService.getCountries(callback);
    }

    @Override
    public void unSubscribe() {
        commonService.unSubscribe();
    }

    private final CommonCallback callback = new CommonCallback() {
        CommonResponse commonResponse = new CommonResponse();

        @Override
        public void onSuccess(List<Country> countries) {
            getView().initializeCountryList(countries);
            getView().setLoaderVisibility(false);
        }

        @Override
        public void onError(String errorMessage) {
            getView().setLoaderVisibility(false);
            commonResponse.setMessage(errorMessage);
            getView().onFailure(commonResponse);
        }

    };


}
