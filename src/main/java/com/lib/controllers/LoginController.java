package com.lib.controllers;

import com.lib.Main;
import com.lib.dataaccess.Auth;
import com.lib.dataaccess.DataAccess;
import com.lib.dataaccess.DataAccessFacade;
import com.lib.dataaccess.User;
import com.lib.models.LoginException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.util.HashMap;

public class LoginController {
    public static Auth currentAuth = null;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label loginMessage;


    @FXML
    protected void login(ActionEvent event) throws Exception {
        String id = usernameField.getText();
        String password = passwordField.getText();
        DataAccess da = new DataAccessFacade();
        HashMap<String, User> map = da.readUserMap();
        if (!map.containsKey(id)) {
            Exception exception = new LoginException("ID " + id + " not found");
            loginMessage.setText(exception.getMessage());
            throw exception;
        }
        String passwordFound = map.get(id).getPassword();
        if (!passwordFound.equals(password)) {
            Exception exception = new LoginException("Password incorrect");
            loginMessage.setText(exception.getMessage());
            throw exception;
        }
        currentAuth = map.get(id).getAuthorization();
        Main.loadProfile(currentAuth);
    }
}