package com.mohammadmarwan.GUI;

import com.mohammadmarwan.Charging.ChargingSystem;
import com.mohammadmarwan.CustomExceptions.UserNameIsNotExistException;
import com.mohammadmarwan.GUI.Items.MovieGridListView;
import com.mohammadmarwan.GUI.Views.*;
import com.mohammadmarwan.Structure.Cinema;
import com.mohammadmarwan.Structure.Movie;
import com.mohammadmarwan.Structure.Show;
import com.mohammadmarwan.Structure.User;
import com.mohammadmarwan.additional.Util;

import javax.swing.*;
import java.time.format.DateTimeFormatter;
import java.util.Vector;

public class Controller {

    public void setCurrentUser(User currentUser) {
        ChargingSystem.addUser(currentUser);
        this.currentUser = currentUser;
    }

    private User currentUser;

    public User getCurrentUser() {

        try {
            return ChargingSystem.getUser(currentUser.getName());
        } catch (UserNameIsNotExistException e) {
            throw new RuntimeException(e);
        }
    }

    public Cinema getSelectedCinema() {
        return selectedCinema;
    }

    public void setSelectedCinema(Cinema selectedCinema) {
        this.selectedCinema = selectedCinema;
    }

    private Cinema selectedCinema;

    public Show getSelectedShow() {
        return selectedShow;
    }

    public void setSelectedShow(Show selectedShow) {
        this.selectedShow = selectedShow;
    }

    private Show selectedShow;

    public Movie getSelectedMovie() {
        return selectedMovie;
    }

    public void setSelectedMovie(Movie selectedMovie) {
        this.selectedMovie = selectedMovie;
    }

    private Movie selectedMovie;

    public void setHomePageView(HomePageView homePageView) {
        this.homePageView = homePageView;
    }

    public void setChargeMoneyView(ChargeMoneyView chargeMoneyView) {
        this.chargeMoneyView = chargeMoneyView;
    }

    public ChargeMoneyView getChargeMoneyView() {
        return chargeMoneyView;
    }

    private ChargeMoneyView chargeMoneyView;
    private JScrollPane moviesListScrollPane;

    private LoginView loginView;

    private MovieGridListView movieGridListView;

    private JFrame movies = new JFrame();

    private ShowsListView shows;
    private BookTicketsView bookTicketsView;

    private HomePageView homePageView = new HomePageView();
    private final CinemasListView cinemasListView = new CinemasListView(Util.getCinemasVector(), this);


    public void setAccountDetails(AccountDetailsView accountDetailsView) {
        this.accountDetailsView = accountDetailsView;
    }

    private AccountDetailsView accountDetailsView;

    private UserBookedTicketsView userBookedTicketsView;

    public Controller() {

    }

    public Controller(User currnetUser, LoginView loginView, HomePageView homePageView, JFrame movies) {
        this.currentUser = currnetUser;
        this.loginView = loginView;
        this.homePageView = homePageView;
        movies.setTitle("Movies");
        this.movies = movies;

    }

    public void setMovieGridListView(MovieGridListView movieGridListView) {
        this.movieGridListView = movieGridListView;
    }

    public void setLoginViewVisible(boolean isVisible) {
        loginView.setVisible(isVisible);
    }

    public void showHomePageView() {
        loginView.setVisible(false);
        homePageView.setVisible(true);
    }
    public void hideHomePageView() {
        loginView.setVisible(true);
        homePageView.setVisible(false);
    }

    public void showMovies() {
        movies.setLocationRelativeTo(null);
        movies.setTitle("Available Movies in " + selectedCinema.getName() + " Cinema: ");

        cinemasListView.setVisible(false);

        if (moviesListScrollPane != null)
            movies.remove(moviesListScrollPane);

        moviesListScrollPane = new JScrollPane(movieGridListView);

        movies.add(moviesListScrollPane);
        movies.setVisible(true);
    }

    public void hideMovies() {
        cinemasListView.setVisible(true);

        movies.setVisible(false);
    }

    public void showAccountDetails() {
        accountDetailsView = new AccountDetailsView(this);
        homePageView.setVisible(false);

        accountDetailsView.setVisible(true);
    }

    public void hideAccountDetails() {
        homePageView.setVisible(true);

        accountDetailsView.setVisible(false);
    }

    public void showChargeMoneyView() {
        homePageView.setVisible(false);

        chargeMoneyView.setVisible(true);
    }

    public void hideChargeMoneyView() {
        homePageView.setVisible(true);

        chargeMoneyView.setVisible(false);
    }

    public void showCinemas() {
        homePageView.setVisible(false);


        cinemasListView.setVisible(true);
    }

    public void hideCinemas() {
        homePageView.setVisible(true);

        cinemasListView.setVisible(false);
    }

    public void showShows() {
        shows = new ShowsListView(new Vector<>(selectedMovie.getShowTimes()), this);

        shows.setLocationRelativeTo(null);

        shows.setTitle("Available Shows for " + selectedMovie.getName() + " Movie: ");

        movies.setVisible(false);


        shows.setVisible(true);
    }

    public void hideShows() {
        movies.setVisible(true);

        shows.setVisible(false);
    }
    public void showBookTicketsView() {
        bookTicketsView = new BookTicketsView(selectedShow, this);

        bookTicketsView.setLocationRelativeTo(null);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy | h:mm a");
        String formattedDate = getSelectedShow().getDate().format(formatter);

        bookTicketsView.setTitle(
                "Available Seats in " +
                        getSelectedCinema().getName() +
                        " Cinema for " +
                        getSelectedMovie().getName() +
                        " Movie at " +
                        formattedDate
        );

        shows.setVisible(false);

        bookTicketsView.setVisible(true);
    }
    public void hideBookTicketsView() {

        shows.setVisible(true);


        bookTicketsView.setVisible(false);
    }

    public void showUserTicketsView() {
        userBookedTicketsView = new UserBookedTicketsView(this);
        homePageView.setVisible(false);

        userBookedTicketsView.setVisible(true);
    }
    public void hideUserTicketsView() {
        homePageView.setVisible(true);

        userBookedTicketsView.setVisible(false);
    }
}

