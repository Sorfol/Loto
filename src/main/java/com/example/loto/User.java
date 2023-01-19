package com.example.loto;

import javafx.beans.property.*;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Objects;

public class User {

    public ObservableList<User> personData = FXCollections.observableArrayList();
    private StringProperty name;
    private DoubleProperty money;
    private DoubleProperty curBet;
    private ArrayList<Double> moneyLog;
    public StringProperty getName() {
        return name;
    }

    public StringProperty nameProperty() {
        return name;
    }

    public DoubleProperty moneyProperty() {
        return money;
    }

    public ArrayList<Double> getMoneyLog() {

        return moneyLog;
    }

    public void addCurGameToLog(Double gameResult) {
        this.moneyLog.add(gameResult);
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public DoubleProperty getMoney() {
        return money;
    }

    public User(String name) {
        this.name = new SimpleStringProperty(name);
        this.money = new SimpleDoubleProperty(0);
        this.curBet = new SimpleDoubleProperty(0);
    }

    public double getCurBet() {
        return curBet.get();
    }

    public DoubleProperty curBetProperty() {
        return curBet;
    }

    public void setCurBet(double curBet) {
        this.curBet.set(curBet);
    }

    public void setMoney(double money) {
        this.money.set(money);
    }
}
