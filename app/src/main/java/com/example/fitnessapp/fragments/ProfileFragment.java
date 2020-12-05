package com.example.fitnessapp.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitnessapp.Adapters.ProfileRoutineAdapter;
import com.example.fitnessapp.Adapters.RoutineAdapter;
import com.example.fitnessapp.LoginActivity;
import com.example.fitnessapp.R;
import com.example.fitnessapp.models.LikedRoutines;
import com.example.fitnessapp.models.Routine;
import com.example.fitnessapp.models.SavedRoutines;
import com.example.fitnessapp.models.User;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
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
    ProfileRoutineAdapter adapter;

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
        rvProfileRoutines = (RecyclerView) view.findViewById(R.id.rvProfileRoutines);
        routines = new ArrayList<Routine>();

        adapter = new ProfileRoutineAdapter(view.getContext(),routines);
        rvProfileRoutines.setAdapter(adapter);
        rvProfileRoutines.setLayoutManager(new LinearLayoutManager(getContext()));

        btnPosts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                queryPosts(0);
            }
        });
        btnSaved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                queryPosts(1);
            }
        });
        btnLiked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                queryPosts(2);
            }
        });

        tvUsername.setText(ParseUser.getCurrentUser().getUsername());

        tvDescription.setText("No description... probably out getting gains instead.");

        queryPosts(0);
    }

    private void queryPosts(int type){
        routines = new ArrayList<Routine>();
        Log.d(TAG, "querying posts");
        ParseUser currUser = ParseUser.getCurrentUser();
        adapter.clear();
        switch (type){
            //get the required ids
            case 1:
                //find RoutineIds in SavedRoutines where ownerId == currUser
                Log.d(TAG, "type: " + type);
                ParseQuery<Routine> sQuery = ParseQuery.getQuery(Routine.class);
                sQuery.whereEqualTo(Routine.KEY_AUTHOR,currUser);
                sQuery.include(SavedRoutines.KEY_ROUTINEID);
                sQuery.findInBackground(new FindCallback<Routine>() {
                    @Override
                    public void done(List<Routine> objects, ParseException e) {
                        Log.d(TAG, "finished query");
                        if(e != null){
                            Log.e(TAG, "Issue getting routines", e);
                            return;
                        }
                        for (Routine routine: objects){
                            Log.i(TAG, "Routine: " + routine);
                        }
//                        adapter.addAll(objects);
                        adapter.notifyDataSetChanged();
                        if (adapter.getItemCount() <= 0) {
                            Toast.makeText(getView().getContext(), "Nothing Here Yet!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                break;
            case 2:
                Log.d(TAG, "type: " + type);
                ParseQuery<Routine> lQuery = ParseQuery.getQuery(Routine.class);
                lQuery.whereEqualTo(Routine.KEY_AUTHOR,currUser);
                lQuery.include(LikedRoutines.KEY_ROUTINEID);
                lQuery.findInBackground(new FindCallback<Routine>() {
                    @Override
                    public void done(List<Routine> objects, ParseException e) {
                        Log.d(TAG, "finished query");
                        if(e != null){
                            Log.e(TAG, "Issue getting routines", e);
                            return;
                        }
                        for (Routine routine: objects){
                            Log.i(TAG, "Routine: " + routine);
                        }
//                        adapter.addAll(objects);
                        adapter.notifyDataSetChanged();
                        if (adapter.getItemCount() <= 0) {
                            Toast.makeText(getView().getContext(), "Nothing Here Yet!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                break;
            case 0:
            default:
                // just the user's posts
                //find RoutineIds in SavedRoutines where ownerId == currUser
                Log.d(TAG, "type: " + type);
                ParseQuery<Routine> pQuery = ParseQuery.getQuery(Routine.class);
                Log.d(TAG, "uid: " + currUser.getObjectId());
                pQuery.include(Routine.KEY_AUTHOR);
                pQuery.whereEqualTo(Routine.KEY_AUTHOR,currUser);
                pQuery.setLimit(20);
                pQuery.addDescendingOrder(Routine.KEY_CREATED);
                pQuery.findInBackground(new FindCallback<Routine>() {
                    @Override
                    public void done(List<Routine> objects, ParseException e) {
                        Log.d(TAG, "finished query");
                        if(e != null){
                            Log.e(TAG, "Issue getting routines", e);
                            return;
                        }
                        for (Routine routine: objects){
                            Log.i(TAG, "Routine: " + routine);
                        }
                        adapter.addAll(objects);
                        adapter.notifyDataSetChanged();
                        if (adapter.getItemCount() <= 0) {
                            Toast.makeText(getView().getContext(), "Nothing Here Yet!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        }

    }
}
