package me.firmannizammudin.justamovie.util;

import java.util.List;

import me.firmannizammudin.justamovie.model.Movie;
import me.firmannizammudin.justamovie.model.Movies;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Firman on 18-Aug-16.
 */

public interface WebAPI {
    String BASE_URL = "http://private-29404-justamovie.apiary-mock.com";

    @GET("/movies")
    Call<Movies> getMovies();

    @POST("/movies")
    Call<Movie> createMovie(@Body Movie movie);

    class Factory {
        public static WebAPI create() {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            return retrofit.create(WebAPI.class);
        }
    }
}