package com.mohammadmarwan.GUI.Views;

import com.mohammadmarwan.GUI.Controller;
import com.mohammadmarwan.GUI.Items.GrayYellowButton;

import javax.swing.*;
import java.awt.*;

public class HomePageView extends JFrame {
    private Controller controller;

    public void setController(Controller controller) {
        this.controller = controller;
        controller.setHomePageView(this);
    }

    public HomePageView() {
        setTitle("User Home Page");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 250);
        setLocationRelativeTo(null);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 10, 10));

        JButton accountDetailsButton = new GrayYellowButton("Account Details");
        JButton CinemasListButton = new GrayYellowButton("Cinemas List");
        JButton chargeMoneyButton = new GrayYellowButton("Charge Money");
        JButton logoutButton = new GrayYellowButton("Logout");
        JButton openMyTickets = new GrayYellowButton("My Tickets");

        accountDetailsButton.addActionListener(e -> controller.showAccountDetails());

        CinemasListButton.addActionListener(e -> controller.showCinemas());

        chargeMoneyButton.addActionListener(e -> controller.showChargeMoneyView());

        logoutButton.addActionListener(e -> controller.hideHomePageView());

        openMyTickets.addActionListener(e -> controller.showUserTicketsView());


        buttonPanel.add(accountDetailsButton);
        buttonPanel.add(chargeMoneyButton);
        buttonPanel.add(CinemasListButton);
        buttonPanel.add(openMyTickets);
        buttonPanel.add(logoutButton);

        add(buttonPanel, BorderLayout.CENTER);

    }

}


