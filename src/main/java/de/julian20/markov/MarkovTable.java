package de.julian20.markov;

import java.util.ArrayList;
import java.util.List;

public class MarkovTable {
    private List<MarkovRow> table = new ArrayList<>();

    private boolean checkEpsilonAtStartEndPhi = false;

    private boolean running = false;
    private String currentWord = ""; //used in markov interpretation and displayed in GUI
    private int currentRow = -1;

    private String input = ""; //text in Input Field, used to initialize currentWord

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
        currentWord = input;
        currentRow = 0;
        running = true;
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

    public String getCurrentWord() {
        return currentWord;
    }

    public void setInput(String input) {
        this.input = input;
    }
}
