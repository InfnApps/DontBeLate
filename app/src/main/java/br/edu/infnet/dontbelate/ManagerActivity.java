package br.edu.infnet.dontbelate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

        attendanceList = findViewById(R.id.attendance_list_view);

        List<AttendanceRecord> records = AttendanceRecord.dummyRecord();
        AttendanceAdapter adapter = new AttendanceAdapter(records);

        LinearLayoutManager llm = new LinearLayoutManager(this);

        attendanceList.setLayoutManager(llm);
        attendanceList.setAdapter(adapter);
    }
}
