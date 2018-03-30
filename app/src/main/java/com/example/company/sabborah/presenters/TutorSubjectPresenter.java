package com.example.company.sabborah.presenters;

import com.example.company.sabborah.BaseCallback;
import com.example.company.sabborah.Presenter;
import com.example.company.sabborah.models.SubjectAvailability;
import com.example.company.sabborah.responses.CommonResponse;
import com.example.company.sabborah.responses.tutor.Subject;
import com.example.company.sabborah.responses.tutorAvailability.TutorReservation;
import com.example.company.sabborah.services.ApiService.TutorService;

import java.util.List;

/**
 * Created by Mohamed Sayed on 1/7/2018.
 */

public class TutorSubjectPresenter extends Presenter<TutorSubjectContract.View> implements TutorSubjectContract.Presenter {
    private static TutorService service;
    private static TutorSubjectPresenter mInstance;

    public static synchronized TutorSubjectPresenter getInstance() {
        if (mInstance == null) {
            mInstance = new TutorSubjectPresenter();
        }
        return mInstance;
    }

    private TutorSubjectPresenter() {
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
}
