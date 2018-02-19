package com.example.company.sabborah.presenters;

import com.example.company.sabborah.BaseView;
import com.example.company.sabborah.responses.CommonResponse;
import com.example.company.sabborah.responses.tutor.Subject;
import com.example.company.sabborah.responses.tutorAvailability.TutorReservation;

import java.util.List;

/**
 * Created by Mohamed Sayed on 5/12/2017
 */

public interface TutorContract {

    interface View extends BaseView {
        void onGetLevelsSuccess(CommonResponse response);

        void onGetLevelsFailure(CommonResponse response);

        void onAddSubjectSuccess(CommonResponse response);

        void onAddSubjectFailure(CommonResponse response);

        void setLoaderVisibility(boolean isVisible);

        void onGetTutorInformationSuccess(TutorReservation tutorReservation);
    }

    interface Presenter {
        void unSubscribe();

        void getLevels();

        void addSubject(String tutorId, List<Subject> subjects);

        void getTutorInformation(String tutorId);
    }
}
