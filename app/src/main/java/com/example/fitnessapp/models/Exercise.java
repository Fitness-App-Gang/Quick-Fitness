package com.example.fitnessapp.models;
import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("Exercise")
public class Exercise extends ParseObject {
    public static final String KEY_NAME="name";
    public static final String KEY_VIDEOLINK="videoLink";
    private static final String KEY_MUSCLE = "muscle";

    public String getName(){
        return getString(KEY_NAME);
    }

    public void setName(String name){
        put(KEY_NAME, name);
    }

    public String getVideoLink(){
        return getString(KEY_VIDEOLINK);
    }

    public void setVideoLink(String videoLink){
        put(KEY_VIDEOLINK, videoLink);
    }

    public ParseObject getMuscle(){
        return getParseObject(KEY_MUSCLE);
    }

    public void setMuscle(ParseObject muscle){
        put(KEY_MUSCLE, muscle);
    }
}