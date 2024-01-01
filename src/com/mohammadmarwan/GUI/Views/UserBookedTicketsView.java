package com.mohammadmarwan.GUI.Views;

import com.mohammadmarwan.CustomExceptions.UserNameIsNotExistException;
import com.mohammadmarwan.GUI.Controller;
import com.mohammadmarwan.GUI.Items.GrayYellowButton;
import com.mohammadmarwan.Logic.Ticketing;
import com.mohammadmarwan.Structure.Show;
import com.mohammadmarwan.Structure.Ticket;
import com.mohammadmarwan.Additional.Util;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

public class UserBookedTicketsView extends JFrame {
    Controller controller;

    public UserBookedTicketsView(Controller controller) {
        this.controller = controller;
        setTitle(controller.getCurrentUser().getName() + "'s Tickets:");
        setSize(1200, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        ArrayList<Ticket> tickets = controller.getCurrentUser().getListOfBookedTickets();

        ArrayList<String> ticketsModel = new ArrayList<>();

        for (Ticket t : tickets) {
            ticketsModel.add(t.toString());
        }


        JList<Ticket> ticketsList = new JList<>(new Vector<>(tickets));
        ticketsList.setBackground(Color.DARK_GRAY);
        ticketsList.setForeground(Color.YELLOW);
        ticketsList.setFont(Util.font);

        JPanel northPanel = new JPanel();
        ticketsList.setBackground(Color.DARK_GRAY);

        JLabel var = new JLabel();
        var.setBackground(Color.DARK_GRAY);
        var.setForeground(Color.BLACK);
        var.setFont(Util.font);


        northPanel.add(var);

        if (tickets.isEmpty()) {
            var.setText("You don't have booked tickets!");
            var.setForeground(Color.RED);

        } else {
            var.setText("Your Tickets: ");
            var.setForeground(Color.BLACK);
        }

        var.setFont(Util.font);


        JPanel buttonsPanel = new JPanel(new GridLayout(1, 2));
        JButton backButton = new GrayYellowButton("Back");
        JButton cancelTickets = new GrayYellowButton("Cancel Tickets");
        cancelTickets.setEnabled(false);


        backButton.addActionListener(e -> controller.hideUserTicketsView());

        cancelTickets.addActionListener(e -> {
            int[] indices = ticketsList.getSelectedIndices();
            ArrayList<Ticket> toCancelTickets = new ArrayList<>(indices.length);
            for (int i : indices) {
                System.out.println(i);
                try {

                    toCancelTickets.add(tickets.get(i));
                } catch (Exception ignored) {
                }
            }
            for (Ticket toCancelTicket : toCancelTickets) {
                controller.setSelectedCinema(toCancelTicket.getCinema());

                controller.setSelectedMovie(toCancelTicket.getMovie());

                Show selectedShow = null;


                for (Show show : toCancelTicket.getMovie().getShowTimes()) {
                    if (show.getDate().toString().equals(toCancelTicket.getDate().toString()))
                        selectedShow = show;
                }


                controller.setSelectedShow(selectedShow);

                ArrayList<Integer> temp = new ArrayList<>();
                temp.add(toCancelTicket.getChairNumber());

                try {
                    Ticketing.cancelBooking(
                            controller,
                            temp
                    );



                } catch (UserNameIsNotExistException ex) {
                    throw new RuntimeException(ex);
                }
            }
            controller.hideUserTicketsView();
            controller.showUserTicketsView();

        });


        buttonsPanel.add(backButton);
        buttonsPanel.add(cancelTickets);


        add(northPanel, BorderLayout.NORTH);
        add(ticketsList, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.SOUTH);
    }
}
