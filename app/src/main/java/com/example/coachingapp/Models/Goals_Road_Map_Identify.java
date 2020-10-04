package com.example.coachingapp.Models;

import java.util.List;

public class Goals_Road_Map_Identify {

    public List<String> hazard;

    public Goals_Road_Map_Identify() {
    }

    public Goals_Road_Map_Identify(List<String> hazard) {
        this.hazard = hazard;
    }

    public List<String> getHazard() {
        return hazard;
    }

    public void setHazard(List<String> hazard) {
        this.hazard = hazard;
    }
}
