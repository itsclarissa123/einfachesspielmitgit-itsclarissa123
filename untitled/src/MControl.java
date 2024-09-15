import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * MVC Control
 * @author Clarissa Czipin
 * @version 2024-09-15
 */
public class MControl implements ActionListener, KeyListener {
    private MPanel p;
    private MFrame f;
    private GewinnModel m;

    public MControl(){
        this.p = new MPanel(this);
        this.f = new MFrame(p);
        this.m = new GewinnModel();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        action();

    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyCode()==KeyEvent.VK_ENTER){
            action();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode()==KeyEvent.VK_ENTER){
            action();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //nix
    }

    public void action(){
        int spielerZahl = p.getZahl();
        if(m.zahlIsValid(spielerZahl)) {
            m.berechneRunde(spielerZahl);
            int[] z = new int[3];
            z[0] = m.getComputerZahl();
            z[1] = m.getGesamtPunkte();
            z[2] = m.getRundenErgebnis();
            p.setZahlen(z);
            p.reset(this);
        }else{
            p.clear(); // löscht Computer Zahl und akt Punkte
            p.nochmal.setEnabled(false);
        }
    }
    /**
     * Main-Methode
     * @param args Commandline Parameter
     */
    public static void main(String[] args) {
        MControl c = new MControl();
    }
}