package de.julian20.markov;

import java.util.ArrayList;
import java.util.List;

public class MarkovTable {
    private boolean checkEpsilonAtStartEndPhi;
    private List<MarkovRow> table = new ArrayList<>();

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

    public boolean isEpsilonAtStartEndPhi() {
        return checkEpsilonAtStartEndPhi;
    }

    public void setEpsilonAtStartEndPhi(boolean checkEpsilonAtStartEndPhi) {
        this.checkEpsilonAtStartEndPhi = checkEpsilonAtStartEndPhi;
    }
}
