package com.example.company.sabborah.presenters;

import com.example.company.sabborah.responses.CommonResponse;

/**
 * Created by Mohamed Sayed on 1/7/2018.
 */

public interface UserView {
    void showWait();

    void removeWait();

    void onFailure(CommonResponse commonResponse);

    void userSuccess(CommonResponse commonResponse);

}
