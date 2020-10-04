package com.example.coachingapp.Models;

import java.util.List;

public class Innerthought {
    public List<String> choices;

    public Innerthought() {
    }

    public Innerthought(List<String> choices) {
        this.choices = choices;
    }

    public List<String> getChoices() {
        return choices;
    }

    public void setChoices(List<String> choices) {
        this.choices = choices;
    }
}

