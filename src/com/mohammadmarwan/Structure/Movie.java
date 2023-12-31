package com.mohammadmarwan.Structure;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Movie implements Serializable {
    private int id;
    private String name;
    private MovieType movieType;
    private List<Show> shows;
    private int rowPrice;

    private String imagePath;

    public Movie(int id, String name, MovieType movieType, List<Show> shows, int rowPrice) {
        this.id = id;
        this.name = name;
        this.movieType = movieType;
//        this.shows = shows;
        this.shows = new ArrayList<>();
        for (Show show : shows)
            this.shows.add(new Show(show.getDate(), show.getPro()));
        this.rowPrice = rowPrice;
    }

    public Movie(int id, String name, MovieType movieType, List<Show> shows, int rowPrice, String imagePath) {
        this.id = id;
        this.name = name;
        this.movieType = movieType;
//        this.shows = shows;
        this.shows = new ArrayList<>();
        for (Show show : shows)
            this.shows.add(new Show(show.getDate(), show.getPro()));
        this.rowPrice = rowPrice;
        this.imagePath = imagePath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MovieType getMovieType() {
        return movieType;
    }

    public void setMovieType(MovieType movieType) {
        this.movieType = movieType;
    }

    public List<Show> getShowTimes() {
        return shows;
    }

    public void setShowTimes(List<Show> shows) {
        this.shows = shows;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "name='" + name + '\'' +
                ", movieType=" + movieType +
                '}';
    }


    public int getRowPrice() {
        return rowPrice;
    }

    public void setRowPrice(int rowPrice) {
        this.rowPrice = rowPrice;
    }

    public String getImagePath() {
        return imagePath;
    }
}
