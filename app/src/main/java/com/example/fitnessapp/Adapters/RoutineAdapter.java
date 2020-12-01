package com.example.fitnessapp.Adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fitnessapp.R;
import com.example.fitnessapp.models.Routine;
import com.parse.ParseFile;

import java.util.List;

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
        private ImageButton btnLike;
        private boolean clicked;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            tvUsername = itemView.findViewById(R.id.tvUsername);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            rbDifficulty = itemView.findViewById(R.id.rbDifficulty);
            tvLikes = itemView.findViewById(R.id.tvLikes);
            btnLike = itemView.findViewById(R.id.btnLike);

            //other place
        }

        public void bind(final Routine routine) {
            String username = routine.getAuthor().toString();
            String desc = routine.getDescription();
            String title = routine.getDescription();
            float rating = (float) routine.getDifficulty();
            String likes =  Integer.toString(routine.getLikes());

            tvUsername.setText(username);
            tvTitle.setText(title);
            tvDescription.setText(desc);
            rbDifficulty.setRating(rating);
            clicked = false;
            tvLikes.setText(likes);

            btnLike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(btnLike.getBackground().equals(R.drawable.heart_alt_outline)){
                        btnLike.setBackgroundResource(R.drawable.heart_alt_fill);
                        routine.setLikes(routine.getLikes()+1);
                        tvLikes.setText(Integer.toString(routine.getLikes()));
                        return;
                    }
                    btnLike.setBackgroundResource(R.drawable.heart_alt_outline);
                    routine.setLikes(routine.getLikes()-1);
                    tvLikes.setText(Integer.toString(routine.getLikes()));
                }
            });

        }
    }

}