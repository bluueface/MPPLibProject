package com.lib.controllers;

import com.lib.Main;
import com.lib.dataaccess.Auth;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ProfileController {
    @FXML
    private AnchorPane contentAnchorPane;

    @FXML
    private Button addMemberBtn;

    @FXML
    private Button editMemberBtn;

    @FXML
    private Button addBookBtn;

    @FXML
    private Button checkoutBookBtn;

    @FXML
    private Button addBookCopyBtn;

    @FXML
    private Button printCheckoutRecordBtn;

    @FXML
    private Label profileLabel;

    @FXML
    private void checkout() {
        loadContent("views/checkout-book.fxml");
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
    private void addBookCopy() {
        loadContent("views/add-book-copy.fxml");
    }


    @FXML
    private void addBook() {
        loadContent("views/add-book.fxml");
    }

    @FXML
    private void printCheckoutRecord() {
        loadContent("views/overview.fxml");
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

    private void loadContent(String fxml) {
        try {
            Node content = FXMLLoader.load(Main.class.getResource(fxml));
            contentAnchorPane.getChildren().setAll(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initializeAuthorization(Auth currentAuth) {
        switch (currentAuth) {
            case ADMIN:
                checkoutBookBtn.setManaged(false);
                printCheckoutRecordBtn.setManaged(false);
                break;
            case LIBRARIAN:
                addMemberBtn.setManaged(false);
                editMemberBtn.setManaged(false);
                addBookBtn.setManaged(false);
                addBookCopyBtn.setManaged(false);
                break;
        }
    }

    public void setContent(Node content) {
        contentAnchorPane.getChildren().setAll(content);
    }

    public void setProfileLabelText(String text) {
        profileLabel.setText(text);
    }
}
