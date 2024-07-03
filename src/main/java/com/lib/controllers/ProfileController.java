package com.lib.controllers;

import com.lib.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ProfileController {
    @FXML
    private AnchorPane contentAnchorPane;

    @FXML
    private void home() {
        loadContent("views/home.fxml");
    }

    @FXML
    private void addMember() {
        loadContent("views/add-member.fxml");
    }

    @FXML
    private void editMember() {
        loadContent("views/edit-member.fxml");
    }

    @FXML
    private void addBook() {
        loadContent("views/add-book.fxml");
    }

    private void loadContent(String fxml) {
        try {
            Node content = FXMLLoader.load(Main.class.getResource(fxml));
            contentAnchorPane.getChildren().setAll(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void logout() {
        try {
            Parent loginView = FXMLLoader.load(Main.class.getResource("views/login.fxml"));
            Scene loginScene = new Scene(loginView);
            Stage primaryStage = (Stage) contentAnchorPane.getScene().getWindow();
            loginScene.getStylesheets().add(Main.getStyle());
            primaryStage.setScene(loginScene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setContent(Node content) {
        contentAnchorPane.getChildren().setAll(content);
    }
}
