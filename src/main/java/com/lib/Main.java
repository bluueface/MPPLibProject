package com.lib;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {
    private static String style;

    @Override
    public void start(Stage stage) throws IOException {
        //FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("views/login.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("views/profile.fxml"));

        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 320, 240);
        style = Objects.requireNonNull(getClass().getResource("css/style.css")).toExternalForm();
        scene.getStylesheets().add(style);
        stage.setTitle("Sample Library Application");
        stage.setHeight(500);
        stage.setWidth(660);
        stage.setScene(scene);
        stage.show();
    }

    public static String getStyle() {
        return style;
    }

    public static void main(String[] args) {
        launch();
    }
}