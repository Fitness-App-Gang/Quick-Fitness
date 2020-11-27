package com.example.fitnessapp.models;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("SavedRoutines")
public class SavedRoutines extends ParseObject {
    public static final String KEY_OWNERID="username";
    public static final String KEY_ROUTINEID="routineId";

    public ParseObject getOwnerId(){
        return getParseObject(KEY_OWNERID);
    }

    public void setOwnerId(ParseObject author){
        put(KEY_OWNERID, author);
    }

    public ParseObject getRoutineId(){
        return getParseObject(KEY_ROUTINEID);
    }

    public void setRoutineId(ParseObject author){
        put(KEY_ROUTINEID, author);
    }
}