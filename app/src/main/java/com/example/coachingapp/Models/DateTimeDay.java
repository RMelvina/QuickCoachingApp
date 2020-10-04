package com.example.coachingapp.Models;

public class DateTimeDay {
    public String mTime;
    public String mDay;
    public String mMonth;


    public DateTimeDay() {
    }

    public DateTimeDay(String mTime, String mDay, String mMonth) {
        this.mTime = mTime;
        this.mDay = mDay;
        this.mMonth = mMonth;
    }


    public String getmTime() {
        return mTime;
    }

    public void setmTime(String mTime) {
        this.mTime = mTime;
    }

    public String getmDay() {
        return mDay;
    }

    public void setmDay(String mDay) {
        this.mDay = mDay;
    }

    public String getmMonth() {
        return mMonth;
    }

    public void setmMonth(String mMonth) {
        this.mMonth = mMonth;
    }
}
