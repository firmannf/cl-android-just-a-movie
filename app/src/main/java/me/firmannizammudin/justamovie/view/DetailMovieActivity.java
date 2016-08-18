package me.firmannizammudin.justamovie.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.firmannizammudin.justamovie.R;
import me.firmannizammudin.justamovie.model.Movie;

public class DetailMovieActivity extends AppCompatActivity {

    @BindView(R.id.detail_movie_toolbar)
    Toolbar toolbar;

    @BindView(R.id.detail_movie_iv_header)
    ImageView ivHeader;

    @BindView(R.id.detail_movie_txt_year)
    TextView txtYear;

    @BindView(R.id.detail_movie_txt_director)
    TextView txtDirector;

    @BindView(R.id.detail_movie_txt_genre)
    TextView txtGenre;

    @BindView(R.id.detail_movie_txt_plot)
    TextView txtPlot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        Movie movie = new Gson().fromJson(intent.getStringExtra("movie"), Movie.class);

        Glide.with(this).load(movie.getMoviePoster()).into(ivHeader);
        setupToolbar(movie.getMovieName());
        txtYear.setText(movie.getMovieYear());
        txtDirector.setText(movie.getMovieDirector());
        String genre = "";
        for (String data : movie.getMovieGenre()) {
            genre += data + "   ";
        }
        txtGenre.setText(genre);
        txtPlot.setText(movie.getMoviePlot());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupToolbar(String title) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
