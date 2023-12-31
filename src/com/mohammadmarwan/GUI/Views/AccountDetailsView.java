package com.mohammadmarwan.GUI.Views;

import com.mohammadmarwan.GUI.Controller;
import com.mohammadmarwan.GUI.Items.GrayYellowButton;

import javax.swing.*;
import java.awt.*;

public class AccountDetailsView extends JFrame {
    public AccountDetailsView(Controller controller) {
        setTitle("Account Details View");
        setSize(250, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);


        JPanel detailsPanel = new JPanel(new GridLayout(3, 2, 5, 10));

        JLabel userNameLabel = new JLabel("User Name");

        JLabel userNameValue = new JLabel(
                controller.getCurrentUser().getName() != null ? controller.getCurrentUser().getName() : "");

        JLabel userBudgetLabel = new JLabel("User Budget");
        JLabel userBudgetValue = new JLabel(
                String.valueOf(controller.getCurrentUser().getBudget())
        );

        JButton backButton = new GrayYellowButton("Back");
        backButton.setSize(100, 25);


        backButton.addActionListener(e -> controller.hideAccountDetails()
        );

        detailsPanel.add(backButton);
        detailsPanel.add(new JLabel());
        detailsPanel.add(userNameLabel);
        detailsPanel.add(userNameValue);
        detailsPanel.add(userBudgetLabel);
        detailsPanel.add(userBudgetValue);

        add(detailsPanel, BorderLayout.CENTER);
    }
}
