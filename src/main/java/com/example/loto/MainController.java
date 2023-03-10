package com.example.loto;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;


public class MainController {
    public TextField curBet;
    public TableColumn<User, Number> bettab;
    public Button justButton;
    @FXML
    private TableView<User> tableUsers;
    @FXML
    private TableColumn<User, String> nametab = new TableColumn<>("First Name");;
    @FXML
    private TableColumn<User, Number> moneytab;
    @FXML
    private BarChart<String, Integer> barUsers;

    private XYChart.Series<String, Integer> series;

    private int gameNumber = 0;

    private Boolean isDrgCreated = false;


    private int cntUsers;
    private final ObservableList<User> personData = FXCollections.observableArrayList();
    private final ObservableList<String> personNames = FXCollections.observableArrayList();
    @FXML
    private void initialize() {

        nametab.setCellValueFactory(cellData -> cellData.getValue().getName());
        moneytab.setCellValueFactory(cellData -> cellData.getValue().getMoney());
        bettab.setCellValueFactory(cellData -> cellData.getValue().curBetProperty());
        tableUsers.setItems(personData);

        series = new XYChart.Series<>();
        CategoryAxis xAxis = new CategoryAxis();
        personNames.add(personData.getClass().getCanonicalName());
        xAxis.setCategories(personNames);
        curBet.setText("150");

        tableUsers.setEditable(true);

        nametab.setCellFactory(TextFieldTableCell.forTableColumn());
        nametab.setOnEditCommit(
                (TableColumn.CellEditEvent<User, String> t) -> t.getTableView().getItems().get(
                        t.getTablePosition().getRow()).setName(t.getNewValue()));



    }

    @FXML
    private void onHelloButtonClick() {
        onChangeDgr();

        // ?????? ?????? ?????????? ?? ????????????????????????????
        if (cntUsers < 8) {
            switch (cntUsers) {
                case 0:
                    personData.add(new User("????????"));
                    break;
                case 1:
                    personData.add(new User("????????"));
                    break;
                case 2:
                    personData.add(new User("????????????"));
                    break;
                case 3:
                    personData.add(new User("????????"));
                    break;
                case 4:
                    personData.add(new User("??.????????????"));
                    break;
                case 5:
                    personData.add(new User("??.????????"));
                    break;
                case 6:
                    personData.add(new User("????????"));
                    break;
                case 7:
                    personData.add(new User("????????????"));
                    break;
                default:
                    personData.add(new User("????????"));
                    break;

            }
            String user = personData.get(cntUsers).getName().getValue();
            Integer money = personData.get(cntUsers).getMoney().intValue();

            series.getData().add(new XYChart.Data<>(user, money));
        }

        cntUsers++;
    }

    @FXML
    private void onAddBet() {

        int selectedIndex = tableUsers.getSelectionModel().getSelectedIndex();
        User curUser = tableUsers.getItems().get(selectedIndex);
        curUser.setCurBet(curUser.getCurBet() + 50);
        onCreateDgr();
    }

    @FXML
    private void onPickUpBet() {

        int selectedIndex = tableUsers.getSelectionModel().getSelectedIndex();
        User curUser = tableUsers.getItems().get(selectedIndex);
        curUser.setCurBet(curUser.getCurBet() - 50);
        onCreateDgr();
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

        if (!isDrgCreated){
            for (User personDatum : personData) {
                series.getData().add(new XYChart.Data<>(personDatum.getName().getValue(), personDatum.getMoney().intValue()));
            }

            barUsers.getData().add(series);
            isDrgCreated = true;
        }
    }

    public void onInputBet() {

        for (int i=0; i < tableUsers.getItems().size(); i++){
            tableUsers.getItems().get(i).setCurBet(Double.parseDouble(curBet.getText()));
        }

        onCreateDgr();
        onChangeDgr();
    }

    public void onSetWinner() {

        int selectedIndex = tableUsers.getSelectionModel().getSelectedIndex();
        User winUser = tableUsers.getItems().get(selectedIndex);
        double finFond = 0.0;
        for (int i=0; i < tableUsers.getItems().size(); i++){
            User curUser = tableUsers.getItems().get(i);

            if (i != selectedIndex){
                finFond += curUser.getCurBet();
                curUser.setMoney(curUser.getMoney().doubleValue()
                        - curUser.getCurBet());

                curUser.addMoneyLogBet(-curUser.getCurBet(), gameNumber);
            }
            tableUsers.getItems().get(i).setCurBet(0);
        }

        winUser.addMoneyLogBet(finFond, gameNumber);
        winUser.setMoney(winUser.getMoney().doubleValue() + finFond);
        onChangeDgr();

        gameNumber++;
    }

    public void onClear() {


    }

    public void onShowLog(ActionEvent event) throws IOException {


        FXMLLoader fxmlLoader = new FXMLLoader(LotoApplication.class.getResource("LogView.fxml"));
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root, 600, 400);
        Stage stage = new Stage();
        stage.setTitle("?????? ??????");
        stage.setScene(scene);
        stage.show();

        LogController logController = fxmlLoader.getController();
        logController.setGriedParam(personData, gameNumber);


        //ActionEvent actionEvent = new ActionEvent();
        //logController.onHelloButtonClick(actionEvent);


    }
}