package com.example.loto;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class LogController {
    public Label lableTest;
    public Button justButton;

    @FXML
    private void initialize() {


    }

    public void setGriedParam(ObservableList<User> pData, int gameCount){

        ShowLog sl = new ShowLog();
        Stage stage = (Stage) justButton.getScene().getWindow();
        sl.setPersonData(pData);
        sl.setGameCount(gameCount);
        sl.start(stage);

    }

    public void onHelloButtonClick(ActionEvent actionEvent) {
        lableTest.setText("Кнопка работает!");

    }
}
