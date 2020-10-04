package com.example.coachingapp.Models;


import java.util.List;

public class Goals_Road_Map {

//    public String rd_map_one;
//    public String rd_map_two;
//    public String rd_map_three;
//    public String rd_map_four;
//    public String re_map_five;


   public List<String> answers;

    public Goals_Road_Map() {
    }

    public Goals_Road_Map(List<String> answers) {
        this.answers = answers;

    }



    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }
}



