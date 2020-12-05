package com.example.fitnessapp.Adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fitnessapp.CreateActivity;
import com.example.fitnessapp.R;
import com.example.fitnessapp.models.Routine;
import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.util.List;

import static android.content.ContentValues.TAG;

public class RoutineAdapter extends RecyclerView.Adapter<RoutineAdapter.ViewHolder> {

    private Context context;
    private List<Routine> routines;

    public RoutineAdapter(Context context, List<Routine> routines) {
        this.context = context;
        this.routines = routines;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_routine, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Routine routine = routines.get(position);
        holder.bind(routine);
    }

    @Override
    public int getItemCount() {
        return routines.size();
    }

    public void clear() {
        routines.clear();
        notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    public void addAll(List<Routine> list) {
        routines.addAll(list);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvUsername;
        private TextView tvTitle;
        private TextView tvDescription;
        private RatingBar rbDifficulty;
        private TextView tvLikes;
        private CheckBox chkLike;
        private boolean clicked;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            tvUsername = itemView.findViewById(R.id.tvUsername);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            rbDifficulty = itemView.findViewById(R.id.rbDifficulty);
            tvLikes = itemView.findViewById(R.id.tvLikes);
            chkLike = itemView.findViewById(R.id.chkLike);

            //other place
        }

        public void bind(final Routine routine) {
            String username = routine.getAuthor().getUsername();
            String desc = routine.getDescription();
            String title = routine.getTitle();
            float rating = (float) routine.getDifficulty();
            String likes =  Integer.toString(routine.getLikes());

            tvUsername.setText(username);
            tvTitle.setText(title);
            tvDescription.setText(desc);
            clicked = false;
            rbDifficulty.setRating(rating);
            tvLikes.setText(likes);

            chkLike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(chkLike.isChecked()){
                        routine.setLikes(routine.getLikes()+1);
                        routine.saveInBackground(new SaveCallback() {
                            @Override
                            public void done(ParseException e) {
                                if(e != null){
                                    Log.e(TAG, "Eror while saving routine", e);
                                }
                                Toast.makeText(context, "Liked", Toast.LENGTH_SHORT).show();
                                tvLikes.setText(Integer.toString(routine.getLikes()));
                                clicked = true;
                                return;
                            }
                        });
                        return;
                    } else if(clicked = true){
                        routine.setLikes(routine.getLikes() - 1);
                        routine.saveInBackground(new SaveCallback() {
                            @Override
                            public void done(ParseException e) {
                                if(e != null){
                                    Log.e(TAG, "Eror while saving routine", e);
                                }
                                Toast.makeText(context, "Unliked", Toast.LENGTH_SHORT).show();
                                tvLikes.setText(Integer.toString(routine.getLikes()));
                                clicked = true;
                                return;
                            }
                        });
                        return;
                    }
                }

            });

        }
    }

}