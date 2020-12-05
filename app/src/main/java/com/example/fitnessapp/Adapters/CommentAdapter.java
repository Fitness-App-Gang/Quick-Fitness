package com.example.fitnessapp.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitnessapp.R;
import com.example.fitnessapp.models.Comment;
import com.parse.ParseException;
import com.parse.SaveCallback;

import java.util.List;

import static android.content.ContentValues.TAG;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {

    private Context context;
    private List<Comment> comments;

    public CommentAdapter(Context context, List<Comment> comments) {
        this.context = context;
        this.comments = comments;
    }

    @NonNull
    @Override
    public CommentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_comment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentAdapter.ViewHolder holder, int position) {
        Comment routine = comments.get(position);
        holder.bind(routine);
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    public void clear() {
        comments.clear();
        notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    public void addAll(List<Comment> list) {
        comments.addAll(list);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        RoutineAdapter.OnRoutineListener onRoutineListener;

        private TextView tvUsername;
        private TextView tvText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.onRoutineListener = onRoutineListener;
            tvUsername = itemView.findViewById(R.id.tvUsername);
            tvText = itemView.findViewById(R.id.tvText);
        }

        public void bind(final Comment comment) {
            String username = comment.getAuthor().getUsername();
            String text = comment.getText();

            tvUsername.setText(username);
            tvText.setText(text);
        }
    }
}