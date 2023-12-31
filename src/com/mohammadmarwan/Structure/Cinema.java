package com.mohammadmarwan.Structure;

import com.mohammadmarwan.IO.IO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Cinema implements Serializable {
    private final String name;
    private List<Movie> movies;
    private final int numberOfSeats;

    public Cinema(String name, List<Movie> movies, int numberOfSeats) {
        this.name = name;
//        this.movies = movies;
        this.movies = new ArrayList<>();
        for (Movie movie : movies) {
            this.movies.add(new Movie(
                    movie.getId(),
                    movie.getName(),
                    movie.getMovieType(),
                    movie.getShowTimes(),
                    movie.getRowPrice(),
                    movie.getImagePath()
            ));
        }

        this.numberOfSeats = numberOfSeats;
        IO s = new IO();
        s.saveCinema(this);
    }


    public String getName() {
        return name;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }


    @Override
    public String toString() {
        return "Cinema{" +
                "name='" + name + '\'' +
                '}';
    }

}