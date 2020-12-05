package com.example.fitnessapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fitnessapp.models.Routine;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class CreateActivity extends AppCompatActivity {

    public static final String TAG = "CreateActivity";

    EditText etTitle;
    EditText etDescription;
    RatingBar rbDifficulty;
    FloatingActionButton fabCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        etTitle  = findViewById(R.id.etTitle);
        etDescription = findViewById(R.id.etDescription);
        rbDifficulty = findViewById(R.id.rvSetDifficulty);

        fabCreate = findViewById(R.id.fabCreate);
        fabCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String routineTitle = etTitle.getText().toString();
                String routineDescription = etDescription.getText().toString();
                float routineDifficulty = rbDifficulty.getRating();
                if(routineTitle.isEmpty()){
                    Toast.makeText(CreateActivity.this, "Routine must have a title", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if (routineDescription.isEmpty()){
                    Toast.makeText(CreateActivity.this, "Routine must have content", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(routineDifficulty == 0){
                    Toast.makeText(CreateActivity.this, "Routine must have a difficulty set", Toast.LENGTH_SHORT).show();
                    return;
                }
                ParseUser curUser = ParseUser.getCurrentUser();
                if(curUser == null){
                    Toast.makeText(CreateActivity.this, "Still need to get user going", Toast.LENGTH_SHORT).show();
                    Log.i(TAG, "Routine User not set successfully!");
                    return;
                }
                else if(postRoutine(curUser, routineTitle, routineDescription, routineDifficulty)){
                    finish();
                }
            }
        });

    }

    private boolean postRoutine(ParseUser curUser, String routineTitle, String routineDescription, final float routineDifficulty) {
        Routine routine = new Routine();
        final boolean[] success = {true};
        routine.setAuthor(curUser);
        routine.setTitle(routineTitle);
        routine.setDescription(routineDescription);
        routine.setDifficulty(routineDifficulty);
        routine.setLikes(0);
        routine.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if(e != null){
                    Log.e(TAG, "Error while saving routine", e);
                    Toast.makeText(CreateActivity.this, "Routine must have content", Toast.LENGTH_SHORT).show();
                    success[0] = false;
                    return;
                }
                Log.i(TAG, "Routine saved successfully!");
                etTitle.setText("");
                etDescription.setText("");
                rbDifficulty.setRating(0);
            }
        });
        return success[0];
    }

}
