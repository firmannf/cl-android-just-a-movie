package me.firmannizammudin.justamovie.view;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.firmannizammudin.justamovie.R;
import me.firmannizammudin.justamovie.adapter.MoviesAdapter;
import me.firmannizammudin.justamovie.model.Movie;
import me.firmannizammudin.justamovie.model.Movies;
import me.firmannizammudin.justamovie.util.WebAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final int ADD_MOVIE = 1;

    @BindView(R.id.main_toolbar)
    Toolbar toolbar;

    @BindView(R.id.main_rv_movies)
    RecyclerView rvMovies;

    MoviesAdapter rvAdapter;

    List<Movie> movies = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setupToolbar();
        setupRecyclerView();

        WebAPI webAPI = WebAPI.Factory.create();
        Call<Movies> call = webAPI.getMovies();
        call.enqueue(new Callback<Movies>() {
            @Override
            public void onResponse(Call<Movies> call, Response<Movies> response) {
                int code = response.code();
                if (code == 200) {
                    movies.addAll(response.body().getData());
                    rvAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(MainActivity.this, "Did not work : " + String.valueOf(code), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Movies> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Did not work : " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_MOVIE) {
            if (resultCode == Activity.RESULT_OK) {
                movies.add(new Gson().fromJson(data.getStringExtra("result"), Movie.class));
            }
        }
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Just A Movie");
    }

    private void setupRecyclerView() {
        rvMovies.setLayoutManager(new LinearLayoutManager(this));
        rvAdapter = new MoviesAdapter(this, movies);
        rvMovies.setAdapter(rvAdapter);
    }

    @OnClick(R.id.main_fab_add)
    public void addMovie() {
        Intent intent = new Intent(this, AddMovieActivity.class);
        startActivityForResult(intent, ADD_MOVIE);
    }
}