package com.example.company.sabborah.views;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.company.sabborah.R;
import com.example.company.sabborah.adapters.TimeSlotAdapter;
import com.example.company.sabborah.models.TimeSlot;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarUtils;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.format.TitleFormatter;
import com.prolificinteractive.materialcalendarview.format.WeekDayFormatter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;

public class SplashFragment extends BaseFragment {
    @BindView(R.id.calendar)
    MaterialCalendarView calendarView;
    @BindView(R.id.timeSlotLV)
    ListView timeSlotLV;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = super.onCreateView(inflater, container, savedInstanceState);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String[] timeSlots = getResources().getStringArray(R.array.timeSlots);
        List<TimeSlot> timeSlotList = getTimeSlots(Arrays.asList(timeSlots));
        TimeSlotAdapter timeSlotAdapter = new TimeSlotAdapter(getActivity(), timeSlotList);
        calendarView.setSelectedDate(new Date());
        calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {

            }
        });
        timeSlotLV.setAdapter(timeSlotAdapter);
        Locale locale = new Locale("ar");
        Locale locale2 = new Locale("en");
        DateFormat dateFormat1 = new SimpleDateFormat("EEE", locale);
        DateFormat dateFormat = new SimpleDateFormat("LLLL yyyy", locale);
        WeekDayFormatter formatter = new WeekDayFormatter() {
            @Override
            public CharSequence format(int dayOfWeek) {
                return dateFormat1.format(Calendar.getInstance().getFirstDayOfWeek());
            }
        };

        TitleFormatter titleFormatter = new TitleFormatter() {
            @Override
            public CharSequence format(CalendarDay day) {
                return dateFormat.format(day.getDate());
            }
        };
        // calendarView.setWeekDayFormatter(formatter);
//        calendarView.setTitleFormatter(titleFormatter);
    }

    private List<TimeSlot> getTimeSlots(List<String> timeSlotString) {
        TimeSlot timeSlot = null;
        List<TimeSlot> timeSlotList = new ArrayList<TimeSlot>();
        int id = 0;
        for (String timeSlots : timeSlotString) {
            timeSlot = new TimeSlot(id, timeSlots, false);
            timeSlotList.add(timeSlot);
            id++;
        }
        return timeSlotList;
    }

    public void addAvailability(View view) {

    }

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_splash;
    }
}
