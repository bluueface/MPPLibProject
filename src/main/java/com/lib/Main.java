package com.lib;

import com.lib.controllers.ProfileController;
import com.lib.dataaccess.Auth;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {
    private static final double WIDTH = 670;

    private static final double HEIGHT = 510;

    private static String style;

    private static Stage mainStage;

    @Override
    public void start(Stage stage) throws IOException {
        mainStage = stage;
        stage.setTitle("Library Management");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("views/login.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        style = Objects.requireNonNull(getClass().getResource("css/style.css")).toExternalForm();
        scene.getStylesheets().add(style);
        mainStage.setScene(scene);
        mainStage.show();
    }

    public static void loadProfile(Auth currentAuth) throws Exception {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("views/profile.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        scene.getStylesheets().add(getStyle());

        ProfileController controller = loader.getController();
        controller.initializeAuthorization(currentAuth);
        controller.setProfileLabelText("Authorization level: " + currentAuth.toString());

        mainStage.setScene(scene);
    }

    public static String getStyle() {
        return style;
    }

    public static void main(String[] args) {
        launch();
    }
}