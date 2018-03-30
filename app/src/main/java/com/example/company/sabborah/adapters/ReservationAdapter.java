package com.example.company.sabborah.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.company.sabborah.R;
import com.example.company.sabborah.adapters.listeners.ReservationListener;
import com.example.company.sabborah.responses.tutorAvailability.ReservationDetails;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Mohamed Sayed on 3/19/2018.
 */

public class ReservationAdapter extends RecyclerView.Adapter<ReservationAdapter.MyViewHolder> {


    ReservationListener listener;
    List<ReservationDetails> reservationDetailsList;
    boolean newReservation;

    public ReservationAdapter(List<ReservationDetails> reservationDetailsList, ReservationListener listener, boolean newReservation) {
        this.reservationDetailsList = reservationDetailsList;
        this.listener = listener;
        this.newReservation = newReservation;
    }

    @Override
    public ReservationAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.reservation_layout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ReservationDetails reservationDetails = reservationDetailsList.get(position);
        holder.setHolder(reservationDetails);
    }

    @Override
    public int getItemCount() {
        return reservationDetailsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.dateTV)
        TextView dateTV;
        @BindView(R.id.subjectTV)
        TextView subjectTV;
        @BindView(R.id.timeSlotTV)
        TextView timeSlotTV;
        @BindView(R.id.deleteReservationId)
        TextView deleteReservation;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void setHolder(ReservationDetails reservationDetails) {
            if (newReservation) {
                deleteReservation.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = getAdapterPosition();
                        long reservationId = reservationDetailsList.get(position).getId();
                        reservationDetailsList.remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, reservationDetailsList.size());
                        if (listener != null) {
                            listener.onDeleteTutorReservation(reservationId);
                        }
                    }
                });
            } else {
                deleteReservation.setVisibility(View.GONE);
            }
            dateTV.setText(reservationDetails.getDate());
            timeSlotTV.setText(reservationDetails.getFirstTS() + " - " + reservationDetails.getLastTS());
            String subject = "";
            for (String subjectName : reservationDetails.getSubjectNames()) {
                subject += subjectName + "/";
            }
            subject = subject.substring(0, subject.lastIndexOf("/"));
            subjectTV.setText(subject);
        }
    }
}
