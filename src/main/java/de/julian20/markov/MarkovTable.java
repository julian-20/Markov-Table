package de.julian20.markov;

import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

public class MarkovTable {
    private TextField inputField = (TextField) Main.getStage().getScene().lookup("#input");
    private List<MarkovRow> table = new ArrayList<>();

    private boolean checkEpsilonAtStartEndPhi = false;

    private boolean running = false;
    private String currentWord = null;
    private int currentRow = -1;

    private double frequency = 1;

    private MarkovRow getMarkovRow(int k) {
        MarkovRow search = null;
        for (MarkovRow r : table) {
            if (Integer.parseInt(r.getK()) == k) {
                search = r;
                break;
            }
        }
        return search;
    }

    private boolean isRowExisting(int k) {
        return getMarkovRow(k) != null;
    }

    public void initialize() {
        currentWord = Main.getInputField().getText();
        currentRow = 0;
        running = true;
        Main.getCurrentWordLabel().setText(currentWord);
    }

    public void step() {
        if (!running)
            initialize();
        else {
            MarkovRow nextRow = getMarkovRow(currentRow);
            currentRow = nextRow.getNextRow(currentWord);
            currentWord = nextRow.transform(currentWord);
            if (!isRowExisting(currentRow)) {
                running = false;
            }
            Main.getCurrentWordLabel().setText(currentWord);
        }

    }

    public boolean isEpsilonAtStartEndPhi() {
        return checkEpsilonAtStartEndPhi;
    }

    public void setEpsilonAtStartEndPhi(boolean checkEpsilonAtStartEndPhi) {
        this.checkEpsilonAtStartEndPhi = checkEpsilonAtStartEndPhi;
    }

    public void setTable(List<MarkovRow> table) {
        this.table = table;
    }

    public double getFrequency() {
        return frequency;
    }

    public void setFrequency(double frequency) {
        this.frequency = frequency;
    }
}
