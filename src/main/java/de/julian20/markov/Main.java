package de.julian20.markov;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private static Stage stage = null;

    private static MarkovTable table = null;
    private static ObservableList<MarkovRow> data = null;

    public static void main(String[] args) {
        table = new MarkovTable();
        data = FXCollections.observableArrayList(
                new MarkovRow(table, "0", "ba", "ab", "0", "1"),
                new MarkovRow(table, "1", "ca", "ac", "0", "2"),
                new MarkovRow(table, "2", "cb", "bc", "0", "3"),
                new MarkovRow(table, "3", "da", "ad", "0", "4"),
                new MarkovRow(table, "4", "db", "bd", "0", "5"),
                new MarkovRow(table, "5", "dc", "cd", "0", "6"));
        table.setTable(data);
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("gui.fxml"));
            Scene scene = new Scene(root);

            stage.setTitle("Markov-Table");
            stage.setScene(scene);
            stage.show();
            this.stage = stage;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ObservableList<MarkovRow> getData() {
        return data;
    }

    public static Stage getStage() {
        return stage;
    }

    public static MarkovTable getTable() {
        return table;
    }
}
