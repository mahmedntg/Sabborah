package com.example.company.sabborah.services.ApiService;

import com.example.company.sabborah.CommonCallback;
import com.example.company.sabborah.models.Country;
import com.example.company.sabborah.responses.CountryResponse;
import com.example.company.sabborah.services.APIClient;
import com.example.company.sabborah.services.ApiInterface.CommonInterface;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;


/**
 * Created by Mohamed Sayed on 1/4/2018.
 */

public class CommonService {
    private static CommonService mInstance;
    private CountryResponse countryResponse;
    CommonInterface apiInterface;
    private Disposable disposable;

    private CommonService() {
        countryResponse = new CountryResponse();
        apiInterface = APIClient.getInstance().getClient().create(CommonInterface.class);
    }

    public static synchronized CommonService getInstance() {
        if (mInstance == null) {
            mInstance = new CommonService();
        }
        return mInstance;
    }


    public void getCountries(final CommonCallback callback) {
        disposable = apiInterface.getCountries()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableObserver<List<CountryResponse>>() {

                    @Override
                    public void onNext(List<CountryResponse> countryResponse) {
                        List<Country> countries = new ArrayList<Country>();
                        for (CountryResponse response : countryResponse) {
                            Country country = new Country(response.getId(), response.getArName(), response.getEnName());
                            countries.add(country);
                        }
                        callback.onSuccess(countries);
                    }

                    @Override
                    public void onError(Throwable e) {
                        // cast to retrofit.HttpException to get the response code
                        if (!(e instanceof HttpException)) {
                            callback.onError(APIClient.connectionLost);
                        }
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    public void unSubscribe() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }


}