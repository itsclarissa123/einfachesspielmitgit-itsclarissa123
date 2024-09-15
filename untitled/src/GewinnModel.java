import java.util.Random;

/**
 * Ganze Logik hinter dem Spiel
 * @author Clarissa Czipin
 * @version 2024-09-15
 */
public class GewinnModel {
    private int spielerZahl;
    private int computerZahl;
    private int gesamtPunkte;
    private int rundenErgebnis;

    public GewinnModel() {
        this.gesamtPunkte = 30;
        this.rundenErgebnis = 0;
    }

    public int getGesamtPunkte() {
        return this.gesamtPunkte;
    }

    public void setSpielerZahl(int z) {
        this.spielerZahl = z;
    }

    public int getComputerZahl() {
        return this.computerZahl;
    }

    public int getRundenErgebnis() {
        return this.rundenErgebnis;
    }

    public void berechneComputerZahl() {
        Random rn = new Random();
        this.computerZahl = rn.nextInt(9) + 1;
    }

    public void berechneRunde(int spielerZahl) {
        this.setSpielerZahl(spielerZahl); // Ensure the player number is set before calculation
        berechneComputerZahl();
        int genau = 20;
        int fast = 5;
        int nicht = -10;

        if (zahlIsValid(spielerZahl)) {
            if (this.computerZahl == this.spielerZahl) {
                this.rundenErgebnis = genau;
                this.gesamtPunkte += genau;
            } else if (this.computerZahl - 1 == this.spielerZahl || this.computerZahl + 1 == this.spielerZahl) {
                this.rundenErgebnis = fast;
                this.gesamtPunkte += fast;
            } else {
                this.rundenErgebnis = nicht;
                this.gesamtPunkte += nicht;
            }

            // Ensure gesamtPunkte does not fall below zero
            if (this.gesamtPunkte < 0) {
                this.gesamtPunkte = 0;
            }
        } else {
            System.err.println("Ungültige Zahl eingegeben: " + spielerZahl);
        }
    }

    public boolean zahlIsValid(int z) {
        return z >= 1 && z <= 9;
    }
}
