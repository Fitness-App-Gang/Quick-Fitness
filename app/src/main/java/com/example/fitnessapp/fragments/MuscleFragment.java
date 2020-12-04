package com.example.fitnessapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.fitnessapp.BicepsActivity;
import com.example.fitnessapp.ChestActivity;
import com.example.fitnessapp.R;

public class MuscleFragment extends Fragment {

    public static final String TAG = "MuscleFragment";


    CardView bicep;
    CardView chest;
    CardView back;
    CardView legs;
    CardView shoulders;
    CardView abs;

    public MuscleFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_muscle, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bicep = (CardView) view.findViewById(R.id.bicep);
        chest = (CardView) view.findViewById(R.id.chest);
        back = (CardView) view.findViewById(R.id.back);
        legs = (CardView) view.findViewById(R.id.legs);
        shoulders = (CardView) view.findViewById(R.id.shoulders);
        abs = (CardView) view.findViewById(R.id.abs);

        bicep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLoadNewActivity = new Intent(getActivity(), BicepsActivity.class);
                // bind data to intent for each muscle part
                startActivity(intentLoadNewActivity);
            }
        });

        chest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLoadNewActivity = new Intent(getActivity(), ChestActivity.class);
                // bind data to intent for each muscle part
                startActivity(intentLoadNewActivity);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLoadNewActivity = new Intent(getActivity(), BicepsActivity.class);
                // bind data to intent for each muscle part
                startActivity(intentLoadNewActivity);
            }
        });

        legs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLoadNewActivity = new Intent(getActivity(), BicepsActivity.class);
                // bind data to intent for each muscle part
                startActivity(intentLoadNewActivity);
            }
        });

        shoulders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLoadNewActivity = new Intent(getActivity(), BicepsActivity.class);
                // bind data to intent for each muscle part
                startActivity(intentLoadNewActivity);
            }
        });

        abs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLoadNewActivity = new Intent(getActivity(), BicepsActivity.class);
                // bind data to intent for each muscle part
                startActivity(intentLoadNewActivity);
            }
        });


    }
}
