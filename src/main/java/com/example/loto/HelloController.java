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
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
    @FXML
    private CategoryAxis xAxis;
    @FXML
    private Label welcomeText;
    @FXML
    private Label bbText;

    private XYChart.Series<String, Integer> series = new XYChart.Series<>();

    private int counter = 0;
    private ObservableList<User> personData = FXCollections.observableArrayList();
    private ObservableList<String> personNames = FXCollections.observableArrayList();
    @FXML
    private void initialize() {
        // Инициализация таблицы адресатов с двумя столбцами.
        nametab.setCellValueFactory(cellData -> cellData.getValue().getName());
        moneytab.setCellValueFactory(cellData -> cellData.getValue().getMoney());

        tableUsers.setItems(personData);

        ///

        // Преобразуем его в список и добавляем в наш ObservableList месяцев.
        personNames.addAll(Arrays.asList(personData.getClass().getName()));

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

        counter++;
    }

    @FXML
    private void onAddMoney() {

        int selectedIndex = tableUsers.getSelectionModel().getSelectedIndex();
        User curUser = tableUsers.getItems().get(selectedIndex);
        curUser.setMoney(curUser.getMoney().doubleValue()+ 300);
    }

    @FXML
    private void onDelete() {
        int selectedIndex = tableUsers.getSelectionModel().getSelectedIndex();
        tableUsers.getItems().remove(selectedIndex);
    }

    public void onShowDgr(ActionEvent actionEvent) {

        for (int i = 0; i < 5; i++) {
            series.getData().add(new XYChart.Data<>(personData.get(i).getName().toString(), i));
        }

        barUsers.getData().add(series);
    }
}