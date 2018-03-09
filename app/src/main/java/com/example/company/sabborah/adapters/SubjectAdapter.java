package com.example.company.sabborah.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.TextView;

import com.example.company.sabborah.R;
import com.example.company.sabborah.responses.tutor.Level;
import com.example.company.sabborah.responses.tutor.Subject;

import java.util.List;

/**
 * Created by Mohamed Sayed on 1/29/2018.
 */

public class SubjectAdapter extends ArrayAdapter<Subject> {
    public SubjectAdapter(Context ctx, List<Subject> subjects) {

        super(ctx, R.layout.subject_layout,R.id.subjectTV, subjects);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Subject subject = getItem(position);

        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.subject_layout, parent, false);

        TextView nameTV = (TextView) convertView.findViewById(R.id.subjectTV);
        nameTV.setText(subject.getName());
        return convertView;
    }
}
