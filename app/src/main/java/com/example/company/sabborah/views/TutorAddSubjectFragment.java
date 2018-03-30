package com.example.company.sabborah.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
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
import com.example.company.sabborah.presenters.TutorSubjectContract;
import com.example.company.sabborah.presenters.TutorSubjectPresenter;
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


public class TutorAddSubjectFragment extends BaseFragment implements TutorSubjectContract.View {
    MyLevelAdapter myGradeAdapter;
    @BindView(R.id.gradeListView)
    ExpandableListView gradeListView;
    private List<Level> levelList = new ArrayList<>();
    TutorSubjectPresenter presenter;
    private AlertDialog alertDialog;
    @BindView(R.id.pb_loading)
    ProgressBar pbLoading;
    @BindView(R.id.levelSpinnerId)
    public Spinner levelSpinner;
    List<Grade> grades = new ArrayList<Grade>();
    String tutorId = "b83a5d5c-50bc-4437-db96-08d4fe661033";


    @Override
    protected void initializePresenter() {
        presenter = TutorSubjectPresenter.getInstance();
        super.presenter = presenter;
        presenter.setView(this);
        alertDialog = MyAlertDialog.createAlertDialog(getActivity(), "");
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_tutor_add_subject;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        populateSpinner();
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onResume() {
        presenter.getLevels();
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
        presenter.addSubject(tutorId, selectedSubjects);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.unSubscribe();
    }


}
