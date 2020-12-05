package com.example.fitnessapp.models;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("Routine")
public class Routine extends ParseObject {
    public static final String KEY_OBJECTID="objectId";
    public static final String KEY_AUTHOR="author";
    public static final String KEY_TITLE="title";
    public static final String KEY_DESCRIPTION="description";
    public static final String KEY_DIFFICULTY="difficulty";
    public static final String KEY_LIKES="likes";
    public static final String KEY_CREATED="createdAt";

    public String getId() {
        return getString(KEY_OBJECTID);
    }

    public ParseUser getAuthor(){
        return getParseUser(KEY_AUTHOR);
    }

    public void setAuthor(ParseUser author){
        put(KEY_AUTHOR, author);
    }

    public String getTitle(){
        return getString(KEY_TITLE);
    }

    public void setTitle(String title){
        put(KEY_TITLE, title);
    }

    public String getDescription(){
        return getString(KEY_DESCRIPTION);
    }

    public void setDescription(String description){
        put(KEY_DESCRIPTION, description);
    }

    public double getDifficulty() {
        return getDouble(KEY_DIFFICULTY);
    }

    public void setDifficulty(double difficulty){
        put(KEY_DIFFICULTY, difficulty);
    }

    public int getLikes(){
        return getInt(KEY_LIKES);
    }

    public void setLikes(int likes) {put(KEY_LIKES, likes);}

    public String getCreated(){
        return getString(KEY_CREATED);
    }
}

