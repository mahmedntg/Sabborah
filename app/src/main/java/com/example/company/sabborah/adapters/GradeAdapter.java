package com.example.company.sabborah.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.company.sabborah.R;
import com.example.company.sabborah.responses.tutor.Grade;
import com.example.company.sabborah.responses.tutor.Level;

import java.util.List;

/**
 * Created by Mohamed Sayed on 1/29/2018.
 */

public class GradeAdapter extends ArrayAdapter<Grade> {
    public GradeAdapter(Context ctx, List<Grade> grades) {

        super(ctx, R.layout.grade_layout    , R.id.gradeTV, grades);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Grade grade = getItem(position);

        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.grade_layout, parent, false);

        TextView nameTV = (TextView) convertView.findViewById(R.id.gradeTV);
        nameTV.setText(grade.getName());
        return convertView;
    }
}
