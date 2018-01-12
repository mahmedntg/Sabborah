package com.example.company.sabborah.presenters;

import com.example.company.sabborah.models.User;
import com.example.company.sabborah.responses.CommonResponse;
import com.example.company.sabborah.services.ApiService.UserService;

/**
 * Created by Mohamed Sayed on 1/7/2018.
 */

public class UserPresenter {
    private final UserService service;
    private final UserView view;
    private static UserPresenter mInstance;

    public static synchronized UserPresenter getInstance(UserService service, UserView view) {
        if (mInstance == null) {
            mInstance = new UserPresenter(service, view);
        }
        return mInstance;
    }

    private UserPresenter(UserService service, UserView view) {
        this.service = service;
        this.view = view;
    }

    public void login(User user) {
        view.showWait();
        service.login(user, new UserService.UserCallback() {
            @Override
            public void onSuccess(CommonResponse commonResponse) {
                view.removeWait();
                view.userSuccess(commonResponse);
            }

            @Override
            public void onError(CommonResponse commonResponse) {
                view.removeWait();
                view.onFailure(commonResponse);
            }
        });
    }

    public void register(User user) {
        view.showWait();
        service.register(user, new UserService.UserCallback() {
            @Override
            public void onSuccess(CommonResponse commonResponse) {
                view.removeWait();
                view.userSuccess(commonResponse);
            }

            @Override
            public void onError(CommonResponse commonResponse) {
                view.removeWait();
                view.onFailure(commonResponse);
            }
        });
    }
}
