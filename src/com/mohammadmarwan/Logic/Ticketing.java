package com.mohammadmarwan.Logic;


import com.mohammadmarwan.Charging.ChargingSystem;
import com.mohammadmarwan.CustomExceptions.PoorPersonException;
import com.mohammadmarwan.CustomExceptions.UserNameIsAlreadyExistException;
import com.mohammadmarwan.CustomExceptions.UserNameIsNotExistException;
import com.mohammadmarwan.GUI.Controller;
import com.mohammadmarwan.IO.IO;
import com.mohammadmarwan.Structure.*;
import com.mohammadmarwan.additional.Util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ticketing implements Serializable {

    static Scanner scanner = new Scanner(System.in);
    static int x, y;

    public static void displayAvailableSeats(Cinema cinema) {

        // for every show time we have to make a file that have the available seats

        System.out.println("choose movie: ");
        List<Movie> movieList = cinema.getMovies();
        for (int i = 0; i < cinema.getMovies().size(); i++) {
            System.out.println(i + 1 + "." + movieList.get(i));
        }

        x = scanner.nextInt(); // x-1 index of selected movie

        System.out.println("choose Show Time: ");
        List<Show> showList = movieList.get(x - 1).getShowTimes();
        for (int i = 0; i < showList.size(); i++) {
            System.out.println(i + 1 + "." + showList.get(i));
        }

        y = scanner.nextInt();  // y-1 index of selected Show


        int allSeats = cinema.getNumberOfSeats();
        int booked = showList.get(y - 1).getNumberOfBookedSeats();

        System.out.println("Available seats = " + (allSeats - booked));


        for (int i = 1; i < allSeats + 1; i++) {

            ArrayList<Integer> listOfBookedChairsNumbers = new ArrayList<>();

            for (Ticket ticket : showList.get(y - 1).getBookedTickets()) {
                listOfBookedChairsNumbers.add(ticket.getChairNumber());
            }

            if (listOfBookedChairsNumbers.contains(i))
                System.out.print(" \t");

            else
                System.out.print(i + "\t");     // print unbooked seats
            if (i % 10 == 0) {
                System.out.println();
                // for formatting
            }

        }

        IO.saveCinema(cinema);

    }

    /**
     * @throws UserNameIsAlreadyExistException which means:
     *                                         You should try if the userName is already exist, if yes retry with another userName
     */

    public static void bookForNewCustomer(Cinema cinema, String userName) throws UserNameIsAlreadyExistException {
        if (ChargingSystem.userIsExist(userName)) {
            System.out.println("This userName is already taken for another one, enter another userName");
            throw new UserNameIsAlreadyExistException();
        }
        System.out.println("bookForNewCustomer(), you are new to our service so:");

        System.out.println("How much does the user want to charge for his account?");
        double budget = scanner.nextDouble();
        User newUser = new User(userName, "NoDescription", budget);
        System.out.println("Now you are an old one, ");
        try {
            bookForOldCustomer(cinema, newUser.getName());
        } catch (UserNameIsNotExistException e) {
            System.out.println("It's impossible to print this, because user is just exist 😁!");
            throw new RuntimeException(e);
        }
    }


    /**
     * @throws UserNameIsNotExistException which means:
     *                                     You should make sure that the user is existed already in ChargingSystem.listOfUsers
     */
    public static void bookForOldCustomer(Cinema cinema, String oldUserName) throws UserNameIsNotExistException {
        if (!ChargingSystem.userIsExist(oldUserName))
            throw new UserNameIsNotExistException();

        displayAvailableSeats(cinema);

        int allSeats = cinema.getNumberOfSeats();

        List<Movie> movieList = cinema.getMovies();
        List<Show> showList = movieList.get(x - 1).getShowTimes();
        int booked = showList.get(y - 1).getNumberOfBookedSeats();


        User user = null;
        for (User u : ChargingSystem.listOfUsers) {
            if (oldUserName.equals(u.getName())) {
                user = u;
                // add break here;
            }
        }

        System.out.println("The user:" + user);

        double predictedPrice = movieList.get(x - 1).getRowPrice() * showList.get(y - 1).getPro();
        System.out.println("Predicted Price for each ticket is " + predictedPrice);

        System.out.println("Enter Number of people");
        int numberOfPeople = scanner.nextInt();

        if (allSeats - booked < numberOfPeople) {
            System.out.println("Failed!");
            return;
        }

        System.out.println("Enter seats number");
        for (int i = 0; i < numberOfPeople; i++) {
            int number = scanner.nextInt();
            if (user.getBudget() >= predictedPrice) {
                Ticket ticket = new Ticket(user, number, predictedPrice, showList.get(y - 1).getDate(), cinema, movieList.get(x - 1));
                showList.get(y - 1).addToList(ticket);
//                user.discount(predictedPrice);
                ChargingSystem.discountUser(user.getName(), predictedPrice);
                user.addTicket(ticket);
                showList.get(y - 1).addBook(1);
                showList.get(y - 1).addToList(ticket);
                System.out.println(ticket.getChairNumber() + " is Booked Successfully ");
            } else {
                System.out.println("user " + user.getName() + " is Poor 😂");
                // add break here;
            }
        }

        IO.saveCinema(cinema);

        ChargingSystem.addUser(user);
        IO.saveUsersList(ChargingSystem.listOfUsers);
    }

    public static void bookForOldCustomer(
            Controller controller,
            ArrayList<Integer> selectedList
    ) throws UserNameIsNotExistException, PoorPersonException {

        Cinema selectedCinema = controller.getSelectedCinema();
        String oldUserName = controller.getCurrentUser().getName();
        Movie selectedMovie = controller.getSelectedMovie();
        Show selectedShow = controller.getSelectedShow();

        if (!ChargingSystem.userIsExist(oldUserName))
            throw new UserNameIsNotExistException();

        int allSeats = selectedCinema.getNumberOfSeats();

        int booked = selectedShow.getNumberOfBookedSeats();

        User user = null;
        for (User u : ChargingSystem.listOfUsers) {
            if (oldUserName.equals(u.getName())) {
                user = u;
                // add break here;
            }
        }

        System.out.println("The user:" + user);

        double predictedPrice = Util.round(selectedMovie.getRowPrice() * selectedShow.getPro(), 1);
        System.out.println("Predicted Price for each ticket is " + predictedPrice);

        if (allSeats - booked < selectedList.size()) {
            System.out.println("Failed!");
            return;
        }


        if (user.getBudget() <= predictedPrice * selectedList.size())
            throw new PoorPersonException();
        System.out.println("Enter seats number");
        for (Integer i : selectedList) {
            int seatNumber = i;
            if (user.getBudget() >= predictedPrice) {
                Ticket ticket = new Ticket(user, seatNumber, predictedPrice, selectedShow.getDate(), selectedCinema, selectedMovie);

                ChargingSystem.discountUser(user.getName(), predictedPrice);
                user.addTicket(ticket);
                selectedShow.addBook(1);
                selectedShow.addToList(ticket);
                System.out.println(ticket.getChairNumber() + " is Booked Successfully ");
            } else {
                System.out.println("user " + user.getName() + " is Poor 😂");
                // add break here;
            }
        }


        IO.saveCinema(selectedCinema);

        ChargingSystem.addUser(user);

        IO.saveUsersList(ChargingSystem.listOfUsers);

        controller.setCurrentUser(user);

    }

    public static void cancelBooking(Cinema cinema, User user) throws UserNameIsNotExistException {
        if (!ChargingSystem.userIsExist(user.getName()))
            throw new UserNameIsNotExistException();

        displayAvailableSeats(cinema);

        List<Movie> movieList = cinema.getMovies();
        List<Show> showList = movieList.get(x - 1).getShowTimes();


        System.out.println("The user:" + user);

        double predictedPrice = movieList.get(x - 1).getRowPrice() * showList.get(y - 1).getPro();

        ArrayList<Ticket> toCancelTickets = new ArrayList<>();

        for (Ticket t : user.getListOfBookedTickets()) {
            if (t.getCinema() == cinema
                    && t.getMovie() == movieList.get(x - 1)
                    && t.getUser() == user
            ) {
                System.out.println("Do you want to cancel ticket (1: to cancel): \n" + t);
                int choose = scanner.nextInt();
                if (choose == 1) {
                    toCancelTickets.add(t);
                }
            }
        }

        user.getListOfBookedTickets().removeAll(toCancelTickets);
        showList.get(y - 1).addBook(-1 * toCancelTickets.size());
        showList.get(y - 1).getBookedTickets().removeAll(toCancelTickets);
        ChargingSystem.chargeUser(user, predictedPrice * toCancelTickets.size());


        IO.saveCinema(cinema);

        ChargingSystem.addUser(user);
        IO.saveUsersList(ChargingSystem.listOfUsers);
    }

    public static void cancelBooking(
            Controller controller,
            ArrayList<Integer> tickets
    ) throws UserNameIsNotExistException {
        System.out.println("ticket = " + tickets);


        Cinema cinema = controller.getSelectedCinema();
        Movie movie = controller.getSelectedMovie();
        Show show = controller.getSelectedShow();


        User user = null;
        for (User u : ChargingSystem.listOfUsers) {
            if (controller.getCurrentUser().getName().equals(u.getName())) {
                System.out.println("User " + u.getName() + " found....");
                System.out.println(u);
                user = u;
                // add break here;
            }
        }

        if (!ChargingSystem.userIsExist(user.getName()))
            throw new UserNameIsNotExistException();


        User tempUser = user;
        for (User u : ChargingSystem.listOfUsers) {
            if (tempUser.equals(u)) {
                tempUser = u;
                // add break here;
            }
        }

        System.out.println("The user:" + user);



        double predictedPrice = Util.round(movie.getRowPrice() * show.getPro(), 1);

        try {
            for (Ticket ticket : user.getListOfBookedTickets()) {
                if (ticket.getCinema().getName().equals(controller.getSelectedCinema().getName())
                        && ticket.getMovie().getName().equals(controller.getSelectedMovie().getName())
                        && ticket.getDate().toString().equals(controller.getSelectedShow().getDate().toString())
                        && ticket.getChairNumber() == tickets.get(0)
                        && ticket.getUser().getName().equals(controller.getCurrentUser().getName())
                ) {

                    show.getBookedTickets().remove(ticket);
                    show.removeFromList(ticket.getChairNumber());
                    ChargingSystem.chargeUser(user.getName(), predictedPrice);
                    user.removeTicket(ticket);
                    show.addBook(-1);
                    System.out.println(ticket.getChairNumber() + " is unbooked Successfully");
                }
            }
        } catch (Exception ignored) {

        }
//

        IO.saveCinema(cinema);

        ChargingSystem.addUser(user);

        IO.saveUsersList(ChargingSystem.listOfUsers);

        controller.setCurrentUser(user);

    }
}
