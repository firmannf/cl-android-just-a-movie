package me.firmannizammudin.justamovie.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.firmannizammudin.justamovie.R;
import me.firmannizammudin.justamovie.model.Movie;
import me.firmannizammudin.justamovie.view.DetailMovieActivity;

/**
 * Created by Firman on 18-Aug-16.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {
    private List<Movie> movies;
    private Context context;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // @BindView(R.id.main_iv_poster)
        // ImageView ivPoster;

        // @BindView(R.id.main_txt_title)
        // TextView txtTitle;

        public ViewHolder(View v) {
            super(v);
            // ButterKnife.bind(this, v);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, DetailMovieActivity.class);
            intent.putExtra("movie", new Gson().toJson(movies.get(getAdapterPosition())));
            context.startActivity(intent);
        }
    }

    public MoviesAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(context)
                .inflate(R.layout.activity_main_list_item, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Movie movie = movies.get(position);
        Glide.with(context).load(movie.getMoviePoster()).into(viewHolder.ivPoster);
        viewHolder.txtTitle.setText(movie.getMovieName());
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }
}