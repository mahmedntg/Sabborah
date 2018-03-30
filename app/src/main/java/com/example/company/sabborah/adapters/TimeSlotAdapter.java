package com.example.company.sabborah.adapters;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;

import com.example.company.sabborah.R;
import com.example.company.sabborah.models.TimeSlot;
import com.example.company.sabborah.responses.tutor.Level;
import com.example.company.sabborah.views.SplashFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Mohamed Sayed on 1/29/2018.
 */

public class TimeSlotAdapter extends ArrayAdapter<TimeSlot> {
    private List<TimeSlot> timeSlots;
    private List<Level> levelList;
    private Context context;
    private FragmentManager fragmentManager;

    public TimeSlotAdapter(Context ctx, List<TimeSlot> timeSlots) {
        super(ctx, 0, timeSlots);
        this.timeSlots = timeSlots;
        this.context = ctx;
    }

    public void refreshList(TimeSlot timeSlot) {
        int position = getPosition(timeSlot);
        timeSlots.set(position, timeSlot);
        notifyDataSetChanged();
    }

    public void setLevelList(List<Level> levelList) {
        this.levelList = levelList;
    }


    public void setFragmentManager(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        TimeSlot timeSlot = getItem(position);
        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.time_slot_layout, parent, false);
        TimeSlotHolder timeSlotHolder = new TimeSlotHolder(convertView);
        timeSlot.setEnabled((timeSlot.getReservationId() != 0 && timeSlot.getAvailabilityId() != 0) ? false : true);
        timeSlotHolder.setHolder(timeSlot);
        timeSlotHolder.timeSlotTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeSlot.setChecked(timeSlot.isChecked() ? false : true);
                    if (timeSlot.isChecked()) {
                        if (isTwoTimeSlotsCheckedTogether(timeSlot.getId(), position)) {
                            if (position != 0 && timeSlots.get(position - 1).getSubjectId() != 0) {
                                timeSlot.setReservationId(timeSlots.get(position - 1).getReservationId());
                                timeSlot.setSubjectId(timeSlots.get(position - 1).getSubjectId());
                                timeSlot.setGroupMax(timeSlots.get(position - 1).getGroupMax());
                            } else if (position != 47 && timeSlots.get(position + 1).getSubjectId() != 0) {
                                timeSlot.setReservationId(timeSlots.get(position - 1).getReservationId());
                                timeSlot.setSubjectId(timeSlots.get(position + 1).getSubjectId());
                                timeSlot.setGroupMax(timeSlots.get(position + 1).getGroupMax());
                            }

                        }
                    }
                SplashFragment.setTimeSlot(timeSlot);
                notifyDataSetChanged();
            }
        });
        return convertView;
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
        if ((timeSlot1.getGroupMax() != 0 || timeSlot2.getGroupMax() != 0) && (timeSlot1.isChecked() || timeSlot2.isChecked())) {
            return true;
        }
        return false;
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
            timeSlotTV.setEnabled(timeSlot.isEnabled());
        }

    }

}
