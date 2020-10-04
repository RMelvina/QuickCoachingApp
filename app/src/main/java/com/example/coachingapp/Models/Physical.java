package com.example.coachingapp.Models;

import java.util.List;

public class Physical {
    public List<String> choices;

    public Physical() {
    }

    public Physical(List<String> choices) {
        this.choices = choices;
    }

    public List<String> getChoices() {
        return choices;
    }

    public void setChoices(List<String> choices) {
        this.choices = choices;
    }
}
