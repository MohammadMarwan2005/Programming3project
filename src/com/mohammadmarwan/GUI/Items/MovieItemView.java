package com.mohammadmarwan.GUI.Items;

import com.mohammadmarwan.GUI.Controller;
import com.mohammadmarwan.Structure.Movie;
import com.mohammadmarwan.Additional.Util;

import javax.swing.*;
import java.awt.*;

public class MovieItemView extends GrayYellowButton {
    private Movie movie;

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    private Controller controller;
    private JLabel titleLabel;
    private JLabel movieTypeLabel;

    private JPanel textPanel;

    public MovieItemView(Movie movie, Controller controller) {
        super("");

        this.controller = controller;

        this.addActionListener(e -> {
                    controller.setSelectedMovie(movie);
                    controller.showShows();
                }
        );

        this.movie = movie;

        setPreferredSize(new Dimension(150, 200));

        setLayout(new BorderLayout());


        setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));


        setIcon(Util.scaleImageIcon(new ImageIcon(
                movie.getImagePath()
        ), 100, 150));

        textPanel = new JPanel();
        textPanel.setBackground(Color.DARK_GRAY);
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));


        titleLabel = new JLabel(movie.getName(), SwingConstants.CENTER);
        titleLabel.setAlignmentX(CENTER_ALIGNMENT);
        titleLabel.setForeground(Color.YELLOW);

        movieTypeLabel = new JLabel(movie.getMovieType().toString(), SwingConstants.CENTER);
        movieTypeLabel.setAlignmentX(CENTER_ALIGNMENT);
        movieTypeLabel.setForeground(Color.CYAN);


        textPanel.add(Box.createVerticalBox());
        textPanel.add(titleLabel);
        textPanel.add(movieTypeLabel);

        add(textPanel, BorderLayout.SOUTH);
    }


}
