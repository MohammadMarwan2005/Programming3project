package com.mohammadmarwan.GUI.Listeners;

import com.mohammadmarwan.Charging.ChargingSystem;
import com.mohammadmarwan.GUI.Controller;
import com.mohammadmarwan.GUI.Views.AccountDetailsView;
import com.mohammadmarwan.GUI.Views.LoginView;
import com.mohammadmarwan.Structure.User;
import com.mohammadmarwan.Additional.IsValid;
import com.mohammadmarwan.Additional.Util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUpListener implements ActionListener {
    public void setLoginView(LoginView loginView) {
        this.loginView = loginView;
    }
    public void setLoginView(LoginView loginView, Controller controller) {
        this.loginView = loginView;
        this.controller = controller;
    }

    Controller controller = null;
    LoginView loginView = null;

    @Override
    public void actionPerformed(ActionEvent e) {
        IsValid isValid = Util.isValidPassword(loginView.getPassword(), loginView.getPassword());
        if (isValid.isValid()) {
            if (ChargingSystem.userIsExist(loginView.getUsername())) {
                String info = "The username is reserved for someone else";
                loginView.getPasswordValidationInfo().setText(info);
                System.out.println(info);
            } else {
                User user = new User(loginView.getUsername(), "No Des", 0, loginView.getPassword());

                controller.setCurrentUser(user);
                String info = "Logged in as " + controller.getCurrentUser().getName();
                loginView.getPasswordValidationInfo().setText(info);

                controller.showHomePageView();

                controller.setAccountDetails(new AccountDetailsView(controller));

                System.out.println(info);
            }
        } else {
            String info = isValid.getCause();
            loginView.getPasswordValidationInfo().setText(info);
        }
    }
}
