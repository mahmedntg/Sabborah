package com.example.company.sabborah.presenters;

import com.example.company.sabborah.BaseCallback;
import com.example.company.sabborah.Presenter;
import com.example.company.sabborah.models.User;
import com.example.company.sabborah.responses.CommonResponse;
import com.example.company.sabborah.services.ApiService.CommonService;
import com.example.company.sabborah.services.ApiService.UserService;

/**
 * Created by Mohamed Sayed on 1/7/2018.
 */

public class UserPresenter extends Presenter<UserContract.View> implements UserContract.Presenter {
    private static UserService service;
    private static UserPresenter mInstance;

    public static synchronized UserPresenter getInstance() {
        if (mInstance == null) {
            mInstance = new UserPresenter();
        }
        return mInstance;
    }

    private UserPresenter() {
        service = UserService.getInstance();
    }

    BaseCallback callback = new BaseCallback() {
        @Override
        public void onSuccess(CommonResponse commonResponse) {
            getView().onSuccess(commonResponse);
            getView().setLoaderVisibility(false);

        }

        @Override
        public void onError(CommonResponse commonResponse) {
            getView().onFailure(commonResponse);
            getView().setLoaderVisibility(false);
        }
    };

    @Override
    public void unSubscribe() {
        service.unSubscribe();
    }

    @Override
    public void login(User user) {
        getView().setLoaderVisibility(true);
        service.login(user, callback);
    }

    @Override
    public void register(User user) {
        getView().setLoaderVisibility(true);
        service.register(user, callback);
    }
}
