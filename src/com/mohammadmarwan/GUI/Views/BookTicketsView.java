package com.mohammadmarwan.GUI.Views;

import com.mohammadmarwan.CustomExceptions.PoorPersonException;
import com.mohammadmarwan.CustomExceptions.UserNameIsNotExistException;
import com.mohammadmarwan.GUI.Controller;
import com.mohammadmarwan.GUI.Items.GrayYellowButton;
import com.mohammadmarwan.GUI.Items.WhiteTextLabel;
import com.mohammadmarwan.Logic.Ticketing;
import com.mohammadmarwan.Structure.Show;
import com.mohammadmarwan.Structure.Ticket;
import com.mohammadmarwan.additional.Util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class BookTicketsView extends JFrame {
    private Controller controller;

    private final JLabel totalTicketsLabel;
    private final JLabel totalCostLabel;

    private final JLabel numberOfSelectedTicketsLabel;

    public BookTicketsView(Show show, Controller controller) {

        setController(controller);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(720, 400);
        setLayout(new BorderLayout());

        ArrayList<Integer> selectedSeats = new ArrayList<>();

        JLabel priceLabel = new WhiteTextLabel("Price of each ticket: " +
                Util.round(controller.getSelectedMovie().getRowPrice() * show.getPro(), 1) + " $"
        );

        totalTicketsLabel = new WhiteTextLabel("Number of selected tickets: 0");

        totalCostLabel = new WhiteTextLabel("Total cost: " + 0.0 + " $");

        numberOfSelectedTicketsLabel = new WhiteTextLabel("Selected tickets: " + selectedSeats);

        JLabel yourBudgetLabel = new WhiteTextLabel("You Budget: " + Util.round(controller.getCurrentUser().getBudget(), 1) + " $");

        numberOfSelectedTicketsLabel.setPreferredSize(new Dimension(getWidth(), 25));

        JPanel selectedTicketsPanel = new JPanel(new FlowLayout());
        selectedTicketsPanel.setPreferredSize(new Dimension(getWidth(), 25));
        selectedTicketsPanel.add(numberOfSelectedTicketsLabel);


        List<Ticket> listOfBookedTickets = controller.getSelectedShow().getBookedTickets();


        ArrayList<Integer> listOfBookedSeatsNumbers = new ArrayList<>();



        System.out.println("listOfBookedTickets: " + listOfBookedTickets);


        JPanel topLinePanel = new JPanel(new GridLayout(1, 4));
        topLinePanel.add(yourBudgetLabel);
        topLinePanel.add(priceLabel);
        topLinePanel.add(totalTicketsLabel);
        topLinePanel.add(totalCostLabel);
        topLinePanel.setBackground(Color.DARK_GRAY);

        JPanel topPanel = new JPanel(new GridLayout(2, 4));
        topPanel.setBackground(Color.DARK_GRAY);

        topPanel.add(topLinePanel);
        topPanel.add(numberOfSelectedTicketsLabel);


        JPanel centerPanel = new JPanel(new GridLayout(
                controller.getSelectedCinema().getNumberOfSeats() / 10,
                10,
                1,
                1
        ));


        for (int seatNumber = 0; seatNumber < controller.getSelectedCinema().getNumberOfSeats(); seatNumber++) {

            String bookerUsername = "";

            for (Ticket t : show.getBookedTickets()) {
                if (t.getChairNumber() == seatNumber + 1)
                    bookerUsername = t.getUser().getName();
            }

            for (Ticket ticket : listOfBookedTickets) {
                listOfBookedSeatsNumbers.add(ticket.getChairNumber());
            }

            JButton cell = new GrayYellowButton(String.valueOf(seatNumber + 1));
            Color color = Util.determineCellColor(controller, listOfBookedSeatsNumbers, seatNumber + 1, bookerUsername);
            if (color.equals(Util.disabledColor)) {
                cell.setEnabled(false);
                cell.setBackground(color);
            }

            cell.setForeground(color);

            int finalSeatNumber = seatNumber + 1;
            cell.addActionListener(e -> {
                if (cell.getForeground() == Color.GREEN) {
                    Util.addUniqueSeat(selectedSeats, finalSeatNumber);
                    numberOfSelectedTicketsLabel.setText(selectedSeats.toString());
                    double ticketPrice = Util.round(
                            controller.getSelectedMovie().getRowPrice() * controller.getSelectedShow().getPro(),
                            1
                    );

                    double cost = selectedSeats.size() * ticketPrice;

                    totalCostLabel.setText(
                            "Total cost: " +
                                    Util.round(cost, 1)
                                    + " $"
                    );

                    totalCostLabel.setForeground(Util.determineCostColor(controller, cost));

                    totalTicketsLabel.setText("Number of selected tickets: " + selectedSeats.size());
                    totalTicketsLabel.setForeground(Util.determineCostColor(controller, cost));

                } else {
                    Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(null, "You can not rebook a ticket again!");
                }
            });

            int finalSeatNumber1 = seatNumber;

            cell.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getButton() == MouseEvent.BUTTON3) {
                        if (cell.getForeground() != Color.ORANGE) {
                            Toolkit.getDefaultToolkit().beep();
                            JOptionPane.showMessageDialog(null, "You Can not Cancel a non-booked Ticket!");
                        }
                        else {
                            int seat = Integer.parseInt(cell.getText());
                            int choice = JOptionPane.showOptionDialog(
                                    null,
                                    "Do you want to cancel this ticket (" + cell.getText() + ")?",
                                    "Cancel Ticket",
                                    JOptionPane.YES_NO_OPTION,
                                    JOptionPane.QUESTION_MESSAGE,
                                    null,
                                    new String[]{"Cancel", "Don't Cancel"},
                                    "Cancel"
                            );

                            System.out.println("Current User == " + controller.getCurrentUser());
                            Toolkit.getDefaultToolkit().beep();

                            if (choice == JOptionPane.YES_OPTION) {
                                ArrayList<Integer> toCancelTickets = new ArrayList<>();
//                                toCancelTickets.add();
                                toCancelTickets.add(seat);
                                System.out.println("toCancelTicket : " + toCancelTickets);
                                try {
                                    Ticketing.cancelBooking(
                                            controller,
                                            toCancelTickets
                                    );
                                } catch (UserNameIsNotExistException ex) {
                                    throw new RuntimeException(ex);
                                }

                                controller.hideBookTicketsView();
                                controller.showBookTicketsView();

                            }

                        }
                    }
                }
            });
            centerPanel.add(cell);
        }

        JButton backButton = new GrayYellowButton("Back");
        backButton.addActionListener(e -> controller.hideBookTicketsView());


        JButton bookButton = new GrayYellowButton("Book");
        bookButton.setForeground(Color.CYAN);
        bookButton.setFont(Util.font);

        bookButton.addActionListener(e -> {
                    try {
                        System.out.println(selectedSeats);

                        Ticketing.bookForOldCustomer(
                                controller,
                                selectedSeats
                        );

                        controller.hideBookTicketsView();
                        controller.showBookTicketsView();


                    } catch (UserNameIsNotExistException ex) {
                        throw new RuntimeException(ex);
                    } catch (PoorPersonException ex) {
                        Toolkit.getDefaultToolkit().beep();


                        int choice = JOptionPane.showOptionDialog(
                                null,
                                "You are poor 🙂, do you want to charge your account?",
                                "Poor Person 🙂",
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.QUESTION_MESSAGE,
                                null,
                                new String[]{"Go to Charging View", "Don't Charge"},
                                "Go to Charging View"
                        );

                        Toolkit.getDefaultToolkit().beep();

                        if (choice == JOptionPane.YES_OPTION) {
                            controller.hideBookTicketsView();
                            controller.hideShows();
                            controller.hideMovies();
                            controller.hideCinemas();
                            controller.showChargeMoneyView();
                        }
//                        JOptionPane.showMessageDialog(null, "You are poor, charge money first 🙂");
//                        JOptionPane.showMessageDialog(new JLabel("You are Poor"), new GrayYellowButton("Charge"));
                    }
                }
        );


        JLabel bookedForYou = new JLabel("Booked For You");
        bookedForYou.setForeground(Color.ORANGE);

        JLabel available = new JLabel("Available Seat");
        available.setForeground(Color.GREEN);

        JLabel bookedForAnotherOne = new JLabel("Booked For Another One");
        bookedForAnotherOne.setForeground(Util.disabledColor);

        JPanel guid = new JPanel(new GridLayout(1, 3));
        guid.add(available);
        guid.add(bookedForYou);
        guid.add(bookedForAnotherOne);
        guid.setBackground(Color.DARK_GRAY);


        JPanel downPanel = new JPanel(new BorderLayout());
        downPanel.setBackground(Color.DARK_GRAY);
        downPanel.add(backButton, BorderLayout.WEST);
        downPanel.add(bookButton, BorderLayout.EAST);

        downPanel.add(guid, BorderLayout.CENTER);

        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(downPanel, BorderLayout.SOUTH);

    }

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }
}
