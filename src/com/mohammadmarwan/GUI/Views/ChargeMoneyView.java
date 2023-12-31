package com.mohammadmarwan.GUI.Views;

import com.mohammadmarwan.GUI.Controller;
import com.mohammadmarwan.GUI.Items.GrayYellowButton;
import com.mohammadmarwan.GUI.Listeners.ChargeButtonListener;

import javax.swing.*;
import java.awt.*;

public class ChargeMoneyView extends JFrame {

    public JTextField getAmountField() {
        return amountField;
    }


    private final JTextField amountField;
    private Controller controller;

    public ChargeMoneyView(Controller controller) {

        setController(controller);

        setTitle("Charge Money");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);

        JPanel amountPanel = new JPanel(new GridLayout(4, 1, 5, 5));
        JPanel buttonsPanel = new JPanel(new FlowLayout());


        amountField = new JTextField(5);


        JLabel amountLabel = new JLabel("Amount:");
        JButton chargeButton = new GrayYellowButton("Charge");
        ChargeButtonListener listener = new ChargeButtonListener();



        listener.setController(controller);

        chargeButton.addActionListener(listener);


        JButton backButton = new GrayYellowButton("Back");
        backButton.addActionListener(e -> controller.hideChargeMoneyView()
        );

        buttonsPanel.add(backButton);
        buttonsPanel.add(chargeButton);



        amountPanel.add(amountLabel);
        amountPanel.add(amountField);

        amountPanel.add(buttonsPanel);

        add(amountPanel);

    }

    public void setController(Controller controller) {
        this.controller = controller;
        controller.setChargeMoneyView(this);
    }

}
