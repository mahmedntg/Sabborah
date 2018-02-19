package com.example.company.sabborah.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.company.sabborah.R;
import com.example.company.sabborah.models.Country;
import com.example.company.sabborah.models.TimeSlot;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Mohamed Sayed on 1/29/2018.
 */

public class TimeSlotAdapter extends ArrayAdapter<TimeSlot> {

    public TimeSlotAdapter(Context ctx, List<TimeSlot> timeSlots) {
        super(ctx, 0, timeSlots);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        TimeSlot timeSlot = getItem(position);
        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.time_slot_layout, parent, false);
        TimeSlotHolder timeSlotHolder = new TimeSlotHolder(convertView);
        timeSlotHolder.setHolder(timeSlot);
        timeSlotHolder.timeSlotTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeSlot.setChecked(timeSlot.isChecked() ? false : true);
                notifyDataSetChanged();
            }
        });
        return convertView;
    }

    public class TimeSlotHolder {
        @BindView(R.id.timeSlotTV)
        CheckedTextView timeSlotTV;

        public TimeSlotHolder(View view) {
            ButterKnife.bind(this, view);
        }

        void setHolder(TimeSlot timeSlot) {
            timeSlotTV.setText(timeSlot.getName());
            timeSlotTV.setChecked(timeSlot.isChecked());
        }

    }
}
