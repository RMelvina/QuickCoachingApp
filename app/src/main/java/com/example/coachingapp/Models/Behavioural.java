package com.example.coachingapp.Models;

import java.util.List;

public class Behavioural {

    public List<String> choices;

    public Behavioural() {
    }

    public Behavioural(List<String> choices) {
        this.choices = choices;
    }

    public List<String> getChoices() {
        return choices;
    }

    public void setChoices(List<String> choices) {
        this.choices = choices;
    }
}
