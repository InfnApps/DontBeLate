package br.edu.infnet.dontbelate.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AttendanceRecord {

    private String className;
    private Date arrival;
    private Date depart;
    private int counter;

    public AttendanceRecord(String className, Date arrival, Date depart, int counter) {
        this.className = className;
        this.arrival = arrival;
        this.depart = depart;
        this.counter = counter;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Date getArrival() {
        return arrival;
    }

    public void setArrival(Date arrival) {
        this.arrival = arrival;
    }

    public Date getDepart() {
        return depart;
    }

    public void setDepart(Date depart) {
        this.depart = depart;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }


    public static List<AttendanceRecord> dummyRecords(){
        List<AttendanceRecord> records = new ArrayList<>();

        for (int i = 1; i < 5; i++){
            Date sampleDate = Calendar.getInstance().getTime();
            records.add(new AttendanceRecord(
                    "Segurança, Monetização e Publicação de Aplicativos: " + i,
                     sampleDate,
                    sampleDate,
                    2
            ));
        }

        return records;
    }
}
