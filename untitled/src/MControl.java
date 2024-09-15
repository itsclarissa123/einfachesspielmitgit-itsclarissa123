import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * MVC Control
 * @author Clarissa Czipin
 * @version 2024-09-15
 */
public class MControl implements ActionListener, KeyListener , DocumentListener {
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
        this.action();

    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyCode()==KeyEvent.VK_ENTER){
            this.action();
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

    public void action() {
        int spielerZahl = p.getZahl();
        m.berechneRunde(spielerZahl);

        // Zahlen für die GUI aktualisieren
        int[] z = new int[3];
        z[0] = m.getComputerZahl();
        z[1] = m.getGesamtPunkte();
        z[2] = m.getRundenErgebnis();

        // Setze die neuen Werte und aktualisiere die GUI
        p.setZahlen(z);
        p.revalidate();  // Erzwinge Neuanordnung der Komponenten
        p.repaint();     // Erzwinge Neuzeichnen der GUI
    }

    /**
     * Main-Methode
     * @param args Commandline Parameter
     */
    public static void main(String[] args) {
        MControl c = new MControl();
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        Document d = e.getDocument();
        int z = 11;
        try {
            String inputText = d.getText(0, d.getLength());
            z = Integer.parseInt(inputText);
        } catch (NumberFormatException ex) {

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        m.setSpielerZahl(z);
        if (m.zahlIsValid(z)) {
            p.nochmal.setEnabled(true);
        } else {
            p.nochmal.setEnabled(false);
            p.clear();
        }
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        insertUpdate(e);
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        insertUpdate(e);
    }
}