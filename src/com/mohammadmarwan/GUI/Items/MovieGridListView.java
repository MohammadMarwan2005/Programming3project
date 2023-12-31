package com.mohammadmarwan.GUI.Items;

import com.mohammadmarwan.GUI.Controller;
import com.mohammadmarwan.Structure.Movie;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class MovieGridListView extends JPanel {

    public void setController(Controller controller) {
        this.controller = controller;
    }

    Controller controller;

    private Vector<Movie> movieList;



    public MovieGridListView(Vector<Movie> movieList, Controller controller) {
        super(new GridLayout(0, 4, 5, 5));
        this.movieList = movieList;

        JButton backButton = new GrayYellowButton("Back");
        backButton.addActionListener(e -> {
            controller.hideMovies();
        });
        add(backButton);

        this.controller = controller;

        displayMovies();
    }


    public Vector<Movie> getMovieList() {
        return movieList;
    }

    public void setMovieList(Vector<Movie> movieList) {
        this.movieList = movieList;
    }

    public void displayMovies() {
        for (Movie m : movieList) {
            MovieItemView movieItemView = new MovieItemView(m, controller);
            add(movieItemView);
        }
    }
}
