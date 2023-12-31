package com.mohammadmarwan.additional;

import com.mohammadmarwan.GUI.Controller;
import com.mohammadmarwan.IO.IO;
import com.mohammadmarwan.Logic.Ticketing;
import com.mohammadmarwan.Structure.*;

import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Vector;

public class Util {
    public static final Font font = new Font("", Font.BOLD, 20);
    public static final Font font18 = new Font("", Font.BOLD, 18);

    public static final Font smallerFont = new Font("", Font.BOLD, 15);

    public static final Color disabledColor = new Color(97, 11, 33);

    public static IsValid isValidPassword(String password1, String password2) {
        if (password1.length() < 8) {
            return new IsValid(false, "Password is too short.");
        }

        if (!password1.equals(password2)) {
            return new IsValid(false, "No Match!");
        }

        return IsValid.NICE;
    }

    public static ImageIcon scaleImageIcon(ImageIcon imageIcon, int width, int height) {
        Image image = imageIcon.getImage();
        image = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(image);
    }

    public static double doubleOf(String s) throws NumberFormatException {
        return Double.parseDouble(s);
    }

    public static Vector<Movie> getMoviesVector() {

        //region initial moviesVector
        ArrayList<Show> showList = new ArrayList<>();
        showList.add(new Show(2023, 2, 2, 15, 1.2));
        showList.add(new Show(2023, 2, 2, 18, 1.3));
        showList.add(new Show(2023, 2, 2, 21, 1.4));
        showList.add(new Show(2023, 2, 2, 0, 1.6));

        showList.add(new Show(2023, 2, 12, 15, 1.2));
        showList.add(new Show(2023, 2, 12, 18, 1.3));
        showList.add(new Show(2023, 2, 12, 21, 1.4));
        showList.add(new Show(2023, 2, 12, 0, 1.6));

        showList.add(new Show(2023, 2, 22, 15, 1.2));
        showList.add(new Show(2023, 2, 22, 18, 1.3));
        showList.add(new Show(2023, 2, 22, 21, 1.4));
        showList.add(new Show(2023, 2, 22, 0, 1.6));


        Vector<Movie> movieVector = new Vector<>();
        Movie movie1 = new Movie(
                3434343,
                "Spider-Man",
                MovieType.ACTION,
                showList,
                10,
                "C:\\Users\\HP\\IdeaProjects\\Programming 3 project\\src\\com\\mohammadmarwan\\Drawable\\Spider-Man.jpg"
        );

        Movie movie2 = new Movie(
                45454545,
                "Green Mile",
                MovieType.FICTION,
                showList,
                11,
                "C:\\Users\\HP\\IdeaProjects\\Programming 3 project\\src\\com\\mohammadmarwan\\Drawable\\green mile.jpg"
        );
        Movie movie3 = new Movie(
                5656565,
                "Interstellar",
                MovieType.FICTION,
                showList,
                12,
                "C:\\Users\\HP\\IdeaProjects\\Programming 3 project\\src\\com\\mohammadmarwan\\Drawable\\interstellar.jpg"
        );
        Movie movie4 = new Movie(
                89989898,
                "National Security",
                MovieType.COMEDY,
                showList,
                13,
                "C:\\Users\\HP\\IdeaProjects\\Programming 3 project\\src\\com\\mohammadmarwan\\Drawable\\national security.jpg"
        );
        Movie movie5 = new Movie(
                5000,
                "Escape Plan",
                MovieType.ACTION,
                showList,
                14,
                "C:\\Users\\HP\\IdeaProjects\\Programming 3 project\\src\\com\\mohammadmarwan\\Drawable\\escape plan.jpg"
        );
        Movie movie6 = new Movie(
                69689898,
                "Extraction",
                MovieType.ACTION,
                showList,
                15,
                "C:\\Users\\HP\\IdeaProjects\\Programming 3 project\\src\\com\\mohammadmarwan\\Drawable\\extraction.jpg"
        );
        Movie movie7 = new Movie(
                87979898,
                "Extraction 2",
                MovieType.ACTION,
                showList,
                16,
                "C:\\Users\\HP\\IdeaProjects\\Programming 3 project\\src\\com\\mohammadmarwan\\Drawable\\extraction 2.jpg"
        );
        Movie movie8 = new Movie(
                89889888,
                "Inception",
                MovieType.FICTION,
                showList,
                17,
                "C:\\Users\\HP\\IdeaProjects\\Programming 3 project\\src\\com\\mohammadmarwan\\Drawable\\inception.jpg"
        );

        Movie movie9 = new Movie(
                898999998,
                "Insidious",
                MovieType.HORROR,
                showList,
                18,
                "C:\\Users\\HP\\IdeaProjects\\Programming 3 project\\src\\com\\mohammadmarwan\\Drawable\\insidious.jpg"
        );
        Movie movie10 = new Movie(
                1010101010,
                "Toy Story",
                MovieType.CARTOON,
                showList,
                19,
                "C:\\Users\\HP\\IdeaProjects\\Programming 3 project\\src\\com\\mohammadmarwan\\Drawable\\toy story.jpg"
        );
        Movie movie11 = new Movie(
                1110110111,
                "Toy Story 2",
                MovieType.CARTOON,
                showList,
                20,
                "C:\\Users\\HP\\IdeaProjects\\Programming 3 project\\src\\com\\mohammadmarwan\\Drawable\\toy story 2.jpg"
        );

        Movie movie12 = new Movie(
                12122121,
                "The Avengers",
                MovieType.ACTION,
                showList,
                21,
                "C:\\Users\\HP\\IdeaProjects\\Programming 3 project\\src\\com\\mohammadmarwan\\Drawable\\the avengers.jpg"
        );

        movieVector.add(movie1);
        movieVector.add(movie2);
        movieVector.add(movie3);
        movieVector.add(movie4);
        movieVector.add(movie5);
        movieVector.add(movie6);
        movieVector.add(movie7);
        movieVector.add(movie8);
        movieVector.add(movie9);
        movieVector.add(movie10);
        movieVector.add(movie11);
        movieVector.add(movie12);

        //endregion

        return movieVector;
    }


