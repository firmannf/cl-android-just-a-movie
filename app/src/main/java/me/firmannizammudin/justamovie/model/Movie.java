package me.firmannizammudin.justamovie.model;

import java.util.List;

/**
 * Created by Firman on 18-Aug-16.
 */

public class Movie {
    private int movieId;
    private String movieName;
    private String movieYear;
    private String movieDirector;
    private List<String> movieGenre;
    private String moviePoster;
    private String moviePlot;

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMovieYear() {
        return movieYear;
    }

    public void setMovieYear(String movieYear) {
        this.movieYear = movieYear;
    }

    public String getMovieDirector() {
        return movieDirector;
    }

    public void setMovieDirector(String movieDirector) {
        this.movieDirector = movieDirector;
    }

    public List<String> getMovieGenre() {
        return movieGenre;
    }

    public void setMovieGenre(List<String> movieGenre) {
        this.movieGenre = movieGenre;
    }

    public String getMoviePoster() {
        return moviePoster;
    }

    public void setMoviePoster(String moviePoster) {
        this.moviePoster = moviePoster;
    }

    public String getMoviePlot() {
        return moviePlot;
    }

    public void setMoviePlot(String moviePlot) {
        this.moviePlot = moviePlot;
    }

    public class MovieData extends Base {
        private Movie data;

        public Movie getData() {
            return data;
        }

        public void setData(Movie data) {
            this.data = data;
        }
    }

    public class MovieList extends Base {
        List<Movie> data;

        public List<Movie> getData() {
            return data;
        }

        public void setData(List<Movie> data) {
            this.data = data;
        }
    }
}
