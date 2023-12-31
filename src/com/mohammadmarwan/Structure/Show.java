package com.mohammadmarwan.Structure;


import java.io.Serializable;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Show implements Serializable {
    private int numberOfBookedSeats;
    private List<Ticket> bookedTickets = new ArrayList<>(); // []
    private ZonedDateTime date;
    private double pro;
    public Show(ZonedDateTime date, double pro) {
        this.numberOfBookedSeats = 0;
        this.date = date;
        this.pro = pro;
    }


    public double getPro() {
        return pro;
    }

    public void setPro(double pro) {
        this.pro = pro;
    }

    public Show(int year, int month, int dayOfMonth, int hour, double pro) {
        this.numberOfBookedSeats = 0;
        this.date = ZonedDateTime.of(year, month, dayOfMonth, hour,
                0, 0, 0,
                ZoneId.of("Asia/Damascus"));
        this.pro = pro;
//        for (Ticket bookedSeat : bookedTickets) {
//            bookedSeat.setCost$(ticketTotalPrice);
//        }
    }


    public int getNumberOfBookedSeats() {
        return numberOfBookedSeats;
    }

    public void setNumberOfBookedSeats(int numberOfBookedSeats) {
        this.numberOfBookedSeats = numberOfBookedSeats;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }

    public void addBook(int numberOfPeople) {
        this.numberOfBookedSeats += numberOfPeople;
    }

    public List<Ticket> getBookedTickets() {
        return bookedTickets;
    }

    public void setBookedTickets(List<Ticket> bookedTickets) {
        this.bookedTickets = bookedTickets;
    }

    public void addToList(Ticket ticket) {
        bookedTickets.add(ticket);
    }

    public void removeFromList(int chairNumber) {
        Iterator<Ticket> iterator = bookedTickets.iterator();

        while (iterator.hasNext())
            if (iterator.next().getChairNumber() == chairNumber)
                iterator.remove();
    }

    @Override
    public String toString() {
        return "Show{" +
                "numberOfBookedSeats=" + numberOfBookedSeats +
                ", date='" + date + '\'' +
                '}';
    }
}
