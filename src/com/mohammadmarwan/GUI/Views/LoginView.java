package com.mohammadmarwan.GUI.Views;

import javax.swing.*;
import java.awt.event.ActionListener;

public class LoginView extends JFrame {
    private final JLabel passwordValidationInfo;
    private final JTextField usernameField;
    private final JPasswordField passwordField;
    private final JButton loginButton;
    private final JButton signupButton;
    private final JCheckBox showPasswordCheckBox;


    public JLabel getPasswordValidationInfo() {
        return passwordValidationInfo;
    }

    public LoginView() {

        setTitle("Login OR Signup");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 400);
        setLayout(null);
        setLocationRelativeTo(null);

        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        passwordValidationInfo = new JLabel("Validation Info:");

        usernameField = new JTextField();
        passwordField = new JPasswordField();

        showPasswordCheckBox = new JCheckBox();
        JLabel showPasswordLabel = new JLabel("Show Password");

        loginButton = new JButton("Login");
        signupButton = new JButton("Sign Up");


        usernameLabel.setBounds(40, 40, 100, 30);
        passwordLabel.setBounds(40, 80, 100, 30);
        showPasswordLabel.setBounds(40, 120, 100, 30);
        loginButton.setBounds(40, 160, 100, 30);


        usernameField.setBounds(150, 40, 100, 30);
        passwordField.setBounds(150, 80, 100, 30);
        showPasswordCheckBox.setBounds(150, 120, 100, 30);
        signupButton.setBounds(150, 160, 100, 30);


        passwordValidationInfo.setBounds(40, 190, 200, 30);

        showPasswordCheckBox.addChangeListener(
                e -> {
                    if (showPasswordCheckBox.isSelected())
                        passwordField.setEchoChar('\0');
                    else
                        passwordField.setEchoChar('•');
                }
        );


        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(showPasswordLabel);
        add(showPasswordCheckBox);
        add(loginButton);
        add(signupButton);

        add(passwordValidationInfo);
    }

    public String getUsername() {
        return usernameField.getText();
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }

    public void setLoginButtonListener(ActionListener listener) {
        loginButton.addActionListener(listener);
    }

    public void setSignupButtonListener(ActionListener listener) {
        signupButton.addActionListener(listener);
    }
}
