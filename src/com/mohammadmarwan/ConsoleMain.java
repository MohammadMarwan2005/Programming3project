package com.mohammadmarwan;

import com.mohammadmarwan.Charging.ChargingSystem;
import com.mohammadmarwan.IO.IO;
import com.mohammadmarwan.Logic.Ticketing;
import com.mohammadmarwan.CustomExceptions.UserNameIsAlreadyExistException;
import com.mohammadmarwan.CustomExceptions.UserNameIsNotExistException;
import com.mohammadmarwan.Structure.*;

import java.util.ArrayList;

public class ConsoleMain {
    public static void main(String[] args) {

        for (User user : ChargingSystem.listOfUsers) {
            System.out.println(user);
        }


        ArrayList<Show> showList = new ArrayList<>();

        showList.add(new Show(2023, 2, 2, 15, 1.2));
        showList.add(new Show(2023, 2, 2, 18, 1.3));
        showList.add(new Show(2023, 2, 2, 21, 1.4));
        showList.add(new Show(2023, 2, 2, 0, 1.4));


        ArrayList<Movie> movieList = new ArrayList<>();
        movieList.add(new Movie(34343, "Extraction 1", MovieType.ACTION, showList, 10));
        movieList.add(new Movie(34343, "Extraction 2", MovieType.COMEDY, showList, 12));
        movieList.add(new Movie(34343, "Extraction 3", MovieType.DRAMA, showList, 15));
        movieList.add(new Movie(34343, "Extraction 4", MovieType.FICTION, showList, 20));

        Cinema MohammadCinema = IO.createOldOrNewCinema("Mohammad", movieList, 60);
        Cinema EhsanCinema = IO.createOldOrNewCinema("Ehsan", movieList, 90);
        Cinema JouCinema = IO.createOldOrNewCinema("Jou", movieList, 80);


        Ticketing.displayAvailableSeats(EhsanCinema);

        String NAME = "Mohammad1";
        ChargingSystem.chargeUser(NAME, 50);
        try {
            Ticketing.bookForOldCustomer(EhsanCinema, NAME);
        } catch (UserNameIsNotExistException e) {
            try {
                Ticketing.bookForNewCustomer(EhsanCinema, NAME);
            } catch (UserNameIsAlreadyExistException ex) {
                throw new RuntimeException(ex); //
            }
        }
        try {
            Ticketing.cancelBooking(EhsanCinema, ChargingSystem.getUser(NAME));
        } catch (UserNameIsNotExistException e) {
            throw new RuntimeException(e);
        }
    }
}
