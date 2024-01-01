package com.mohammadmarwan.GUI.Views;

import com.mohammadmarwan.GUI.Controller;
import com.mohammadmarwan.GUI.Items.GrayYellowButton;
import com.mohammadmarwan.GUI.Items.MovieGridListView;
import com.mohammadmarwan.Structure.Cinema;
import com.mohammadmarwan.Additional.Util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

public class CinemasListView extends JFrame {
    public void setController(Controller controller) {
        this.controller = controller;
    }

    Controller controller;

    public CinemasListView(Vector<Cinema> cinemasList, Controller controller) {
        this.controller = controller;
        setLayout(new FlowLayout());
        setTitle("Cinemas List");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 180);
        setLocationRelativeTo(null);

        JButton backButton = new GrayYellowButton("Back");
        add(backButton);

        JList<String> cinemasListView = new JList<>(Util.getNames(cinemasList));
        cinemasListView.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        cinemasListView.setSelectedIndex(0);


        backButton.addActionListener(e -> controller.hideCinemas()
        );

        add(cinemasListView);



        cinemasListView.addMouseListener(
                new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (e.getClickCount() == 2) {
                            int index = cinemasListView.locationToIndex(e.getPoint());
                            Cinema selectedCinema = cinemasList.get(index);
                            controller.setSelectedCinema(selectedCinema);

                            MovieGridListView movieGridListView =
                                    new MovieGridListView(new Vector<>(selectedCinema.getMovies()), controller);

                            controller.setMovieGridListView(movieGridListView);
                            controller.showMovies();
                        }
                    }
                }
        );

        cinemasListView.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    int index = cinemasListView.getSelectedIndex();
                    Cinema selectedCinema = cinemasList.get(index);
                    controller.setSelectedCinema(selectedCinema);

                    MovieGridListView movieGridListView =
                            new MovieGridListView(new Vector<>(selectedCinema.getMovies()), controller);

                    controller.setMovieGridListView(movieGridListView);
                    controller.showMovies();
                }
            }
        });

    }
}
