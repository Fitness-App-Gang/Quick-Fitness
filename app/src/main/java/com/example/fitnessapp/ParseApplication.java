package com.example.fitnessapp;

import android.app.Application;

import com.example.fitnessapp.models.Comment;
import com.example.fitnessapp.models.Exercise;
import com.example.fitnessapp.models.LikedRoutines;
import com.example.fitnessapp.models.MuscleGroup;
import com.example.fitnessapp.models.Routine;
import com.example.fitnessapp.models.SavedRoutines;
import com.example.fitnessapp.models.User;
import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {

//    public static final String APP_API_KEY = BuildConfig.APP_ID_KEY;
//    public static final String ClIENT_KEY = BuildConfig.CLIENT_KEY;

    @Override
    public void onCreate() {
        super.onCreate();

        // Register your parse models
        ParseObject.registerSubclass(User.class);
        ParseObject.registerSubclass(Routine.class);
        ParseObject.registerSubclass(SavedRoutines.class);
        ParseObject.registerSubclass(LikedRoutines.class);
        ParseObject.registerSubclass(Comment.class);
        ParseObject.registerSubclass(MuscleGroup.class);
        ParseObject.registerSubclass(Exercise.class);


//        Parse.initialize(new Parse.Configuration.Builder(this)
//                .applicationId(APP_API_KEY)
//                .clientKey(ClIENT_KEY)
//                .server("https://parseapi.back4app.com")
//                .build()
//        );
    }
}