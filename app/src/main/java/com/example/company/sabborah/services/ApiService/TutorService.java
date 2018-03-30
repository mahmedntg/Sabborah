package com.example.company.sabborah.services.ApiService;

import com.example.company.sabborah.BaseCallback;
import com.example.company.sabborah.models.SubjectAvailability;
import com.example.company.sabborah.models.SubjectDiff;
import com.example.company.sabborah.models.TutorSubjectRequest;
import com.example.company.sabborah.responses.CommonResponse;
import com.example.company.sabborah.responses.tutor.Subject;
import com.example.company.sabborah.responses.tutorAvailability.AvailabilityDetails;
import com.example.company.sabborah.responses.tutorAvailability.ReservationDetails;
import com.example.company.sabborah.responses.tutorAvailability.TutorReservation;
import com.example.company.sabborah.services.APIClient;
import com.example.company.sabborah.services.ApiInterface.TutorInterface;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.HttpException;


/**
 * Created by Mohamed Sayed on 1/4/2018.
 */

public class TutorService {
    private static TutorService mInstance;
    private CommonResponse commonResponse;
    TutorInterface apiInterface;
    private Disposable disposable;

    private TutorService() {
        commonResponse = new CommonResponse();
        apiInterface = APIClient.getInstance().getClient().create(TutorInterface.class);
    }

    public static synchronized TutorService getInstance() {
        if (mInstance == null) {
            mInstance = new TutorService();
        }
        return mInstance;
    }

    /**
     * get levels
     *
     * @param callback
     */
    public void getLevels(final BaseCallback callback) {
        disposable = apiInterface.getLevels()
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

    /**
     * add tutor's subjects
     *
     * @param tutorId
     * @param subjects
     * @param callback
     */
    public void addSubject(String tutorId, List<Subject> subjects, final BaseCallback callback) {

        List<SubjectDiff> subjectDiffs = new ArrayList<>();
        for (Subject subject : subjects) {
            SubjectDiff subjectDiff = new SubjectDiff(subject.getId(), true, subject.getSingleRate(), subject.getGroupRate());
            subjectDiffs.add(subjectDiff);
        }
        TutorSubjectRequest request = new TutorSubjectRequest();
        //request.setDiff(subjectDiffs);
        disposable = apiInterface.addSubject(tutorId, request)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableObserver<ResponseBody>() {


                    @Override
                    public void onNext(ResponseBody responseBody) {
                        commonResponse.setSuccess(true);
                        commonResponse.setMessage("Subjects added successfully");
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

    public void addAvailability(String tutorId, List<SubjectAvailability> subjectAvailabilities, final BaseCallback callback) {
        TutorSubjectRequest request = new TutorSubjectRequest();
        request.setRoot(subjectAvailabilities);
        disposable = apiInterface.addAvailability(tutorId, request)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableObserver<ResponseBody>() {
                    @Override
                    public void onNext(ResponseBody responseBody) {
                        commonResponse.setSuccess(true);
                        commonResponse.setMessage("Availability added successfully");
                        callback.onSuccess(commonResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        // cast to retrofit.HttpException to get the response code
                        if (e instanceof HttpException) {
                            HttpException response = (HttpException) e;
                            commonResponse = APIClient.getInstance().getNonCommonErrorBody(response.response());
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

    /**
     * get tutor information
     *
     * @param tutorId
     * @param callback
     */
    public void getTutorInformation(String tutorId, final TutorCallBack callback, final ReservationCallBack reservationCallBack) {
        disposable = apiInterface.getTutorInformation(tutorId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableObserver<ResponseBody>() {
                    @Override
                    public void onNext(ResponseBody responseBody) {
                        Type availabilityType = new TypeToken<HashMap<String, List<AvailabilityDetails>>>() {
                        }.getType();
                        Type reservationType = new TypeToken<HashMap<String, List<ReservationDetails>>>() {
                        }.getType();
                        TutorReservation tutorReservation = new TutorReservation();
                        GsonBuilder builder = new GsonBuilder();
                        builder.excludeFieldsWithoutExposeAnnotation();
                        Gson gson = builder.create();
                        try {
                            String response = responseBody.string();
                            tutorReservation = gson.fromJson(response, TutorReservation.class);
                            JSONObject jsonObject = new JSONObject(response);
                            JSONObject availability = jsonObject.getJSONObject("availability");
                            JSONObject reservation = jsonObject.getJSONObject("reservations");
                            tutorReservation.setAvailability(gson.fromJson(availability.toString(), availabilityType));
                            tutorReservation.setReservations(gson.fromJson(reservation.toString(), reservationType));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        if (reservationCallBack != null)
                            reservationCallBack.onSuccess(tutorReservation);
                        else
                            callback.onSuccess(tutorReservation);
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
                        if (reservationCallBack != null)
                            reservationCallBack.onError(commonResponse);
                        else
                            callback.onError(commonResponse);
                    }

                    @Override
                    public void onComplete() {
                    }
                });

    }

    public void deleteTutorReservation(String tutorId, long reservationId, final DeleteReservationCallBack callback) {
        disposable = apiInterface.deleteTutorReservation(tutorId, reservationId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableObserver<ResponseBody>() {
                    @Override
                    public void onNext(ResponseBody responseBody) {
                        commonResponse.setMessage("Reservation deleted successfully.");
                        commonResponse.setSuccess(true);
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

    public interface TutorCallBack {
        void onSuccess(TutorReservation tutorReservation);

        void onError(CommonResponse commonResponse);
    }

    public interface ReservationCallBack {
        void onSuccess(TutorReservation tutorReservation);

        void onError(CommonResponse commonResponse);
    }

    public interface DeleteReservationCallBack {
        void onSuccess(CommonResponse commonResponse);

        void onError(CommonResponse commonResponse);
    }
}