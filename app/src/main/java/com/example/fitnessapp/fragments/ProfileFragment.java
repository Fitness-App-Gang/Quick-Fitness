package com.example.fitnessapp.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitnessapp.R;
import com.example.fitnessapp.models.Routine;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class ProfileFragment extends Fragment {

    private static final String TAG = "ProfileFragment";

    Button btnPosts;
    Button btnSaved;
    Button btnLiked;
    TextView tvUsername;
    TextView tvDescription;
    ImageView ivProfile;
    List<Routine> routines;
    RecyclerView rvProfileRoutines;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //logic goes here
        btnPosts = (Button) view.findViewById(R.id.btnPosts);
        btnSaved = (Button) view.findViewById(R.id.btnSaved);
        btnLiked = (Button) view.findViewById(R.id.btnLiked);
        tvUsername = (TextView) view.findViewById(R.id.tvUsername);
        tvDescription = (TextView) view.findViewById(R.id.tvDescription);
        ivProfile = (ImageView) view.findViewById(R.id.ivProfile);
        ivProfile.setImageResource(R.drawable.anonymous);
        rvProfileRoutines = view.findViewById(R.id.rvProfileRoutines);
        btnPosts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        tvUsername.setText("A user!");

        tvDescription.setText("Likes long walks on the beach");
    }

    private void queryPosts(int type){
        Log.d(TAG, "querying posts");
        List<ParseObject> routineIds;
        ParseUser currUser = ParseUser.getCurrentUser();
        switch (type){
            //get the required ids
            case 1:
                //find RoutineIds in SavedRoutines where ownerId == currUser
                ParseQuery<Routine> query = ParseQuery.getQuery(Routine.class);
                query.whereEqualTo("ownerId",currUser.)
                break;
            case 2:
                desiredPosts = "liked";
                break;
            case 0:
            default:
                // just the user's posts
        }
        // get routine where
        ParseQuery<Routine> query = ParseQuery.getQuery()
    }
}
