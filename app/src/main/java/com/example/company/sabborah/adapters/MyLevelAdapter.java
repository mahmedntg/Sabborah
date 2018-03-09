package com.example.company.sabborah.adapters;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.company.sabborah.R;
import com.example.company.sabborah.responses.tutor.Grade;
import com.example.company.sabborah.responses.tutor.Subject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Mohamed Sayed on 2/3/2018.
 */

public class MyLevelAdapter extends BaseExpandableListAdapter {
    private List<Grade> gradeList;

    public MyLevelAdapter(List<Grade> gradeList) {
        this.gradeList = gradeList;
    }

    @Override
    public int getGroupCount() {
        return gradeList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return gradeList.get(groupPosition).getSubjects().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return gradeList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return gradeList.get(groupPosition).getSubjects().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View view, ViewGroup parent) {
        Grade grade = (Grade) getGroup(groupPosition);
        GradeHolder gradeHolder;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_level_layout, parent, false);
        gradeHolder = new GradeHolder(view);
        gradeHolder.setGradeName(grade);
        if (isExpanded) {
            //view.setBackgroundColor(ContextCompat.getColor(parent.getContext(), R.color.colorAccent));
            //gradeHolder.gradeName.setTextColor(ContextCompat.getColor(parent.getContext(), R.color.white));
            gradeHolder.arrow.setImageResource(R.drawable.ic_menu_min);
            //gradeHolder.arrow.setColorFilter(ContextCompat.getColor(parent.getContext(), R.color.white));
        }

        return view;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View view, ViewGroup parent) {
        final Subject subject = (Subject) getChild(groupPosition, childPosition);
        final SubjectHolder subjectHolder;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grade_subject_layout, parent, false);
        subjectHolder = new SubjectHolder(view, subject);
        subjectHolder.setSubject();
        gradeList.get(groupPosition).getSubjects().get(childPosition).setSingleRate(subject.getSingleRate());
        gradeList.get(groupPosition).getSubjects().get(childPosition).setGroupRate(subject.getGroupRate());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gradeList.get(groupPosition).getSubjects().get(childPosition).setChecked(subject.isChecked() ? false : true);
                notifyDataSetChanged();
            }
        });
        subjectHolder.singleMinusBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (subject.getSingleRate() > 5) {
                    gradeList.get(groupPosition).getSubjects().get(childPosition).setSingleRate(subject.getSingleRate() - 1.0);
                    notifyDataSetChanged();
                }
            }
        });
        subjectHolder.groupMinusBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (subject.getGroupRate() > 5) {
                    gradeList.get(groupPosition).getSubjects().get(childPosition).setGroupRate(subject.getGroupRate() - 1.0);
                    notifyDataSetChanged();
                }
            }
        });
        subjectHolder.singlePlusBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gradeList.get(groupPosition).getSubjects().get(childPosition).setSingleRate(subject.getSingleRate() + 1.0);
                notifyDataSetChanged();
            }
        });
        subjectHolder.groupPlusBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gradeList.get(groupPosition).getSubjects().get(childPosition).setGroupRate(subject.getGroupRate() + 1.0);
                notifyDataSetChanged();
            }
        });
        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}

class GradeHolder {
    @BindView(R.id.gradeTV)
    TextView gradeName;
    @BindView(R.id.grad_arrow)
    ImageView arrow;

    public GradeHolder(View view) {
        ButterKnife.bind(this, view);
    }

    public void setGradeName(Grade grade) {
        gradeName.setText(grade.getName());
    }

}

class SubjectHolder implements ExpandableListView.OnChildClickListener {
    @Nullable
    @BindView(R.id.subjectTV)
    CheckedTextView checkedSubjectName;
    @Nullable
    @BindView(R.id.groupPriceTV)
    TextView groupPriceTV;
    @Nullable
    @BindView(R.id.singlePriceTV)
    TextView singlePriceTV;
    @Nullable
    @BindView(R.id.subjectPriceLinearLayout)
    LinearLayout subjectPriceLinearLayout;
    @BindView(R.id.gradeRL)
    RelativeLayout gradeRL;
    @Nullable
    @BindView(R.id.singleMinusBTN)
    Button singleMinusBTN;
    @Nullable
    @BindView(R.id.groupMinusBTN)
    Button groupMinusBTN;
    @Nullable
    @BindView(R.id.singlePlusBTN)
    Button singlePlusBTN;
    @Nullable
    @BindView(R.id.groupPlusBTN)
    Button groupPlusBTN;
    private Subject subject;

    public SubjectHolder(View view, Subject subject) {
        ButterKnife.bind(this, view);
        this.subject = subject;
    }

    public void setSinglePriceTV() {
        singlePriceTV.setText(String.valueOf(subject.getSingleRate()));
    }

    public void setSubject() {
        checkedSubjectName.setText(subject.getName());
        checkedSubjectName.setChecked(subject.isChecked());
        subjectPriceLinearLayout.setVisibility(subject.isChecked() ? View.VISIBLE : View.GONE);
        if (subjectPriceLinearLayout.getVisibility() == View.GONE) {
            FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) gradeRL.getLayoutParams();
            params.height = 130;
            gradeRL.setLayoutParams(params);
        }
        singlePriceTV.setText(String.valueOf(subject.getSingleRate()));
        groupPriceTV.setText(String.valueOf(subject.getGroupRate()));
    }

    public void addGroupPrice(View view, int position) {
        double value = Double.parseDouble(groupPriceTV.getText().toString());
        groupPriceTV.setText(String.valueOf(value + 1));
    }

    public void addSinglePrice(View view, int position) {
        double value = Double.parseDouble(singlePriceTV.getText().toString());
        singlePriceTV.setText(String.valueOf(value + 1));

    }

    public void minusGroupPrice(View view, int position) {
        double value = Double.parseDouble(groupPriceTV.getText().toString());
        groupPriceTV.setText(String.valueOf(value - 1));
    }

    public void minusSinglePrice(View view, int position) {
        double value = Double.parseDouble(singlePriceTV.getText().toString());
        singlePriceTV.setText(String.valueOf(value - 1));
    }

    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
        return false;
    }
}

