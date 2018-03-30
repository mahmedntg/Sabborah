package com.example.company.sabborah.views;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.company.sabborah.R;
import com.example.company.sabborah.adapters.GradeAdapter;
import com.example.company.sabborah.adapters.LevelAdapter;
import com.example.company.sabborah.adapters.SubjectAdapter;
import com.example.company.sabborah.adapters.TimeSlotAdapter;
import com.example.company.sabborah.models.Country;
import com.example.company.sabborah.models.TimeSlot;
import com.example.company.sabborah.responses.tutor.Grade;
import com.example.company.sabborah.responses.tutor.Level;
import com.example.company.sabborah.responses.tutor.Subject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Mohamed Sayed on 2/25/2018.
 */

public class DFragment extends DialogFragment {
    private List<Level> levelList;
    private Spinner levelSpinner;
    private Spinner gradeSpinner;
    private Spinner subjectSpinner;
    private TimeSlot timeSlot;
    @BindView(R.id.groupPriceTV)
    TextView groupPriceTV;
    int groupMax = 5;
    int position;
    private static int reservationId = -1;
    private List<TimeSlot> timeSlots;

    public void setLevelList(List<Level> levelList, List<TimeSlot> timeSlots, TimeSlot timeSlot) {
        this.levelList = levelList;
        this.timeSlots = timeSlots;
        this.timeSlot = timeSlot;
    }

    public void setTimeSlot(TimeSlot timeSlot) {
        this.timeSlot = timeSlot;
    }

    private boolean isTwoTimeSlotsCheckedTogether(int timeSlotId, int position) {
        TimeSlot timeSlot1 = new TimeSlot();
        TimeSlot timeSlot2 = new TimeSlot();
        if (position == 0) {
            timeSlot2 = timeSlots.get(position + 1);
        } else if (position == 47) {
            timeSlot1 = timeSlots.get(position - 1);
        } else {
            timeSlot1 = timeSlots.get(position - 1);
            timeSlot2 = timeSlots.get(position + 1);
        }
        if (timeSlot1.isChecked() || timeSlot2.isChecked()) {
            return true;
        }
        return false;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.level_dialog_layout, container,
                false);
        ButterKnife.bind(this, view);
        LevelAdapter levelAdapter = new LevelAdapter(getActivity(), levelList);
        levelSpinner = (Spinner) view.findViewById(R.id.levelSpinnerId);
        gradeSpinner = (Spinner) view.findViewById(R.id.gradeSpinnerId);
        subjectSpinner = (Spinner) view.findViewById(R.id.subjectSpinnerId);

        Button okBTN = (Button) view.findViewById(R.id.dialogButtonOK);
        Button cancelBTN = (Button) view.findViewById(R.id.dialogButtonCancel);

        okBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Subject subject = (Subject) subjectSpinner.getSelectedItem();
                timeSlot.setSubjectId(subject.getId());
                timeSlot.setGroupMax(groupMax);
                timeSlot.setReservationId(reservationId);
                reservationId--;
                SplashFragment.notifyAdapter(timeSlot);
                getDialog().dismiss();
            }
        });
        cancelBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });
        levelSpinner.setAdapter(levelAdapter);
        levelSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                GradeAdapter gradeAdapter = new GradeAdapter(getActivity(), levelList.get(position).getGrades());
                gradeSpinner.setAdapter(gradeAdapter);
                gradeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        SubjectAdapter subjectAdapter = new SubjectAdapter(getActivity(), ((Grade) parent.getItemAtPosition(position)).getSubjects());
                        subjectSpinner.setAdapter(subjectAdapter);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        getDialog().setTitle("Add Subject");
        // Do something else
        return view;
    }

    @OnClick(R.id.groupPlusBTN)
    public void addGroupPrice(View view) {
        groupMax = Integer.parseInt(groupPriceTV.getText().toString());
        if (groupMax < 11) {
            groupPriceTV.setText(String.valueOf(groupMax + 1));
        }
    }

    @OnClick(R.id.groupMinusBTN)
    public void minusGroupPrice(View view) {
        groupMax = Integer.parseInt(groupPriceTV.getText().toString());
        if (groupMax > 5) {
            groupPriceTV.setText(String.valueOf(groupMax - 1));
        }
    }
}
