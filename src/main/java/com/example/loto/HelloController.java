package com.example.loto;

import javafx.beans.property.IntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {
    public TextField someText;
    public TableView<User> tableUsers;
    public TableColumn<User, String> nametab;
    public TableColumn<User, Number> moneytab;
    @FXML
    private Label welcomeText;
    @FXML
    private Label bbText;


    private ObservableList<User> personData = FXCollections.observableArrayList();

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");

        personData.add(new User("Hans"));
        personData.add(new User("Ruth"));


        nametab.setCellValueFactory(cellData -> cellData.getValue().getName());

        moneytab.setCellValueFactory(cellData -> cellData.getValue().getMoney());

        tableUsers.setItems(personData);
    }

    @FXML
    protected void onGoodbuyButtonClick() throws IOException {
        bbText.setText("Где эта кнопка?");


        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Users.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 624, 400);
        stage.setTitle("Hello!");
        stage.setScene(scene);


        stage.show();
    }
}