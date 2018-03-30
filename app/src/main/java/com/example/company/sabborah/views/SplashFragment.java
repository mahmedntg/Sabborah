package com.example.company.sabborah.views;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.example.company.sabborah.R;
import com.example.company.sabborah.adapters.TimeSlotAdapter;
import com.example.company.sabborah.models.SubjectAvailability;
import com.example.company.sabborah.models.TimeSlot;
import com.example.company.sabborah.presenters.TutorContract;
import com.example.company.sabborah.presenters.TutorPresenter;
import com.example.company.sabborah.responses.CommonResponse;
import com.example.company.sabborah.responses.tutor.Grade;
import com.example.company.sabborah.responses.tutor.Level;
import com.example.company.sabborah.responses.tutor.Subject;
import com.example.company.sabborah.responses.tutorAvailability.AvailabilityDetails;
import com.example.company.sabborah.responses.tutorAvailability.AvailabilitySubject;
import com.example.company.sabborah.responses.tutorAvailability.TutorReservation;
import com.example.company.sabborah.services.APIClient;
import com.example.company.sabborah.utils.DateUtil;
import com.example.company.sabborah.utils.MyAlertDialog;
import com.example.company.sabborah.utils.MySharedPreferences;
import com.example.company.sabborah.utils.TokenUtil;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarUtils;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.format.TitleFormatter;
import com.prolificinteractive.materialcalendarview.format.WeekDayFormatter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class SplashFragment extends BaseFragment implements TutorContract.View {
    @BindView(R.id.calendar)
    MaterialCalendarView calendarView;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.timeSlotLV)
    ListView timeSlotLV;
    TutorPresenter presenter;
    AlertDialog alertDialog;
    List<Level> levelList = new ArrayList<>();
    public Spinner levelSpinner;
    public Spinner gradeSpinner;
    public Spinner subjectSpinner;
    @BindView(R.id.pb_loading)
    ProgressBar pbLoading;
    static TimeSlotAdapter timeSlotAdapter;
    static List<TimeSlot> timeSlotList;
    private String reservationDate;
    List<AvailabilitySubject> availabilitySubjects;
    Map<String, List<AvailabilityDetails>> availabilityDetails;
    static TimeSlot timeSlot;

    @Override
    protected void initializePresenter() {
        presenter = TutorPresenter.getInstance();
        super.presenter = presenter;
        presenter.setView(this);
        alertDialog = MyAlertDialog.createAlertDialog(getActivity(), "");
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_splash;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DFragment dFragment = new DFragment();
                dFragment.setLevelList(levelList, timeSlotList, timeSlot);
                dFragment.show(fragmentManager, "Dialog");
            }
        });
        super.onViewCreated(view, savedInstanceState);

    }

    public static void setTimeSlot(TimeSlot timeSlot1) {
        timeSlot = timeSlot1;
    }

    @Override
    public void onResume() {
        reservationDate = DateUtil.getSimpleDate(new Date());
        populateTimeSlots();
        presenter.getTutorInformation("b83a5d5c-50bc-4437-db96-08d4fe661033");
        super.onResume();
    }

    private void populateTimeSlots() {
        String[] timeSlots = getResources().getStringArray(R.array.timeSlots);
        timeSlotList = getTimeSlots(Arrays.asList(timeSlots));
        timeSlotAdapter = new TimeSlotAdapter(getActivity(), timeSlotList);
        calendarView.setSelectedDate(new Date());
        calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                reservationDate = DateUtil.getSimpleDate(date.getDate());

                updateTimeSlots();
            }
        });
        timeSlotLV.setAdapter(timeSlotAdapter);
        Locale locale = new Locale("ar");
        Locale locale2 = new Locale("en");
        DateFormat dateFormat1 = new SimpleDateFormat("EEE", locale);
        DateFormat dateFormat = new SimpleDateFormat("LLLL yyyy", locale);
        WeekDayFormatter formatter = new WeekDayFormatter() {
            @Override
            public CharSequence format(int dayOfWeek) {
                return dateFormat1.format(Calendar.getInstance().getFirstDayOfWeek());
            }
        };

        TitleFormatter titleFormatter = new TitleFormatter() {
            @Override
            public CharSequence format(CalendarDay day) {
                return dateFormat.format(day.getDate());
            }
        };
        // calendarView.setWeekDayFormatter(formatter);
