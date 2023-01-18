package com.example.loto;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class LogController {

    public Label test;

    public LogController(ObservableList<User> personData) {


    }

    @FXML
    private void initialize() {

    }

    public void setMainApp(LotoApplication mainApp) {
        // this.mainApp = mainApp;

        // pass the main app to the drawerContentController:
        //LogController.setMainApp(mainApp);
    }
}
