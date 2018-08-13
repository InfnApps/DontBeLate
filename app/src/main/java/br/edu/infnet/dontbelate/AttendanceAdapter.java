package br.edu.infnet.dontbelate;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.Inflater;

import br.edu.infnet.dontbelate.model.AttendanceRecord;

public class AttendanceAdapter extends RecyclerView.Adapter {

    List<AttendanceRecord> attendanceRecords;

    public AttendanceAdapter(List<AttendanceRecord> attendanceRecords) {
        this.attendanceRecords = attendanceRecords;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View card = LayoutInflater.from(parent.getContext()).
                            inflate(R.layout.attendance_card, parent, false);

        return new AttendanceViewHolder(card);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        AttendanceRecord attendanceRecord = attendanceRecords.get(position);
        //AttendanceViewHolder viewHolder = (AttendanceViewHolder) holder;
        //viewHolder.classLabel.setText(attendanceRecord.getClassName());
    }

    @Override
    public int getItemCount() {
        return attendanceRecords.size();
    }
}
