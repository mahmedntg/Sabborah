package com.example.company.sabborah.views;


import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.example.company.sabborah.R;
import com.example.company.sabborah.adapters.MyLevelAdapter;
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
    MyLevelAdapter myGradeAdapter;
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
        myGradeAdapter = new MyLevelAdapter(grades);
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
