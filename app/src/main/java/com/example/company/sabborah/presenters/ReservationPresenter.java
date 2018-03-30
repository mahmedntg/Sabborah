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

public class ReservationPresenter extends Presenter<ReservationContract.View> implements ReservationContract.Presenter {
    private static TutorService service;
    private static ReservationPresenter mInstance;

    public static synchronized ReservationPresenter getInstance() {
        if (mInstance == null) {
            mInstance = new ReservationPresenter();
        }
        return mInstance;
    }

    private ReservationPresenter() {
        this.service = TutorService.getInstance();
    }

    @Override
    public void unSubscribe() {
        service.unSubscribe();
    }

    @Override
    public void getTutorInformation(String tutorId) {
        getView().setLoaderVisibility(true);
        service.getTutorInformation(tutorId, null , reservationCallBack);
    }

    @Override
    public void deleteTutorReservation(String tutorId, long reservationId) {
        getView().setLoaderVisibility(true);
        service.deleteTutorReservation(tutorId, reservationId, deleteReservationCallBack);
    }

    TutorService.ReservationCallBack reservationCallBack = new TutorService.ReservationCallBack() {
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
    TutorService.DeleteReservationCallBack deleteReservationCallBack = new TutorService.DeleteReservationCallBack() {
        @Override
        public void onSuccess(CommonResponse commonResponse) {
            getView().onDeleteTutorReservationSuccess(commonResponse);
            getView().setLoaderVisibility(false);
        }

        @Override
        public void onError(CommonResponse commonResponse) {
            getView().onDeleteTutorReservationFailed(commonResponse);
            getView().setLoaderVisibility(false);

        }
    };
}
