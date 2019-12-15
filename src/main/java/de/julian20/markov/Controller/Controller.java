package de.julian20.markov.Controller;

import de.julian20.markov.Main;
import de.julian20.markov.MarkovRow;
import javafx.beans.binding.DoubleBinding;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Pane;

public class Controller {
    @FXML
    private TextField frequencyTextField;

    @FXML
    private Button stepButton;

    @FXML
    private TableView markovTableView;

    @FXML
    private TableColumn tKColumn;

    @FXML
    private TableColumn tPhiColumn;

    @FXML
    private TableColumn tPsiColumn;

    @FXML
    private TableColumn tIColumn;

    @FXML
    private TableColumn tJColumn;

    @FXML
    private Pane currentWordBorder;

    @FXML
    private Label currentWordLabel;

    @FXML
    private TextField inputTextField;

    @FXML
    protected void initialize() {
        frequencyTextField.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal)
                changeFrequency();
        });

        //OnInputMethodTextChanged just won't work. I couldn't find a solution nor a reason
        inputTextField.textProperty().addListener((obs, oldVal, newVal) -> {
            Main.getTable().setInput(newVal);
        });

        //Define the elements the cells should represent
        tKColumn.setCellValueFactory(new PropertyValueFactory<MarkovRow, String>("K"));
        tPhiColumn.setCellValueFactory(new PropertyValueFactory<MarkovRow, String>("Phi"));
        tPsiColumn.setCellValueFactory(new PropertyValueFactory<MarkovRow, String>("Psi"));
        tIColumn.setCellValueFactory(new PropertyValueFactory<MarkovRow, String>("I"));
        tJColumn.setCellValueFactory(new PropertyValueFactory<MarkovRow, String>("J"));

        //Define to use a TextField, when a cell is edited
        tPhiColumn.setCellFactory(TextFieldTableCell.<MarkovRow>forTableColumn());
        tPsiColumn.setCellFactory(TextFieldTableCell.<MarkovRow>forTableColumn());
        tIColumn.setCellFactory(TextFieldTableCell.<MarkovRow>forTableColumn());
        tJColumn.setCellFactory(TextFieldTableCell.<MarkovRow>forTableColumn());

        DoubleBinding columnWidthPhiPsi = markovTableView.widthProperty().subtract(tKColumn.widthProperty()).subtract(tIColumn.widthProperty()).subtract(tJColumn.widthProperty()).divide(2).subtract(2);
        tPhiColumn.prefWidthProperty().bind(columnWidthPhiPsi);
        tPsiColumn.prefWidthProperty().bind(columnWidthPhiPsi);

        //Current word border automatic resizing
        currentWordBorder.prefWidthProperty().bind(((Pane) currentWordBorder.getParent()).widthProperty().subtract(454)); //454 is the sum of all gaps before and after the box (everything else has fixed sizes)

        markovTableView.setItems(Main.getData());
    }

    @FXML
    protected void step() {
        Main.getTable().step();
        currentWordLabel.setText(Main.getTable().getCurrentWord());
    }

    @FXML
    protected void changeFrequency() {
        try {
            double freq = Double.parseDouble(frequencyTextField.getText());
            Main.getTable().setFrequency(freq);
            frequencyTextField.setText("" + freq);
        } catch (NumberFormatException exception) {
            frequencyTextField.setText("" + Main.getTable().getFrequency());
        }
    }

    @FXML
    protected void setOnEditCommitColumnPhi(TableColumn.CellEditEvent t) {
        ((MarkovRow) t.getTableView().getItems().get(
                t.getTablePosition().getRow())
        ).setPhi((String) t.getNewValue());
    }

    @FXML
    protected void setOnEditCommitColumnPsi(TableColumn.CellEditEvent t) {
        ((MarkovRow) t.getTableView().getItems().get(
                t.getTablePosition().getRow())
        ).setPsi((String) t.getNewValue());
    }

    @FXML
    protected void setOnEditCommitColumnI(TableColumn.CellEditEvent t) {
        ((MarkovRow) t.getTableView().getItems().get(
                t.getTablePosition().getRow())
        ).setI((String) t.getNewValue());
    }

    @FXML
    protected void setOnEditCommitColumnJ(TableColumn.CellEditEvent t) {
        ((MarkovRow) t.getTableView().getItems().get(
                t.getTablePosition().getRow())
        ).setJ((String) t.getNewValue());
    }
}
