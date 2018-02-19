package com.example.company.sabborah.services.ApiService;

import com.example.company.sabborah.BaseCallback;
import com.example.company.sabborah.services.APIClient;
import com.example.company.sabborah.services.ApiInterface.UserInterface;
import com.example.company.sabborah.models.User;
import com.example.company.sabborah.responses.CommonResponse;


import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;


/**
 * Created by Mohamed Sayed on 1/4/2018.
 */

public class UserService {
    private static UserService mInstance;
    private CommonResponse commonResponse;
    UserInterface apiInterface;
    private Disposable disposable;

    private UserService() {
        commonResponse = new CommonResponse();
        apiInterface = APIClient.getInstance().getClient().create(UserInterface.class);
    }

    public static synchronized UserService getInstance() {
        if (mInstance == null) {
            mInstance = new UserService();
        }
        return mInstance;
    }


    public void register(User user, final BaseCallback callback) {
        disposable = apiInterface.register(user)
                .subscribeOn(io.reactivex.schedulers.Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableObserver<CommonResponse>() {
                    @Override
                    public void onNext(CommonResponse commonResponse) {
                        callback.onSuccess(commonResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        // cast to retrofit.HttpException to get the response code
                        if (e instanceof HttpException) {
                            HttpException response = (HttpException) e;
                            commonResponse = APIClient.getInstance().getErrorBody(response.response());
                        } else {
                            List<String> errors = new ArrayList<>();
                            errors.add(APIClient.connectionLost);
                            commonResponse.setErrors(errors);
                        }
                        callback.onError(commonResponse);
                    }

                    @Override
                    public void onComplete() {
                    }
                });

    }

    public void login(User user, final BaseCallback callback) {
        disposable = apiInterface.login(user)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableObserver<CommonResponse>() {


                    @Override
                    public void onNext(CommonResponse commonResponse) {
                        callback.onSuccess(commonResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        // cast to retrofit.HttpException to get the response code
                        if (e instanceof HttpException) {
                            HttpException response = (HttpException) e;
                            commonResponse = APIClient.getInstance().getErrorBody(response.response());
                        } else {
                            List<String> errors = new ArrayList<>();
                            errors.add(APIClient.connectionLost);
                            commonResponse.setErrors(errors);
                        }
                        callback.onError(commonResponse);
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

    public interface UserCallback {
        void onSuccess(CommonResponse commonResponse);

        void onError(CommonResponse commonResponse);
    }
}