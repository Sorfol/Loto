package com.example.loto;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LotoApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LotoApplication.class.getResource("mainView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 950, 500);
        stage.setTitle("Пытаюсь сделать не всратое лото");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}