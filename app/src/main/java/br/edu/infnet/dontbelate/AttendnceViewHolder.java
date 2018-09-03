package br.edu.infnet.dontbelate;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class AttendnceViewHolder extends RecyclerView.ViewHolder{

    public TextView classLabel;
    public TextView dateLabel;
    public TextView arrival;
    public TextView depart;
    public TextView counter;


    public AttendnceViewHolder(View itemView) {
        super(itemView);

        classLabel = itemView.findViewById(R.id.class_name);
        dateLabel = itemView.findViewById(R.id.date);
        arrival = itemView.findViewById(R.id.arrival_time);
        depart = itemView.findViewById(R.id.depart_time);
        counter = itemView.findViewById(R.id.attendance_counter);

    }
}
