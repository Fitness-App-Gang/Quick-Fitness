package com.example.fitnessapp.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fitnessapp.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfilePostsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfilePostsFragment extends Fragment {

    public static final String ARG_PAGE = "POSTS";
    private int mPage;

    public static ProfilePostsFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        ProfilePostsFragment fragment = new ProfilePostsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile_posts, container, false);
    }
}