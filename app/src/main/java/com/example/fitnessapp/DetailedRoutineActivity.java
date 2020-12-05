package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fitnessapp.Adapters.CommentAdapter;
import com.example.fitnessapp.Adapters.RoutineAdapter;
import com.example.fitnessapp.models.Comment;
import com.example.fitnessapp.models.Routine;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class DetailedRoutineActivity extends AppCompatActivity {

    //Routine Section
    private Routine routine;
    private String routineId;
    private String routineUser;
    private TextView tvUsername, tvTitle, tvDescription, tvLikes;
    private RatingBar rbDifficulty;
    private LinearLayout profileLayout;

    private RecyclerView rvComments;

    protected List<Comment> allComments;
    protected CommentAdapter commentAdapter;


    //Commenting Section
    private EditText etComment;
    private ImageButton btnSend;

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

        rvComments = findViewById(R.id.rvComments);
        allComments = new ArrayList<>();
        commentAdapter = new CommentAdapter(DetailedRoutineActivity.this ,allComments);

        rvComments.setAdapter(commentAdapter);
        rvComments.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        etComment = findViewById(R.id.etComment);
        btnSend = findViewById(R.id.btnSendComment);
        query.getInBackground(routineId, new GetCallback<Routine>() {
            @Override
            public void done(Routine queryRoutine, ParseException e) {
                if(e == null){
                    routine = queryRoutine;
                    loadRoutineData();
                    queryComments();
                }
            }
        });

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String commentText = etComment.getText().toString();
                if(commentText.isEmpty()) {
                    Log.i(TAG, "Comment is empty UwU");
                    return;
                } else{
                    postComment(commentText);
                    return;
                }
            }
        });
    }

    private void queryComments() {
        Log.d(TAG, "Query made");
        ParseQuery<Comment> query = ParseQuery.getQuery(Comment.class);
        query.include(Comment.KEY_AUTHOR);
        query.whereEqualTo(Comment.KEY_PARENTROUTINE, routine);
        query.setLimit(20);
        query.addDescendingOrder(Comment.KEY_CREATED);
        query.findInBackground(new FindCallback<Comment>() {
            @Override
            public void done(List<Comment> comments, ParseException e) {
                if(e != null){
                    Log.e(TAG, "Issue getting comments", e);
                    return;
                }
                for (Comment comment: comments){
                    Log.i(TAG, "Comment: " + comment.getText() + ", USERNAME: " + routine.getAuthor());
                }
                allComments.clear();
                allComments.addAll(comments);
                commentAdapter.notifyDataSetChanged();
            }
        });
    }

    protected void loadRoutineData(){
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

    protected void postComment(String text){
        Comment comment = new Comment();
        comment.setAuthor(ParseUser.getCurrentUser());
        comment.setParentRoutine(routine);
        comment.setText(text.trim());
        comment.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if(e != null){
                    Log.e(TAG, "Error while saving routine", e);
                    Toast.makeText(DetailedRoutineActivity.this, "Error posting comment", Toast.LENGTH_SHORT).show();
                    return;
                }
                Log.i(TAG, "Comment saved successfully!");
                etComment.setText("");
                queryComments();
            }
        });
    }
}