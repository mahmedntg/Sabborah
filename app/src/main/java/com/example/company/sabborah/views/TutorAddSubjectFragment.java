package com.example.company.sabborah.views;


import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ExpandableListView;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.example.company.sabborah.R;
import com.example.company.sabborah.adapters.MyGradeAdapter;
import com.example.company.sabborah.presenters.TutorContract;
import com.example.company.sabborah.presenters.TutorPresenter;
import com.example.company.sabborah.responses.CommonResponse;
import com.example.company.sabborah.responses.tutor.Grade;
import com.example.company.sabborah.responses.tutor.Level;
import com.example.company.sabborah.responses.tutor.Subject;
import com.example.company.sabborah.responses.tutorAvailability.TutorReservation;
import com.example.company.sabborah.utils.MyAlertDialog;
import com.example.company.sabborah.utils.MySharedPreferences;
import com.example.company.sabborah.utils.TokenUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;


public class TutorAddSubjectFragment extends BaseFragment implements TutorContract.View {
    MyGradeAdapter myGradeAdapter;
    @BindView(R.id.gradeListView)
    ExpandableListView gradeListView;
    private List<Level> levelList = new ArrayList<>();
    TutorPresenter tutorPresenter;
    private AlertDialog alertDialog;
    @BindView(R.id.pb_loading)
    ProgressBar pbLoading;
    @BindView(R.id.levelSpinnerId)
    public Spinner levelSpinner;
    List<Grade> grades = new ArrayList<Grade>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = super.onCreateView(inflater, container, savedInstanceState);
        tutorPresenter = TutorPresenter.getInstance();
        tutorPresenter.setView(this);
        alertDialog = MyAlertDialog.createAlertDialog(getActivity(), "");
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        populateSpinner();
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_tutor_add_subject;
    }

    @Override
    public void onResume() {
        tutorPresenter.getLevels();
        tutorPresenter.getTutorInformation(TokenUtil.getReference().getUserId("eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1aWQiOiJmNTE0NTIxMi1kNmQxLTQxOTItODBkNi0wOGQ1NmZhZTI4MjIiLCJzdWIiOiJmaXJlYmFzZS1hZG1pbnNkay1yOGphekBmaXJlYmFzZS1teXR1dG9yYXBwLmlhbS5nc2VydmljZWFjY291bnQuY29tIiwiaXN0dXRvciI6IjEiLCJlbWFpbCI6Im1vaGFtZWRAbS5jb20iLCJuYmYiOjE1MTg5NzY5NDYsImV4cCI6MTUxODk4MDU0NiwiaWF0IjoxNTE4OTc2OTQ2LCJpc3MiOiJmaXJlYmFzZS1hZG1pbnNkay1yOGphekBmaXJlYmFzZS1teXR1dG9yYXBwLmlhbS5nc2VydmljZWFjY291bnQuY29tIiwiYXVkIjoiaHR0cHM6Ly9pZGVudGl0eXRvb2xraXQuZ29vZ2xlYXBpcy5jb20vZ29vZ2xlLmlkZW50aXR5LmlkZW50aXR5dG9vbGtpdC52MS5JZGVudGl0eVRvb2xraXQifQ.CVonmmFk40WDGD7TCjDjEITK39mdaWotCDf2cobzRImRR76D56G8iFCq7ZhCSVvVUkugNw97qoTbpbDZfHkqtjGWxQKRUrlqqTzJ8Sj_2v7ywEHJ6pnEXJEYLor7-RSSKMnm4mShRmG2pNSMedcWY9nbHBL-D08o7HR5V75-4Fyn8_abb2baMk5xm_V_ckOc--tvZdNDyfWmluW2sIzL6aTTJKCph036skG2cJrljq-hGVlPf4RmKiJnh6KDd0erzKmQec1AqcJGV1JyUlfTTSlEY8N8Y75kE7BppShvy7TMadL6Modh7lfC_7SDpU8lculBSOkZTxmmiZDgN9i9Hw").getUid());
        super.onResume();
    }

    @Override
    public void onGetLevelsSuccess(CommonResponse response) {
        levelList = response.getResult().getLevels();
        ArrayAdapter<Level> adapter = new ArrayAdapter<Level>(
                getActivity(), android.R.layout.simple_spinner_dropdown_item, levelList);
        levelSpinner.setAdapter(adapter);
    }

    private void populateGrades(int index) {
        grades.clear();
        for (Grade grade : levelList.get(index).getGrades()) {
            grades.add(grade);
        }
        myGradeAdapter = new MyGradeAdapter(grades);
        gradeListView.setAdapter(myGradeAdapter);
    }

    @Override
    public void onGetLevelsFailure(CommonResponse response) {
        alertDialog.setMessage(response.getErrors().get(0));
        alertDialog.show();
    }

    @Override
    public void onAddSubjectSuccess(CommonResponse response) {
        alertDialog = MyAlertDialog.createAlertDialog(getActivity(), "");
        alertDialog.setMessage(response.getMessage());
        alertDialog.show();
    }

    @Override
    public void onAddSubjectFailure(CommonResponse response) {
        alertDialog = MyAlertDialog.createAlertDialog(getActivity(), "");
        alertDialog.setMessage(response.getErrors().get(0));
        alertDialog.show();
    }

    @Override
    public void setLoaderVisibility(boolean isVisible) {
        pbLoading.setVisibility(isVisible ? VISIBLE : GONE);
    }

    @Override
    public void onGetTutorInformationSuccess(TutorReservation tutorReservation) {
        tutorReservation.getAvailability();
    }

    @Override
    public void onDestroy() {
        tutorPresenter.unSubscribe();
        super.onDestroy();
    }


    private void populateSpinner() {
        levelSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                populateGrades(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }

    @OnClick(R.id.addSubjectBTN)
    public void addSubject(View view) {
        List<Subject> selectedSubjects = new ArrayList<>();
        for (int i = 0; i < grades.size(); i++) {
            for (Subject subject : grades.get(i).getSubjects()) {
                if (subject.isChecked()) {
                    selectedSubjects.add(subject);
                }
            }
        }
        String token = MySharedPreferences.getReference(getActivity()).getToken();
        tutorPresenter.addSubject(TokenUtil.getReference().getUserId(token).getUid(), selectedSubjects);
    }

}
