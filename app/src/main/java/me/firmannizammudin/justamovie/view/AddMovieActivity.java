package me.firmannizammudin.justamovie.view;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.firmannizammudin.justamovie.R;
import me.firmannizammudin.justamovie.model.Movie;
import me.firmannizammudin.justamovie.util.WebAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddMovieActivity extends AppCompatActivity {

    @BindView(R.id.add_movie_toolbar)
    Toolbar toolbar;

    @BindView(R.id.add_movie_et_title)
    EditText etTitle;

    @BindView(R.id.add_movie_et_year)
    EditText etYear;

    @BindView(R.id.add_movie_et_director)
    EditText etDirector;

    @BindView(R.id.add_movie_et_genre)
    EditText etGenre;

    @BindView(R.id.add_movie_et_plot)
    EditText etPlot;

    @BindView(R.id.add_movie_et_poster)
    EditText etPoster;

    @BindView(R.id.add_movie_btn_create)
    Button btnCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);
        ButterKnife.bind(this);

        setupToolbar();
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

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Add a Movie");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @OnClick(R.id.add_movie_btn_create)
    public void addMovie() {
        Movie movie = new Movie();
        movie.setMovieId(123456);
        movie.setMovieName(etTitle.getText().toString().trim());
        movie.setMovieYear(etYear.getText().toString().trim());
        movie.setMovieDirector(etDirector.getText().toString().trim());
        movie.setMovieGenre(new ArrayList<>(Arrays.asList(etGenre.toString().split(","))));
        movie.setMoviePlot(etPlot.getText().toString().trim());
        movie.setMoviePoster(etPoster.getText().toString().trim());

        WebAPI webAPI = WebAPI.Factory.create();
        Call<Movie.MovieData> call = webAPI.createMovie(movie);
        call.enqueue(new Callback<Movie.MovieData>() {
            @Override
            public void onResponse(Call<Movie.MovieData> call, Response<Movie.MovieData> response) {
                int code = response.code();
                if (code == 201) {
                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("result", new Gson().toJson(response.body().getData()));
                    setResult(Activity.RESULT_OK, returnIntent);
                    finish();
                } else {
                    Toast.makeText(AddMovieActivity.this, "Did not work : " + String.valueOf(code), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Movie.MovieData> call, Throwable t) {
                Toast.makeText(AddMovieActivity.this, "Did not work : " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
