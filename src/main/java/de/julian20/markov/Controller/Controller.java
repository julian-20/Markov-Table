package de.julian20.markov.Controller;

import de.julian20.markov.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Controller {
    @FXML
    private TextField frequencyTextField;

    @FXML
    private Button stepButton;

    @FXML
    public void initialize() {
        frequencyTextField.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal)
                changeFrequency();
        });
    }

    @FXML
    protected void step() {
        Main.getTable().step();
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
}
