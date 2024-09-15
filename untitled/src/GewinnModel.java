import java.util.Random;
/**
 * Ganze Logik hinter dem Spiel
 * @author Clarissa Czipin
 * @version 2024-09-15
 */
public class GewinnModel {
    int spielerZahl;
    int computerZahl;
    int gesamtPunkte;
    int rundenErgebnis;

    public GewinnModel(){
        this.gesamtPunkte = 30;
        this.rundenErgebnis = 0;
    }
    public int getGesamtPunkte(){
        return this.gesamtPunkte;
    }
    public int getComputerZahl(){
        return this.computerZahl;
    }
    public int getRundenErgebnis(){
        return this.rundenErgebnis;
    }
    public void berechneComputerZahl(){
        Random rn = new Random();
        this.computerZahl = rn.nextInt(9)+1;
    }
    public void berechneRunde(int spielerZahl){
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
        }
    }
    public boolean zahlIsValid(int z){
        if(z > 9 || z < 1){
            return false;
        }
        return true;
    }
}