    public static Vector<Cinema> getCinemasVector() {
        Vector<Cinema> cinemaVector = new Vector<>();


        Vector<Movie> filteredMovies1 = getMoviesVector();
        filteredMovies1.remove(0);
        Vector<Movie> filteredMovies2 = getMoviesVector();
        filteredMovies2.remove(1);
        Vector<Movie> filteredMovies3 = getMoviesVector();
        filteredMovies3.remove(2);

        Cinema c = IO.createOldOrNewCinema("Daraa", filteredMovies1, 90);
        cinemaVector.add(c);

        cinemaVector.add(IO.createOldOrNewCinema("Damascus", getMoviesVector(), 60));

        cinemaVector.add(IO.createOldOrNewCinema("Aleppo", filteredMovies2, 80));


        cinemaVector.add(IO.createOldOrNewCinema("Homs", filteredMovies3, 50));
        cinemaVector.add(IO.createOldOrNewCinema("Latakia", getMoviesVector(), 40));


        return cinemaVector;
    }

    public static <T> Vector<String> getNames(Vector<T> cinemaVector) {
        Vector<String> res = new Vector<>();
        for (T t : cinemaVector) {
            if (t instanceof Cinema)
                res.add(((Cinema) t).getName());
            if (t instanceof Show) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy | h:mm a");
                res.add(((Show) t).getDate().format(formatter));
            }
        }
        return res;
    }

    public static double round(double number, int decimalPlaces) {
        // number = 2.5554343, dp = 2 >> scale = 100, 255/100 = 2.55
        double scale = Math.pow(10, decimalPlaces);
        return Math.round(number * scale) / scale;
    }


    public static void addUniqueSeat(ArrayList<Integer> selectedSeats, int selectedSeat) {
        if (!selectedSeats.contains(selectedSeat))
            selectedSeats.add(selectedSeat);
//        Collections.sort(selectedSeats);
    }

    public static Color determineCellColor(Controller controller, ArrayList<Integer> listOfBookedSeatsNumbers, int seatNumber, String bookerUsername) {

        if (listOfBookedSeatsNumbers.contains(seatNumber)) {
            if (bookerUsername.equals(controller.getCurrentUser().getName()))
                return Color.ORANGE;
            else
                return Util.disabledColor;
        }
        return Color.GREEN;
    }

    public static Color determineCostColor(Controller controller, double cost) {
        if (controller.getCurrentUser().getBudget() < cost)
            return Color.RED;
        return Color.GREEN;
    }

    public static ArrayList<Integer> getSeatsNumbers(ArrayList<Ticket> tickets) {
        ArrayList<Integer> res = new ArrayList<>();
        for (Ticket ticket : tickets) {
            res.add(ticket.getChairNumber());
        }
        return res;
    }

    public static void justRemoveIt(Controller controller, Ticket ticket) {
        for (Ticket bookedTicket : controller.getSelectedShow().getBookedTickets()) {
            System.out.println(bookedTicket.getCinema().getName().equals(ticket.getCinema().getName()));
            System.out.println(bookedTicket.getMovie().getName().equals(ticket.getMovie().getName()));
            System.out.println(bookedTicket.getDate().toString().equals(ticket.getDate().toString()));
            System.out.println(bookedTicket.getChairNumber() == ticket.getChairNumber());
            System.out.println(bookedTicket.getChairNumber());
            System.out.println(bookedTicket.getChairNumber());

            if (bookedTicket.getCinema().getName().equals(ticket.getCinema().getName())
            && bookedTicket.getMovie().getName().equals(ticket.getMovie().getName())
                    && bookedTicket.getDate().toString().equals(ticket.getDate().toString())
//                    && bookedTicket.getChairNumber() == ticket.getChairNumber()
            )
                controller.getSelectedShow().setBookedTickets(new ArrayList<>());
        }
    }

}