package com.example.company.sabborah.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.Checkable;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.company.sabborah.R;
import com.example.company.sabborah.responses.tutor.Subject;
import com.thoughtbot.expandablecheckrecyclerview.CheckableChildRecyclerViewAdapter;
import com.thoughtbot.expandablecheckrecyclerview.models.CheckedExpandableGroup;
import com.thoughtbot.expandablecheckrecyclerview.viewholders.CheckableChildViewHolder;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.view.animation.Animation.RELATIVE_TO_SELF;

/**
 * Created by Mohamed Sayed on 2/3/2018.
 */

public class MyGradeAdapterOld extends CheckableChildRecyclerViewAdapter<GradeViewHolder, SubjectViewHolder> {
    public MyGradeAdapterOld(List<? extends CheckedExpandableGroup> groups) {
        super(groups);
    }

    @Override
    public GradeViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grade_layout, parent, false);
        return new GradeViewHolder(view);

    }

    @Override
    public void onBindGroupViewHolder(GradeViewHolder holder, int flatPosition, ExpandableGroup group) {
        holder.setGradeName(group);
    }


    @Override
    public SubjectViewHolder onCreateCheckChildViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.subject_layout, parent, false);
        return new SubjectViewHolder(view);
    }

    @Override
    public void onBindCheckChildViewHolder(final SubjectViewHolder holder, int flatPosition, final CheckedExpandableGroup group, final int childIndex) {
        final Subject subject = (Subject) group.getItems().get(childIndex);
        holder.setSubjectName(subject);
        holder.itemView.setTag(childIndex);
        holder.singleMinusBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // holder.minusSinglePrice(v, childIndex);
                ((Subject) group.getItems().get(childIndex)).setSingleRate(1);
                notifyItemChanged(childIndex);
                holder.singlePriceTV.setText("1");
            }
        });
        holder.groupMinusBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.minusGroupPrice(v, childIndex);
                notifyItemChanged(childIndex);
            }
        });
        holder.singlePlusBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.addSinglePrice(v, childIndex);
                notifyItemChanged(childIndex);
            }
        });
        holder.groupPlusBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.addGroupPrice(v, childIndex);
                notifyItemChanged(childIndex);
            }
        });

    }

    public void getData() {

    }
}


class GradeViewHolder extends GroupViewHolder {
    private TextView gradeName;
    private ImageView arrow;

    public GradeViewHolder(View itemView) {
        super(itemView);
        gradeName = itemView.findViewById(R.id.gradeTV);
        arrow = (ImageView) itemView.findViewById(R.id.grad_arrow);
    }

    public void setGradeName(ExpandableGroup group) {
        gradeName.setText(group.getTitle());
    }

    @Override
    public void expand() {
        animateExpand();
    }

    @Override
    public void collapse() {
        animateCollapse();
    }

    private void animateExpand() {
        RotateAnimation rotate =
                new RotateAnimation(360, 180, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(300);
        rotate.setFillAfter(true);
        arrow.setAnimation(rotate);
    }

    private void animateCollapse() {
        RotateAnimation rotate =
                new RotateAnimation(180, 360, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(300);
        rotate.setFillAfter(true);
        arrow.setAnimation(rotate);
    }
}

class SubjectViewHolder extends CheckableChildViewHolder {
    @BindView(R.id.subjectTV)
    CheckedTextView checkedSubjectName;
    @BindView(R.id.groupPriceTV)
    TextView groupPriceTV;
    @BindView(R.id.singlePriceTV)
    TextView singlePriceTV;
    @BindView(R.id.subjectPriceLinearLayout)
    LinearLayout linearLayout;
    @BindView(R.id.singleMinusBTN)
    Button singleMinusBTN;
    @BindView(R.id.groupMinusBTN)
    Button groupMinusBTN;
    @BindView(R.id.singlePlusBTN)
    Button singlePlusBTN;
    @BindView(R.id.groupPlusBTN)
    Button groupPlusBTN;

    public SubjectViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public Checkable getCheckable() {
        return checkedSubjectName;
    }

    public void setSinglePriceTV(double value) {
        singlePriceTV.setText(String.valueOf(value));
    }

    public void setSubjectName(Subject subject) {
        checkedSubjectName.setText(subject.getName());
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
    public void onBindViewHolder(int flatPos, boolean checked) {
        super.onBindViewHolder(flatPos, checked);
    }

}


