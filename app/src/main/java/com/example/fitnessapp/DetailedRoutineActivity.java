//package com.example.fitnessapp;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.os.Bundle;
//import android.widget.EditText;
//import android.widget.ImageButton;
//import android.widget.LinearLayout;
//import android.widget.RatingBar;
//import android.widget.TextView;
//
//public class DetailedRoutineActivity extends AppCompatActivity {
//
//    //Routine Section
//    TextView tvUsername, tvTitle, tvDescription, tvLikes;
//    RatingBar rbDifficulty;
//    ImageButton btnLike;
//    LinearLayout profileLayout;
//
//
//    //Commenting Section
//    EditText etComment;
//    ImageButton btnSend;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_detailed_routine);
//
//        tvUsername= findViewById(R.id.tvUsername);
//        tvTitle = findViewById(R.id.tvTitle);
//        tvDescription = findViewById(R.id.tvDescription);
//        tvLikes = findViewById(R.id.tvLikes);
//        rbDifficulty = findViewById(R.id.rbDifficulty);
//        btnLike = findViewById(R.id.btnLike);
//        profileLayout = findViewById(R.id.profileLayout);
//
//        etComment = findViewById(R.id.etComment);
//        btnSend = findViewById(R.id.btnSendComment);
//    }
//}