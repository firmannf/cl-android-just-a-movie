package me.firmannizammudin.justamovie.model;

import java.util.List;

/**
 * Created by Firman on 18-Aug-16.
 */

public class Movies extends Base {
    List<Movie> data;

    public List<Movie> getData() {
        return data;
    }

    public void setData(List<Movie> data) {
        this.data = data;
    }
}
