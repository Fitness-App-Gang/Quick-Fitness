package com.example.fitnessapp.models;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("LikedRoutines")
public class LikedRoutines extends ParseObject {
    public static final String KEY_OWNERID="ownerId";
    public static final String KEY_ROUTINEID="routineId";

    public ParseUser getOwnerId(){
        return getParseUser(KEY_OWNERID);
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