package com.mohammadmarwan.GUI.Listeners;

import com.mohammadmarwan.Charging.ChargingSystem;
import com.mohammadmarwan.CustomExceptions.UserNameIsNotExistException;
import com.mohammadmarwan.GUI.Controller;
import com.mohammadmarwan.GUI.Views.AccountDetailsView;
import com.mohammadmarwan.additional.Util;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChargeButtonListener implements ActionListener {

    Controller controller;

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String username = controller.getCurrentUser().getName();
            ChargingSystem.chargeUser(
                    ChargingSystem.getUser(username),
                    Util.doubleOf(controller.getChargeMoneyView().getAmountField().getText())
            );
            controller.setCurrentUser(ChargingSystem.getUser(username));
            controller.setAccountDetails(new AccountDetailsView(controller));

        } catch (UserNameIsNotExistException ex) {
            throw new RuntimeException(ex);
        } catch (NumberFormatException numberFormatException) {
            JOptionPane.showMessageDialog(controller.getChargeMoneyView(), "Enter a valid value!");
        }
        controller.getChargeMoneyView().getAmountField().setText("");
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }
}
