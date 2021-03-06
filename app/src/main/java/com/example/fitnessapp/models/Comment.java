package com.example.fitnessapp.models;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

import static com.example.fitnessapp.models.Routine.KEY_CREATED;

@ParseClassName("Comment")
public class Comment extends ParseObject {
    public static final String KEY_AUTHOR="author";
    public static final String KEY_TEXT="text";
    public static final String KEY_PARENTROUTINE="parentRoutine";
    public static final String KEY_CREATED="createdAt";

    public ParseUser getAuthor(){
        return getParseUser(KEY_AUTHOR);
    }

    public void setAuthor(ParseUser author){
        put(KEY_AUTHOR, author);
    }

    public String getText(){
        return getString(KEY_TEXT);
    }

    public void setText(String text){
        put(KEY_TEXT, text);
    }

    public ParseObject getParentRoutine(){
        return getParseObject(KEY_PARENTROUTINE);
    }

    public void setParentRoutine(ParseObject parentRoutine){
        put(KEY_PARENTROUTINE, parentRoutine);
    }

    public String getCreated(){
        return getString(KEY_CREATED);
    }
}