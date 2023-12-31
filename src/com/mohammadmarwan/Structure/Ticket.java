package com.mohammadmarwan.Structure;

import java.io.PrintStream;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Ticket implements Serializable {
    private User user;
    private int chairNumber;
    private double cost$;
    private Cinema cinema;
    private Movie movie;
    private ZonedDateTime date;

    public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Ticket(User user, int chairNumber, double cost$, ZonedDateTime zonedDateTime, Cinema cinema, Movie movie) {
        this.user = user;
        this.chairNumber = chairNumber;
        this.date = zonedDateTime;
        this.cost$ = cost$;
        this.movie = movie;
        this.cinema = cinema;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getChairNumber() {
        return chairNumber;
    }

    public void setChairNumber(int chairNumber) {
        this.chairNumber = chairNumber;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }

    public double getCost$() {
        return cost$;
    }

    public void setCost$(double cost$) {
        this.cost$ = cost$;
    }


    public String toString2() {
        return "Ticket{" +
                "userName=" + user.getName() +
                ", chairNumber=" + chairNumber +
                ", cost$=" + cost$ +
                ", date=" + date +
                ", cinema=" + cinema.getName() +
                ", movie=" + movie.getName() +
                '}' + '\n';
    }

    @Override
    public String toString() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy | h:mm a");
        String stringDate = this.date.format(formatter);
        String res =
                "cinema: " + cinema.getName() +
                        ", movie: " + movie.getName() +
                        ", date: " + stringDate +
                        ", chairNumber: " + chairNumber +
        ", cost$=" + cost$;
        return res;
    }
}