//        calendarView.setTitleFormatter(titleFormatter);

    }

    public static void notifyAdapter(TimeSlot timeSlot) {
        int position1 = timeSlot.getId();
        int position2 = timeSlot.getId();
        timeSlotList.set(position1, timeSlot);
        while (position1 != 0) {
            if (timeSlotList.get(position1 - 1).isChecked() && timeSlotList.get(position1 - 1).getReservationId() == 0L) {
                TimeSlot firstTimeSlot = timeSlotList.get(position1 - 1);
                firstTimeSlot.setGroupMax(timeSlot.getGroupMax());
                firstTimeSlot.setReservationId(timeSlot.getReservationId());
                firstTimeSlot.setSubjectId(timeSlot.getSubjectId());
                timeSlotList.set(position1 - 1, firstTimeSlot);
                position1--;
            } else {
                break;
            }
        }
        while (position2 != 47) {
            if (timeSlotList.get(position2 + 1).isChecked() && timeSlotList.get(position1 - 1).getReservationId() == 0L) {
                TimeSlot firstTimeSlot = timeSlotList.get(position2 + 1);
                firstTimeSlot.setGroupMax(timeSlot.getGroupMax());
                firstTimeSlot.setReservationId(timeSlot.getReservationId());
                firstTimeSlot.setSubjectId(timeSlot.getSubjectId());
                timeSlotList.set(position2 + 1, firstTimeSlot);
                position2++;
            } else {
                break;
            }
        }
       /*
        TimeSlot firstTimeSlot = timeSlotList.get(position - 1);
        TimeSlot secondTimeSlot = timeSlotList.get(position + 1);
        if (firstTimeSlot.isChecked()) {
            firstTimeSlot.setReservationId(timeSlot.getReservationId());
            firstTimeSlot.setSubjectId(timeSlot.getSubjectId());
            firstTimeSlot.setGroupMax(timeSlot.getGroupMax());
            timeSlotList.set(position - 1, firstTimeSlot);
        }
        if (secondTimeSlot.isChecked()) {
            secondTimeSlot.setReservationId(timeSlot.getReservationId());
            secondTimeSlot.setSubjectId(timeSlot.getSubjectId());
            secondTimeSlot.setGroupMax(timeSlot.getGroupMax());
            timeSlotList.set(position + 1, secondTimeSlot);
        }*/
        timeSlotAdapter.notifyDataSetChanged();
    }

    private List<TimeSlot> getTimeSlots(List<String> timeSlotString) {
        TimeSlot timeSlot = null;
        List<TimeSlot> timeSlotList = new ArrayList<TimeSlot>();
        int id = 0;
        for (String timeSlots : timeSlotString) {
            timeSlot = new TimeSlot(id, timeSlots, false);
            timeSlotList.add(timeSlot);
            id++;
        }
        return timeSlotList;
    }

    @OnClick(R.id.addAvailability)
    public void addAvailability(View view) {
        String tutorId = TokenUtil.getReference().getUserId("eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1aWQiOiJmNTE0NTIxMi1kNmQxLTQxOTItODBkNi0wOGQ1NmZhZTI4MjIiLCJzdWIiOiJmaXJlYmFzZS1hZG1pbnNkay1yOGphekBmaXJlYmFzZS1teXR1dG9yYXBwLmlhbS5nc2VydmljZWFjY291bnQuY29tIiwiaXN0dXRvciI6IjEiLCJlbWFpbCI6Im1vaGFtZWRAbS5jb20iLCJuYmYiOjE1MTk2NDc1MjgsImV4cCI6MTUxOTY1MTEyOCwiaWF0IjoxNTE5NjQ3NTI4LCJpc3MiOiJmaXJlYmFzZS1hZG1pbnNkay1yOGphekBmaXJlYmFzZS1teXR1dG9yYXBwLmlhbS5nc2VydmljZWFjY291bnQuY29tIiwiYXVkIjoiaHR0cHM6Ly9pZGVudGl0eXRvb2xraXQuZ29vZ2xlYXBpcy5jb20vZ29vZ2xlLmlkZW50aXR5LmlkZW50aXR5dG9vbGtpdC52MS5JZGVudGl0eVRvb2xraXQifQ.jmM_NDf5kjzm3bIUtZrD9clhhI6pmhj7Q-YY5w7pSNo6KRLTN6SwfFjDwruOxumRKSCNpPQfH0UBIZcYCm_ifKCKAMP4MhuV1sFJn8zU_3RSYP1dtH9VinP-ri4MerQPuxQjJKupBVKhIlomMHYtvyTdeHuQM7tW4LGME6WdQChlbNmUfcF9E8p-ZZbfRa-i5DmOlwqDHtkvV6DSTufP5ht2COkV3iOi8AQXWxhJokwunqWFJ_CMHlDtv7VNgtkcgtybLkWhvRTIpLbqK98ceDH_l4dS7r_mqaWYgzjiZg0OrNP4FWWG8LKuWyHi-p4JWryh-MyxUzfQG_GHCw0K_A").getUid();
        List<SubjectAvailability> subjectAvailabilities = new ArrayList<SubjectAvailability>();
        SubjectAvailability subjectAvailability = null;
        int index = 0;
        for (TimeSlot timeSlot : timeSlotList) {
            // timeSlot = checkIfTimeSlotSingle(timeSlot);
            if ((timeSlot.isChecked() && timeSlot.getAvailabilityId() == 0) || (!timeSlot.isChecked() && timeSlot.getAvailabilityId() != 0)) {
                subjectAvailability = new SubjectAvailability();
                subjectAvailability.setDate(reservationDate);
                subjectAvailability.setGroupMax(timeSlot.getGroupMax());
                subjectAvailability.setReservationId(timeSlot.getReservationId());
                subjectAvailability.setSubjectId(timeSlot.getSubjectId());
                subjectAvailability.setTimeslot(timeSlot.getId());
                subjectAvailability.setAvailabilityId(timeSlot.getAvailabilityId() != 0L ? timeSlot.getAvailabilityId() : null);
                subjectAvailability.setTutorId("b83a5d5c-50bc-4437-db96-08d4fe661033");
                subjectAvailabilities.add(subjectAvailability);
            }
        }
        if (subjectAvailabilities.isEmpty()) {
            onAddAvailabilityFailure(APIClient.getInstance().getDefaultErrorBody("Nothing to add!"));
        }
        presenter.addAvailability("b83a5d5c-50bc-4437-db96-08d4fe661033", subjectAvailabilities);
    }

    @Override
    public void onGetLevelsSuccess(CommonResponse response) {
        levelList = response.getResult().getLevels();
        updateLevelList();
        timeSlotAdapter.setLevelList(levelList);
        timeSlotAdapter.setFragmentManager(fragmentManager);
    }

    @Override
    public void onGetLevelsFailure(CommonResponse response) {
        alertDialog.setMessage(response.getErrors().get(0));
        alertDialog.show();
    }

    @Override
    public void onAddAvailabilitySuccess(CommonResponse response) {
        alertDialog.setMessage(response.getMessage());
        alertDialog.show();
        presenter.getTutorInformation("b83a5d5c-50bc-4437-db96-08d4fe661033");
    }

    @Override
    public void onAddAvailabilityFailure(CommonResponse response) {
        alertDialog.setMessage(response.getErrors().get(0));
        alertDialog.show();
    }

    @Override
    public void setLoaderVisibility(boolean isVisible) {
        pbLoading.setVisibility(isVisible ? VISIBLE : GONE);

    }

    @Override
    public void onGetTutorInformationSuccess(TutorReservation tutorReservation) {
        availabilityDetails = tutorReservation.getAvailability();
        availabilitySubjects = tutorReservation.getSubjects();
        updateTimeSlots();
        presenter.getLevels();
    }

    private void updateTimeSlots() {
        for (int i = 0; i < timeSlotList.size(); i++) {
            timeSlotList.get(i).setChecked(false);
            timeSlotList.get(i).setAvailabilityId(0);
            timeSlotList.get(i).setSubjectId(0);
            timeSlotList.get(i).setReservationId(0);
            timeSlotList.get(i).setGroupMax(0);

        }
        List<AvailabilityDetails> details = new ArrayList<AvailabilityDetails>();
        if (availabilityDetails.containsKey(reservationDate)) {
            details = availabilityDetails.get(reservationDate);
        }
        for (int i = 0; i < timeSlotList.size(); i++) {
            for (AvailabilityDetails availability : details) {
                TimeSlot timeSlot = timeSlotList.get(i);
                if (availability.getTimeSlot() == (timeSlot.getId())) {
                    timeSlot.setChecked(true);
                    timeSlot.setAvailabilityId(availability.getId());
                    timeSlot.setSubjectId(availability.getSubjectId());
                    timeSlot.setGroupMax(availability.getGroupMax());
                    timeSlot.setReservationId(availability.getReservationId());
                    timeSlotList.set(i, timeSlot);
                    break;
                }
            }
        }

        timeSlotAdapter.notifyDataSetChanged();

    }

    private void updateLevelList() {
        List<Integer> levelIds = new ArrayList<>();
        List<Integer> gradeIds = new ArrayList<>();
        List<Integer> subjectIds = new ArrayList<>();
        for (AvailabilitySubject subject : availabilitySubjects) {
            levelIds.add(subject.getLevelId());
            gradeIds.add(subject.getGradeId());
            subjectIds.add(subject.getSubjectId());
        }
        for (Iterator<Level> levelIterator = levelList.iterator(); levelIterator.hasNext(); ) {
            Level level = levelIterator.next();
            if (!levelIds.contains(level.getId())) {
                levelIterator.remove();
            } else {
                for (Iterator<Grade> gradeIterator = level.getGrades().iterator(); gradeIterator.hasNext(); ) {
                    Grade grade = gradeIterator.next();
                    if (!gradeIds.contains(grade.getId())) {
                        gradeIterator.remove();
                    } else {
                        for (Iterator<Subject> subjectIterator = grade.getSubjects().iterator(); subjectIterator.hasNext(); ) {
                            Subject subject = subjectIterator.next();
                            if (!subjectIds.contains(subject.getId())) {
                                subjectIterator.remove();
                            }

                        }
                    }
                }
            }
        }

    }

    private TimeSlot checkIfTimeSlotSingle(TimeSlot timeSlot) {
        int position = timeSlot.getId();
        if (timeSlot.isChecked()) {
            TimeSlot timeSlot1 = new TimeSlot();
            TimeSlot timeSlot2 = new TimeSlot();
            if (position == 0) {
                timeSlot2 = timeSlotList.get(position + 1);
            } else if (position == 47) {
                timeSlot1 = timeSlotList.get(position - 1);
            } else {
                timeSlot1 = timeSlotList.get(position - 1);
                timeSlot2 = timeSlotList.get(position + 1);
            }
            if (!timeSlot1.isChecked() && !timeSlot2.isChecked()) {
                timeSlot.setReservationId(0);
                timeSlot.setAvailabilityId(0);
            }
        }
        return timeSlot;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.unSubscribe();
    }

}
