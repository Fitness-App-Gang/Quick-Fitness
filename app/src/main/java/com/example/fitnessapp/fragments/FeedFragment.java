package com.example.fitnessapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.fitnessapp.Adapters.RoutineAdapter;
import com.example.fitnessapp.CreateActivity;
import com.example.fitnessapp.R;
import com.example.fitnessapp.models.Routine;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class FeedFragment extends Fragment {

    public static final String TAG = "FeedFragment";

    private SwipeRefreshLayout swipeContainer;
    private RecyclerView rvRoutines;
    private FloatingActionButton fabCreate;
    protected RoutineAdapter adapter;
    protected List<Routine> allRoutines;

    public FeedFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_feed, container, false);
        fabCreate = (FloatingActionButton) view.findViewById(R.id.fabCreate);
        fabCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "Successfull Button press!!!");
                Intent i = new Intent(getActivity(), CreateActivity.class);
                startActivity(i);
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "Feed fragment opened");
        swipeContainer = view.findViewById(R.id.swipeContainer);
        swipeContainer.setColorSchemeResources(R.color.colorPrimary);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.i(TAG, "Fetching new data");
                queryPosts();
            }
        });
        rvRoutines = view.findViewById(R.id.rvRoutines);
        allRoutines = new ArrayList<>();
        adapter = new RoutineAdapter(getContext(),allRoutines);

        rvRoutines.setAdapter(adapter);
        rvRoutines.setLayoutManager(new LinearLayoutManager(getContext()));

        queryPosts();
    }

    protected void queryPosts() {
        Log.d(TAG, "Query made");
        ParseQuery<Routine> query = ParseQuery.getQuery(Routine.class);
        query.include(Routine.KEY_AUTHOR);
        query.setLimit(20);
        query.addDescendingOrder(Routine.KEY_CREATED);
        query.findInBackground(new FindCallback<Routine>() {
            @Override
            public void done(List<Routine> objects, ParseException e) {
                if(e != null){
                    Log.e(TAG, "Issue getting routines", e);
                    return;
                }
                for (Routine routine: objects){
                    Log.i(TAG, "Routine: " + routine.getTitle() + ", USERNAME: " + routine.getAuthor() + ", Description: " + routine.getDescription());
                }
                allRoutines.clear();
                allRoutines.addAll(objects);
                adapter.notifyDataSetChanged();
                swipeContainer.setRefreshing(false);
            }
        });
    }
}
