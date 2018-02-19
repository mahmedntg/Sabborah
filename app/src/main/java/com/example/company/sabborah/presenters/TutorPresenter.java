package com.example.company.sabborah.presenters;

import com.example.company.sabborah.BaseCallback;
import com.example.company.sabborah.Presenter;
import com.example.company.sabborah.responses.CommonResponse;
import com.example.company.sabborah.responses.tutor.Subject;
import com.example.company.sabborah.responses.tutorAvailability.TutorReservation;
import com.example.company.sabborah.services.ApiService.TutorService;

import java.util.List;

/**
 * Created by Mohamed Sayed on 1/7/2018.
 */

public class TutorPresenter extends Presenter<TutorContract.View> implements TutorContract.Presenter {
    private static TutorService service;
    private static TutorPresenter mInstance;

    public static synchronized TutorPresenter getInstance() {
        if (mInstance == null) {
            mInstance = new TutorPresenter();
        }
        return mInstance;
    }

    private TutorPresenter() {
        this.service = TutorService.getInstance();
    }

    @Override
    public void unSubscribe() {
        service.unSubscribe();
    }

    @Override
    public void getLevels() {
        getView().setLoaderVisibility(true);
        service.getLevels(getLevelCallback);
    }

    @Override
    public void addSubject(String tutorId, List<Subject> subjects) {
        getView().setLoaderVisibility(true);
        service.addSubject(tutorId, subjects, addSubjectCallback);
    }

    @Override
    public void getTutorInformation(String tutorId) {
        getView().setLoaderVisibility(true);
        service.getTutorInformation(tutorId, tutorCallBack);
    }

    BaseCallback getLevelCallback = new BaseCallback() {
        @Override
        public void onSuccess(CommonResponse commonResponse) {
            getView().onGetLevelsSuccess(commonResponse);
            getView().setLoaderVisibility(false);

        }

        @Override
        public void onError(CommonResponse commonResponse) {
            getView().onGetLevelsFailure(commonResponse);
            getView().setLoaderVisibility(false);
        }
    };

    BaseCallback addSubjectCallback = new BaseCallback() {
        @Override
        public void onSuccess(CommonResponse commonResponse) {
            getView().onAddSubjectSuccess(commonResponse);
            getView().setLoaderVisibility(false);

        }

        @Override
        public void onError(CommonResponse commonResponse) {
            getView().onAddSubjectFailure(commonResponse);
            getView().setLoaderVisibility(false);
        }
    };
    TutorService.TutorCallBack tutorCallBack = new TutorService.TutorCallBack() {
        @Override
        public void onSuccess(TutorReservation tutorReservation) {
            getView().onGetTutorInformationSuccess(tutorReservation);
            getView().setLoaderVisibility(false);

        }

        @Override
        public void onError(CommonResponse commonResponse) {
            getView().setLoaderVisibility(false);
        }
    };
}
