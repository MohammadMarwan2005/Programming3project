package com.mohammadmarwan.GUI.Listeners;

import com.mohammadmarwan.Charging.ChargingSystem;
import com.mohammadmarwan.GUI.Controller;
import com.mohammadmarwan.GUI.Views.AccountDetailsView;
import com.mohammadmarwan.GUI.Views.LoginView;
import com.mohammadmarwan.CustomExceptions.UserNameIsNotExistException;
import com.mohammadmarwan.CustomExceptions.WrongPasswordException;
import com.mohammadmarwan.Additional.IsValid;
import com.mohammadmarwan.Additional.Util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginListener implements ActionListener {

    Controller controller = null;

    public void setController(Controller controller) {
        this.controller = controller;
    }

    LoginView loginView = null;

    public void setLoginView(LoginView loginView) {
        this.loginView = loginView;
    }
    public void setLoginView(LoginView loginView, Controller controller) {
        this.loginView = loginView;
        this.controller = controller;
    }

    public void actionPerformed(ActionEvent e) {
        IsValid isValid = Util.isValidPassword(loginView.getPassword(), loginView.getPassword());
        if (isValid.isValid()) {
            try {

                controller.setCurrentUser(ChargingSystem.getUser(loginView.getUsername(), loginView.getPassword()));
                String info = "Logged in as " + controller.getCurrentUser();
                loginView.getPasswordValidationInfo().setText(info);
                System.out.println(info);


                controller.showHomePageView();
                controller.setAccountDetails(new AccountDetailsView(controller));

            } catch (UserNameIsNotExistException ex) {
                String info = "User Name Is Not Exist!";
                loginView.getPasswordValidationInfo().setText(info);
                System.out.println(info);
            } catch (WrongPasswordException ex) {
                String info = "Wrong Password";
                loginView.getPasswordValidationInfo().setText(info);
                System.out.println(info);
            }
        } else {
            String info = isValid.getCause();
            loginView.getPasswordValidationInfo().setText(info);
            System.out.println(info);
        }
    }
}
