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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.DateFormatSymbols;
import java.util.Arrays;
import java.util.List;

public class HelloController {
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

        tableUsers.setItems(personData);

        series = new XYChart.Series<>();
        // Кусок с сайта https://betacode.net/11107/javafx-barchart
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Programming Language");

        //NumberAxis yAxis = new NumberAxis();
        //yAxis.setLabel("Percent");

        personNames.addAll(Arrays.asList(personData.getClass().getCanonicalName()));

        // Назначаем имена месяцев категориями для горизонтальной оси.
        xAxis.setCategories(personNames);


    }



    @FXML
    private void onHelloButtonClick() {

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

        String test = personData.get(counter).getName().getValue();
        series.getData().add(new XYChart.Data<>(test, personData.get(counter).getMoney().intValue()));

        barUsers.getData().add(series);

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
    }

    public void onChangeDgr() {

        for (int i = 0; i < personData.size(); i++) {
            series.getData().add(new XYChart.Data<>(personData.get(i).getName().getValue(), personData.get(i).getMoney().intValue()));
        }

        barUsers.getData().add(series);
    }

}