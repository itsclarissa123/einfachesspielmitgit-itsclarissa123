import java.util.Random;

/**
 * Ganze Logik hinter dem Spiel
 * @author Clarissa Czipin
 * @version 2024-09-15
 */
public class GewinnModel {
    /* die Zahl, die der Spieler tippt */
    private int spielerZahl;
    /* die generierte Zahl des Computers */
    private int computerZahl;
    /* Die gesamte Punktanzahl */
    private int gesamtPunkte;
    /* die Punkte Anzahl der Runde */
    private int rundenErgebnis;

    /**
     * Kostruktor des Models
     * Die Standard-Gesamtpunkte, sind 30
     * Runden-Ergebnis ist offensichtlich 0
     */
    public GewinnModel() {
        this.gesamtPunkte = 30;
        this.rundenErgebnis = 0;
    }

    /**
     * Zugriff auf die Gesamtpunkte
     * @return die Gesamtpunkte
     */
    public int getGesamtPunkte() {
        return this.gesamtPunkte;
    }

    /**
     * Zugriff auf die Spielerzahl
     * @param z die Spieler Zahl
     */
    public void setSpielerZahl(int z) {
        this.spielerZahl = z;
    }

    /**
     * Zugriff auf Computerzahl
     * @return Computerzahl
     */
    public int getComputerZahl() {
        return this.computerZahl;
    }

    /**
     * Zugriff auf Rundenergebnis
     * @return Rundenergebnis
     */
    public int getRundenErgebnis() {
        return this.rundenErgebnis;
    }
    /**
     * Die Methode berechneComputerZahl() berechnet
     * eine zufällige Zahl von 1-9 für den Computer
     * und speichert sie in das entsprechende Attribut (computerZahl)
     */
    public void berechneComputerZahl() {
        Random rn = new Random();
        this.computerZahl = rn.nextInt(9) + 1;
    }
    /**
     * Die Methode berechneRunde() übernimmt die eingegebene
     * Zahl und berechnet das Ergebnis für die Runde
     * (+20, +5 oder -10) und den Gesamtpunktestand und speichert
     * diese Werte und die Spielerzahl in die entsprechenden Attribute.
     */
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

    /**
     * schaut nur ob eine Zahl im Bereich von 1 bis 9 liegt
     * @param z zu überprüfende Zahl
     * @return ob die zahll zwischen 1 und 9 liegt
     */
    public boolean zahlIsValid(int z) {
        return z >= 1 && z <= 9;
    }
}
