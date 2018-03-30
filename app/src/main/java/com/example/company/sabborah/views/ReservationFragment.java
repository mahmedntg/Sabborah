package com.example.company.sabborah.views;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.company.sabborah.R;
import com.example.company.sabborah.adapters.ReservationAdapter;
import com.example.company.sabborah.adapters.listeners.ReservationListener;
import com.example.company.sabborah.models.TimeSlot;
import com.example.company.sabborah.presenters.ReservationContract;
import com.example.company.sabborah.presenters.ReservationPresenter;
import com.example.company.sabborah.presenters.TutorContract;
import com.example.company.sabborah.presenters.TutorPresenter;
import com.example.company.sabborah.responses.CommonResponse;
import com.example.company.sabborah.responses.tutorAvailability.ReservationDetails;
import com.example.company.sabborah.responses.tutorAvailability.TutorReservation;
import com.example.company.sabborah.utils.DateUtil;
import com.example.company.sabborah.utils.DividerItemDecoration;
import com.example.company.sabborah.utils.MyAlertDialog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.OnClick;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;


/**
 * A simple {@link Fragment} subclass.
 */
public class ReservationFragment extends BaseFragment implements ReservationContract.View {

    ReservationPresenter presenter;
    AlertDialog alertDialog;
    @BindView(R.id.pb_loading)
    ProgressBar pbLoading;
    @BindView(R.id.recyclerReservationId)
    RecyclerView recyclerView;
    private ReservationAdapter mAdapter;
    private Date reservationDate;
    List<ReservationDetails> reservationDetails;
    TutorReservation tutorReservation;
    List<TimeSlot> timeSlots;
    String tutorId = "b83a5d5c-50bc-4437-db96-08d4fe661033";
    @BindView(R.id.previousBTNId)
    Button previousBTN;
    @BindView(R.id.nextBTNId)
    Button nextBTN;

    @Override
    protected void initializePresenter() {
        presenter = ReservationPresenter.getInstance();
        super.presenter = presenter;
        presenter.setView(this);
        alertDialog = MyAlertDialog.createAlertDialog(getActivity(), "");
        timeSlots = getTimeSlots();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_reservation;
    }

    @Override
    public void onResume() {
        reservationDate = DateUtil.getCurrentDate();
        presenter.getTutorInformation(tutorId);
        super.onResume();
    }

    public void refreshData() {
        presenter.getTutorInformation(tutorId);
    }

    @Override
    public void setLoaderVisibility(boolean isVisible) {
        pbLoading.setVisibility(isVisible ? VISIBLE : GONE);
    }

    @Override
    public void onDeleteTutorReservationSuccess(CommonResponse commonResponse) {
        // alertDialog.setMessage(commonResponse.getMessage());
        // alertDialog.show();
    }

    @Override
    public void onDeleteTutorReservationFailed(CommonResponse commonResponse) {
        alertDialog.setMessage(commonResponse.getMessage());
        alertDialog.show();
    }

    @Override
    public void onGetTutorInformationSuccess(TutorReservation tutorReservation) {
        nextBTN.setBackgroundColor(Color.BLUE);
        this.tutorReservation = tutorReservation;
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        reservationDetails = getReservation(false);
        mAdapter = new ReservationAdapter(reservationDetails, reservationListener, true);
        recyclerView.setAdapter(mAdapter);

    }

    @OnClick(R.id.previousBTNId)
    public void previousBTNClicked(View view) {
        previousBTN.setBackgroundColor(Color.BLUE);
        nextBTN.setBackgroundColor(Color.WHITE);
        reservationDetails = getReservation(true);
        mAdapter = new ReservationAdapter(reservationDetails, reservationListener, false);
        recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    @OnClick(R.id.nextBTNId)
    public void nextBTNClicked(View view) {
        nextBTN.setBackgroundColor(Color.BLUE);
        previousBTN.setBackgroundColor(Color.WHITE);
        reservationDetails = getReservation(false);
        mAdapter = new ReservationAdapter(reservationDetails, reservationListener, true);
        recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    private List<ReservationDetails> getReservation(boolean before) {
        reservationDetails = new ArrayList<ReservationDetails>();
        Set<String> dateList = tutorReservation.getReservations().keySet();
        if (before) {
            for (Map.Entry<String, List<ReservationDetails>> entry : tutorReservation.getReservations().entrySet()) {
                if (DateUtil.getDateFromSimpleDateString(entry.getKey()).equals(reservationDate) || DateUtil.getDateFromSimpleDateString(entry.getKey()).before(reservationDate)) {
                    List<ReservationDetails> details = entry.getValue();
                    for (int i = 0; i < details.size(); i++) {
                        ReservationDetails reservation = details.get(i);
                        String name = getFirstTimeSlotName(reservation.getFirstTimeSlot().intValue());
                        reservation.setFirstTS(name);
                        reservation.setLastTS(getLastTimeSlotName(reservation.getFirstTS(), reservation.getAvailabilityIds().size()));
                        details.set(i, reservation);
                    }
                    reservationDetails.addAll(details);
                }
            }
        } else {
            for (Map.Entry<String, List<ReservationDetails>> entry : tutorReservation.getReservations().entrySet()) {
                if (DateUtil.getDateFromSimpleDateString(entry.getKey()).equals(reservationDate) || DateUtil.getDateFromSimpleDateString(entry.getKey()).after(reservationDate)) {
                    List<ReservationDetails> details = entry.getValue();
                    for (int i = 0; i < details.size(); i++) {
                        ReservationDetails reservation = details.get(i);
                        String name = getFirstTimeSlotName(reservation.getFirstTimeSlot().intValue());
                        reservation.setFirstTS(name);
                        reservation.setLastTS(getLastTimeSlotName(reservation.getFirstTS(), reservation.getAvailabilityIds().size()));
                        details.set(i, reservation);
                    }
                    reservationDetails.addAll(details);
                }

            }
        }
        return reservationDetails;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.unSubscribe();
    }

    private List<TimeSlot> getTimeSlots() {
        String[] timeSlotsArr = getResources().getStringArray(R.array.timeSlots);
        TimeSlot timeSlot = null;
        List<TimeSlot> timeSlotList = new ArrayList<TimeSlot>();
        int id = 0;
        for (String timeSlots : Arrays.asList(timeSlotsArr)) {
            timeSlot = new TimeSlot(id, timeSlots, false);
            timeSlotList.add(timeSlot);
            id++;
        }
        return timeSlotList;
    }

    private String getFirstTimeSlotName(int id) {
        for (TimeSlot timeSlot : timeSlots) {
            if (timeSlot.getId() == id) {
                return timeSlot.getName();
            }
        }
        return "";
    }

    private String getLastTimeSlotName(String time, int availabilitySize) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm a");
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dateFormat.parse(time));
            int intervalTime = availabilitySize * 30;
            calendar.add(Calendar.MINUTE, intervalTime);
            return dateFormat.format(calendar.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    ReservationListener reservationListener = new ReservationListener() {
        @Override
        public void onDeleteTutorReservation(long reservationId) {
            presenter.deleteTutorReservation(tutorId, reservationId);
        }
    };
}
