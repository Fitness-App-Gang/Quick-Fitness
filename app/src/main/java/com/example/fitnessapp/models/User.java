package com.example.fitnessapp.models;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("User")
public class User extends ParseUser {
    public static final String KEY_USERNAME="username";
    public static final String KEY_PASSWORD="password";
    public static final String KEY_EMAIL="email";
    public static final String KEY_PROFILEPICTURE="profilePicture";
    public static final String KEY_BIO="bio";

    public String getUsername(){
        return getString(KEY_USERNAME);
    }

    public void setUsername(String username){
        put(KEY_USERNAME, username);
    }

    public String getPassword(){
        return getString(KEY_PASSWORD);
    }

    public void setPassword(String password){
        put(KEY_PASSWORD, password);
    }

    public String getEmail(){
        return getString(KEY_EMAIL);
    }

    public void setEmail(String email){
        put(KEY_EMAIL, email);
    }

    public ParseFile getProfilePicture() {
        return getParseFile(KEY_PROFILEPICTURE);
    }

    public void setProfilePicture(ParseFile parseFile){
        put(KEY_PROFILEPICTURE, parseFile);
    }

    public String getBio(){
        return getString(KEY_BIO);
    }

    public void setBio(String bio){
        put(KEY_BIO, bio);
    }
}

