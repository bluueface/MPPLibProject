package com.lib.controllers;

import com.lib.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    protected void login(ActionEvent event) throws Exception {
        String username = usernameField.getText();
        String password = passwordField.getText();

        Main.loadProfile();

//        if ("admin".equals(username) && "password".equals(password)) {
//            try {
//                Main.loadProfile();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        } else {
//            // Handle invalid login
//            System.out.println("Invalid username or password");
//        }
    }
}