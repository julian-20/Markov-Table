package de.julian20.markov;

public class MarkovRow {
    private String k;
    private String phi;
    private String psi;
    private String i;
    private String j;

    public MarkovRow(String k, String phi, String psi, String i, String j) {
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
}
