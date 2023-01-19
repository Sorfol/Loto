package com.example.loto;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class LogController {
    public Label lableTest;

    @FXML
    private void initialize() {


    }

    public void setCurBer(String bet){
        lableTest.setText(bet);
    }

    public void onHelloButtonClick(ActionEvent actionEvent) {
        lableTest.setText("Кнопка работает!");

    }
}
