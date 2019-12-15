package de.julian20.markov;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class Controller {

    @FXML
    private Button stepButton;

    @FXML
    protected void step() {
        Main.getTable().step();
    }
}
