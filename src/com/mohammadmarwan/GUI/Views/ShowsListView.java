package com.mohammadmarwan.GUI.Views;

import com.mohammadmarwan.GUI.Controller;
import com.mohammadmarwan.GUI.Items.GrayYellowButton;
import com.mohammadmarwan.Structure.Show;
import com.mohammadmarwan.Additional.Util;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

public class ShowsListView extends JFrame {
    Controller controller;

    public ShowsListView(Vector<Show> shows, Controller controller) {
        this.controller = controller;
        setTitle(controller.getSelectedMovie().toString());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(650, 720);
        setLocationRelativeTo(null);

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));    // todo
        leftPanel.setPreferredSize(new Dimension(400, getHeight()));
        leftPanel.setBackground(Color.DARK_GRAY);


        JButton backButton = new GrayYellowButton("Back");
        backButton.addActionListener(e ->
                controller.hideShows()
        );
        leftPanel.add(backButton);

        JLabel priceOfTicketLabel = new JLabel(
                "Price Of Ticket: "
        );
        priceOfTicketLabel.setForeground(Color.GREEN);
        priceOfTicketLabel.setFont(Util.font18);
        leftPanel.add(priceOfTicketLabel);


        JLabel movieCoverLabel = new JLabel(
                Util.scaleImageIcon(
                        new ImageIcon(controller.getSelectedMovie().getImagePath()),
                        400,
                        533
                )
        );
        leftPanel.add(movieCoverLabel);





        JLabel cinemaNameLabel = new JLabel(controller.getSelectedCinema().getName() + " Cinema: ");
        cinemaNameLabel.setFont(Util.font);
        leftPanel.add(cinemaNameLabel);
        cinemaNameLabel.setForeground(Color.green);


        JLabel movieNameLabel = new JLabel(controller.getSelectedMovie().getName());
        leftPanel.add(movieNameLabel);
        movieNameLabel.setFont(Util.font);
        movieNameLabel.setForeground(Color.CYAN);

        JLabel movieTypeLabel = new JLabel(controller.getSelectedMovie().getMovieType().toString());
        leftPanel.add(movieTypeLabel);
        movieTypeLabel.setFont(Util.font);
        movieTypeLabel.setForeground(Color.YELLOW);


        JPanel middlePanel = new JPanel(new BorderLayout());
        JList<String> showsList = new JList<>(Util.getNames(shows));

        JScrollPane scrollPane = new JScrollPane(showsList);
        showsList.setBackground(Color.DARK_GRAY);
        showsList.setForeground(Color.YELLOW);
        middlePanel.add(scrollPane);
        showsList.setFont(Util.font);

        showsList.setSelectedIndex(0);
        showsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);



        showsList.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent e) {
                int index = showsList.locationToIndex(e.getPoint());
//                System.out.println(controller.getSelectedMovie().getRowPrice() * shows.get(index).getPro());
                priceOfTicketLabel.setText("Price Of Ticket: " +
                        (
                                Util.round(controller.getSelectedMovie().getRowPrice() * shows.get(index).getPro(), 1) +
                                        " $"
                        )
                );
            }
        });

        showsList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int index = showsList.getSelectedIndex();
                priceOfTicketLabel.setText("Price Of Ticket: " +
                        (
                                Util.round(controller.getSelectedMovie().getRowPrice() * shows.get(index).getPro(), 1) +
                                        " $"
                        )
                );
            }
        });

        showsList.addKeyListener(
                new KeyAdapter() {
                    @Override
                    public void keyPressed(KeyEvent e) {
                        if (e.getKeyCode() == KeyEvent.VK_ENTER) {

                            System.out.println("Let's go in");

                            int index = showsList.getSelectedIndex();
                            controller.setSelectedShow(shows.get(index));
                            controller.showBookTicketsView();
                        }
                    }
                }
        );

        showsList.addMouseListener(
                new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (e.getClickCount() == 2) {
                            System.out.println("Let's go in");
                            int index = showsList.locationToIndex(e.getPoint());
//                            int index = showsList.getSelectedIndex();

                            controller.setSelectedShow(shows.get(index));
                            controller.showBookTicketsView();
                        }
                    }
                }
        );

        setLayout(new BorderLayout());
        add(leftPanel, BorderLayout.WEST);
        add(middlePanel, BorderLayout.CENTER);

//        add(rightPanel, BorderLayout.EAST);

    }

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }


}
