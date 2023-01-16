package com.example.loto;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.DateFormatSymbols;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class HelloController {
    public Button showDiagr;
    public TextField curBet;
    public TableColumn<User, Number> bettab;
    @FXML
    private TableView<User> tableUsers;
    @FXML
    private TableColumn<User, String> nametab;
    @FXML
    private TableColumn<User, Number> moneytab;
    @FXML
    private BarChart<String, Integer> barUsers;

    private XYChart.Series<String, Integer> series;

    private int counter = 0;
    private final ObservableList<User> personData = FXCollections.observableArrayList();
    private final ObservableList<String> personNames = FXCollections.observableArrayList();
    @FXML
    private void initialize() {
        // Инициализация таблицы адресатов с двумя столбцами.
        nametab.setCellValueFactory(cellData -> cellData.getValue().getName());
        moneytab.setCellValueFactory(cellData -> cellData.getValue().getMoney());
        bettab.setCellValueFactory(cellData -> cellData.getValue().curBetProperty());

        tableUsers.setItems(personData);

        series = new XYChart.Series<>();
        // Кусок с сайта https://betacode.net/11107/javafx-barchart
        CategoryAxis xAxis = new CategoryAxis();

        xAxis.setLabel("Programming Language");

        personNames.add(personData.getClass().getCanonicalName());

        xAxis.setCategories(personNames);

        curBet.setText("300");

    }

    @FXML
    private void onHelloButtonClick() throws InterruptedException {
        onChangeDgr();
        switch (counter){
            case 0:
                personData.add(new User("Ляля"));
                break;
            case 1:
                personData.add(new User("Прян"));
                break;
            case 2:
                personData.add(new User("Максим"));
                break;
            case 3:
                personData.add(new User("Вера"));
                break;
            case 4:
                personData.add(new User("Буфя"));
                break;
            case 5:
                personData.add(new User("Кефя"));
                break;
        }

        String user = personData.get(counter).getName().getValue();
        Integer money = personData.get(counter).getMoney().intValue();
        Integer curBet = personData.get(counter).curBetProperty().intValue();

        series.getData().add(new XYChart.Data<>(user, money));

        counter++;
    }

    @FXML
    private void onAddMoney() {

        int selectedIndex = tableUsers.getSelectionModel().getSelectedIndex();
        User curUser = tableUsers.getItems().get(selectedIndex);
        curUser.setMoney(curUser.getMoney().doubleValue() + 300);
        onChangeDgr();
    }

    @FXML
    private void onPickUpMoney() {

        int selectedIndex = tableUsers.getSelectionModel().getSelectedIndex();
        User curUser = tableUsers.getItems().get(selectedIndex);
        curUser.setMoney(curUser.getMoney().doubleValue() - 300);
        onChangeDgr();
    }
    @FXML
    private void onDelete() {
        int selectedIndex = tableUsers.getSelectionModel().getSelectedIndex();

        tableUsers.getItems().remove(selectedIndex);
        personData.remove(selectedIndex);
        personNames.remove(selectedIndex);
        series.getData().remove(selectedIndex);

    }

    public void onChangeDgr() {
        series.getData().clear();
        for (User personDatum : personData) {
            series.getData().add(new XYChart.Data<>(personDatum.getName().getValue(), personDatum.getMoney().intValue()));
        }
        barUsers.getData();


    }

    public void onCreateDgr(){

        for (User personDatum : personData) {
            series.getData().add(new XYChart.Data<>(personDatum.getName().getValue(), personDatum.getMoney().intValue()));
        }

        barUsers.getData().add(series);
        showDiagr.setDisable(true);
    }

    public void onInputBet() {

        for (int i=0; i < tableUsers.getItems().size(); i++){

            User curUser = tableUsers.getItems().get(i);
            double value = Double.parseDouble(curBet.getText());
            tableUsers.getItems().get(i).setMoney(curUser.getMoney().doubleValue() + value);
        }

        onChangeDgr();

    }
}