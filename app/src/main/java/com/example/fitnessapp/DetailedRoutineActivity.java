package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fitnessapp.models.Comment;
import com.example.fitnessapp.models.Routine;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import static android.content.ContentValues.TAG;

public class DetailedRoutineActivity extends AppCompatActivity {

    //Routine Section
    Routine routine;
    String routineId;
    String routineUser;
    TextView tvUsername, tvTitle, tvDescription, tvLikes;
    RatingBar rbDifficulty;
    LinearLayout profileLayout;


    //Commenting Section
    EditText etComment;
    ImageButton btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_routine);

        Intent i = getIntent();
        routineId = i.getStringExtra("routineID");
        routineUser = i.getStringExtra("username");
        ParseQuery<Routine> query = ParseQuery.getQuery(Routine.class);


        tvUsername= findViewById(R.id.tvUsername);
        tvTitle = findViewById(R.id.tvTitle);
        tvDescription = findViewById(R.id.tvDescription);
        tvLikes = findViewById(R.id.tvLikes);
        rbDifficulty = findViewById(R.id.rbDifficulty);
        profileLayout = findViewById(R.id.profileLayout);

        etComment = findViewById(R.id.etComment);
        btnSend = findViewById(R.id.btnSendComment);
        query.getInBackground(routineId, new GetCallback<Routine>() {
            @Override
            public void done(Routine queryRoutine, ParseException e) {
                if(e == null){
                    routine = queryRoutine;
                    String username = routineUser;
//                            routine.getAuthor().getUsername();
                    String desc = routine.getDescription();
                    String title = routine.getTitle();
                    float rating = (float) routine.getDifficulty();
                    String likes =  Integer.toString(routine.getLikes()) + " Likes";

                    tvUsername.setText(username);
                    tvTitle.setText(title);
                    tvDescription.setText(desc);
                    rbDifficulty.setRating(rating);
                    tvLikes.setText(likes);
                }
            }
        });

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String commentText = etComment.getText().toString();
                if(commentText.isEmpty()) {
                    return;
                } else{
                    postComment(commentText);
                    return;
                }
            }
        });
    }

    protected void loadRoutineData(){
        return;

    }

    protected void postComment(String text){
        Comment comment = new Comment();
        comment.setAuthor(ParseUser.getCurrentUser());
        comment.setParentRoutine(routine);
        comment.setText(text.trim());
        routine.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if(e != null){
                    Log.e(TAG, "Error while saving routine", e);
                    Toast.makeText(DetailedRoutineActivity.this, "Error posting comment", Toast.LENGTH_SHORT).show();
                    return;
                }
                Log.i(TAG, "Comment saved successfully!");
                etComment.setText("");
            }
        });
    }
}