package com.example.company.sabborah.presenters;

import com.example.company.sabborah.BaseView;
import com.example.company.sabborah.models.SubjectAvailability;
import com.example.company.sabborah.responses.CommonResponse;
import com.example.company.sabborah.responses.tutor.Subject;
import com.example.company.sabborah.responses.tutorAvailability.TutorReservation;

import java.util.List;

/**
 * Created by Mohamed Sayed on 5/12/2017
 */

public interface ReservationContract {

    interface View extends BaseView {

        void setLoaderVisibility(boolean isVisible);


        void onDeleteTutorReservationSuccess(CommonResponse commonResponse);

        void onDeleteTutorReservationFailed(CommonResponse commonResponse);

        void onGetTutorInformationSuccess(TutorReservation tutorReservation);
    }

    interface Presenter {
        void unSubscribe();

        void getTutorInformation(String tutorId);

        void deleteTutorReservation(String tutorId, long reservationId);
    }
}
