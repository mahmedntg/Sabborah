package com.example.company.sabborah.presenters;

import com.example.company.sabborah.BaseView;
import com.example.company.sabborah.models.Country;
import com.example.company.sabborah.models.User;
import com.example.company.sabborah.responses.CommonResponse;

import java.util.List;

/**
 * Created by Mohamed Sayed on 5/12/2017
 */

public interface UserContract {

    interface View extends BaseView {
        void onSuccess(CommonResponse response);

        void onFailure(CommonResponse response);

        void setLoaderVisibility(boolean isVisible);
    }

    interface Presenter {
        void unSubscribe();
        void login(User user);
        void register(User user);
    }
}
