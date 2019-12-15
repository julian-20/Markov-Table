package de.julian20.markov;

public class MarkovRow {
    private String k;
    private String phi;
    private String psi;
    private String i;
    private String j;
    private MarkovTable table;

    public MarkovRow(MarkovTable table, String k, String phi, String psi, String i, String j) {
        this.table = table;
        this.k = k;
        this.phi = phi;
        this.psi = psi;
        this.i = i;
        this.j = j;
    }

    public String getK() {
        return k;
    }

    public void setK(String k) {
        this.k = k;
    }

    public String getPhi() {
        return phi;
    }

    public void setPhi(String phi) {
        this.phi = phi;
    }

    public String getPsi() {
        return psi;
    }

    public void setPsi(String psi) {
        this.psi = psi;
    }

    public String getI() {
        return i;
    }

    public void setI(String i) {
        this.i = i;
    }

    public String getJ() {
        return j;
    }

    public void setJ(String j) {
        this.j = j;
    }

    public boolean isESingle() {
        return phi.length() == 1 && phi.charAt(0) == 'e';
    }

    public boolean isEStart() {
        return table.isEpsilonAtStartEndPhi() && !isESingle() && phi.length() > 0 && phi.charAt(0) == 'e';
    }

    public boolean isEEnd() {
        return table.isEpsilonAtStartEndPhi() && !isEStart() && phi.length() > 0 && phi.charAt(phi.length() - 1) == 'e';
    }

    public int getNextRow(String input) {
        if (isESingle()) {
            return Integer.parseInt(i);
        } else if (isEStart()) {
            if (input.indexOf(phi.substring(1)) == 0)
                return Integer.parseInt(i);
            else
                return Integer.parseInt(j);
        } else if (isEEnd()) {
            if (input.length() - phi.length() >= 0
                    && input.substring(input.length() - phi.length() + 1).equals(phi.substring(0, phi.length() - 1)))
                return Integer.parseInt(i);
            else
                return Integer.parseInt(j);
        } else {
            if (input.indexOf(phi) > -1)
                return Integer.parseInt(i);
            else
                return Integer.parseInt(j);
        }
    }

    public String transform(String input) {
        int pos = -1;
        if (isESingle()) {
            pos = 0;
        } else if (isEStart()) {
            pos = input.indexOf(phi.substring(1));
            if (pos != 0)
                pos = -1;
        } else if (isEEnd()) {
            if (input.length() - phi.length() + 1 >= 0
                    && input.substring(input.length() - phi.length() + 1).equals(phi.substring(0, phi.length() - 1)))
                pos = input.length() - phi.length() + 1;
        } else {
            pos = input.indexOf(phi);
        }
        if (pos > -1)
            return input.substring(0, pos)
                    + psi
                    + input.substring(pos + (isESingle() || isEStart() || isEEnd() ? phi.length() - 1 : phi.length()));
        else
            return input;
    }
}
