package com.example.loto;

import javafx.beans.property.*;
import javafx.beans.value.ObservableValue;

import java.util.Objects;

public class User {

    public StringProperty name;
    public DoubleProperty money;

    public StringProperty getName() {
        return name;
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
    }

}