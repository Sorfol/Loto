package com.example.loto;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class ShowLog {

    public ObservableList<User> personData;

    public void setGameCount(int gameCount) {
        this.gameCount = gameCount;
    }

    public int gameCount;
    private final Pattern intPattern = Pattern.compile("-?[0-9]+");
    // this could probably be improved: demo purposes only
    private final Pattern doublePattern = Pattern.compile("-?(([0-9]+)|([0-9]*\\.[0-9]+))");

    public void start(Stage primaryStage) {
        TableView<List<Object>> table = new TableView<>();
        String[][] rawData = generateTestData();
        int numCols = computeMaxRowLength(rawData);

        Class<?>[] types = new Class<?>[numCols];

        for (int columnIndex = 0 ; columnIndex < numCols ; columnIndex++) {
            String[] column = extractColumn(rawData, columnIndex);
            types[columnIndex] = deduceColumnType(column);
            table.getColumns().add(createColumn(types[columnIndex], columnIndex));
        }

        for (int rowIndex = 0 ; rowIndex < rawData.length ; rowIndex++) {
            List<Object> row = new ArrayList<>();
            for (int columnIndex = 0 ; columnIndex < numCols ; columnIndex++) {
                row.add(getDataAsType(rawData[rowIndex], types[columnIndex], columnIndex));
            }
            table.getItems().add(row);
        }

        Scene scene = new Scene(table, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Object getDataAsType(String[] row, Class<?> type, int columnIndex) {
        if (type == Integer.class) {
            if (columnIndex < row.length) {
                return Integer.valueOf(row[columnIndex]);
            } else {
                return 0;
            }
        } else if (type == Double.class) {
            if (columnIndex < row.length) {
                return Double.valueOf(row[columnIndex]);
            } else {
                return 0.0;
            }
        } else {
            if (columnIndex < row.length) {
                return row[columnIndex];
            } else {
                return "" ;
            }
        }
    }

    private TableColumn<List<Object>, ?> createColumn(Class<?> type, int index) {

        String text = personData.get(index).getName().getValue();
        //String text = "Column "+(index+1);

        if (type==Integer.class) {
            TableColumn<List<Object>, Number> col = new TableColumn<>(text);
            col.setCellValueFactory(data -> new SimpleIntegerProperty((Integer)data.getValue().get(index)));
            return col ;
        } else if (type == Double.class) {
            TableColumn<List<Object>, Number> col = new TableColumn<>(text);
            col.setCellValueFactory(data -> new SimpleDoubleProperty((Double)data.getValue().get(index)));
            return col ;
        } else {
            TableColumn<List<Object>, String> col = new TableColumn<>(text);
            col.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().get(index).toString()));
            return col ;
        }
    }

    private Class<?> deduceColumnType(String[] column) {
        boolean allInts = true ;
        boolean allDoubles = true ;
        for (int i = 0 ; i < column.length ; i++) {
            allInts = allInts && intPattern.matcher(column[i]).matches() ;
            allDoubles = allDoubles && doublePattern.matcher(column[i]).matches() ;
        }
        if (allInts) {
            return Integer.class ;
        }
        if (allDoubles) {
            return Double.class ;
        }
        return String.class ;
    }

    private int computeMaxRowLength(Object[][] array) {
        int maxLength = Integer.MIN_VALUE ;
        for (int i = 0 ; i < array.length ; i++) {
            if (array[i].length > maxLength) {
                maxLength = array[i].length ;
            }
        }
        return maxLength ;
    }

    private String[] extractColumn(String[][] data, int columnIndex) {
        String[] column = new String[data.length];
        for (int i = 0 ; i < data.length ; i++) {
            if (columnIndex < data[i].length) {
                column[i]=data[i][columnIndex];
            } else {
                column[i]="";
            }
        }
        return column ;
    }

    private String[][] generateTestData() {

        String[][] logGames = new String[gameCount][personData.size()];

        for (int i=0; i<gameCount; i++){
            for (int j = 0; j<personData.size(); j++){
                logGames[i][j] = personData.get(j).getMoneyLogBet(i).toString();
            }
        }

        return logGames;

    }

    public void setPersonData(ObservableList<User> pData){
        personData = pData;
    }

}
