package br.edu.infnet.dontbelate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import br.edu.infnet.dontbelate.model.AttendanceRecord;

public class ManagerActivity extends AppCompatActivity {


    RecyclerView attendanceList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);

        attendanceList = findViewById(R.id.attendance_list);


        List<AttendanceRecord> records = AttendanceRecord.dummyRecords();
        AttendanceAdapter adapter = new AttendanceAdapter(records);

        RecyclerView.LayoutManager llm = new LinearLayoutManager(this);
        //GridLayoutManager llm = new GridLayoutManager(this, 2);

        attendanceList.setLayoutManager(llm);

        attendanceList.setAdapter(adapter);
    }
}
