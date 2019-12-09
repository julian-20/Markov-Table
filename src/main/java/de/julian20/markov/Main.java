package de.julian20.markov;

import javafx.application.Application;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("gui.fxml"));
            Scene scene = new Scene(root);



            stage.setTitle("Markov-Tafeln");
            stage.setScene(scene);
            stage.show();

            //Table column automatic resizing
            TableView markovTableView = (TableView)stage.getScene().lookup("#markovtable");
            TableColumn tK = null;
            TableColumn tPhi = null;
            TableColumn tPsi = null;
            TableColumn tI = null;
            TableColumn tJ = null;

            for(Object c : markovTableView.getColumns()) {
                TableColumn cT = (TableColumn)c;
                if(cT.getText().equals("k"))
                    tK = cT;
                else if(cT.getText().equals("ϕ"))
                    tPhi = cT;
                else if(cT.getText().equals("Ψ"))
                    tPsi = cT;
                else if(cT.getText().equals("i"))
                    tI = cT;
                else if(cT.getText().equals("j"))
                    tJ = cT;
            }
            DoubleBinding columnWidthPhiPsi = markovTableView.widthProperty().subtract(tK.widthProperty()).subtract(tI.widthProperty()).subtract(tJ.widthProperty()).divide(2);
            tPhi.prefWidthProperty().bind(columnWidthPhiPsi);
            tPsi.prefWidthProperty().bind(columnWidthPhiPsi);

            //Current word border automatic resizing
            Pane currentWordBorder = (Pane)stage.getScene().lookup("#currentwordborder");
            currentWordBorder.prefWidthProperty().bind(((Pane)currentWordBorder.getParent()).widthProperty().subtract(454)); //454 is the sum of all gaps before and after the box (everything else has fixed sizes)
            System.out.println("Hi 1");
            System.out.println("Hi 2");


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
