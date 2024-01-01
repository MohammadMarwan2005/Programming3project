package com.mohammadmarwan;

import com.mohammadmarwan.Charging.ChargingSystem;
import com.mohammadmarwan.GUI.Controller;
import com.mohammadmarwan.GUI.Items.MovieGridListView;
import com.mohammadmarwan.GUI.Listeners.LoginListener;
import com.mohammadmarwan.GUI.Listeners.SignUpListener;
import com.mohammadmarwan.GUI.Views.*;
import com.mohammadmarwan.Additional.Util;

import javax.swing.*;

public class GUIMain {
//    public static User currentUser = null;
    static LoginView loginView = new LoginView();
    static MovieGridListView gridMovie;

    static HomePageView homePageView = new HomePageView();
    static ChargeMoneyView chargeMoneyView;

    public static void main(String[] args) {
        System.out.println(ChargingSystem.listOfUsers);
        Thread t = new Thread(GUIMain::doS);
        t.start();
    }

    public static synchronized void doS() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                System.out.println("users = " + ChargingSystem.listOfUsers);
                JFrame frame = new JFrame();
                frame.setSize(650, 720);

                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

                Controller controller = new Controller(null, loginView, homePageView, frame);

                chargeMoneyView = new ChargeMoneyView(controller);
                homePageView.setController(controller);

                LoginListener loginListener = new LoginListener();
                loginListener.setLoginView(loginView, controller);

                gridMovie = new MovieGridListView(Util.getMoviesVector(), controller);
                controller.setMovieGridListView(gridMovie);
//                gridMovie.setController(controller);

                SignUpListener signUpListener = new SignUpListener();
                signUpListener.setLoginView(loginView, controller);


                loginView.setLoginButtonListener(
                        loginListener
                );


                loginView.setSignupButtonListener(
                        signUpListener
                );

                controller.setLoginViewVisible(true);
            }
        });

    }
}
