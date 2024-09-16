import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Die Listener alias control Klasse
 */
public class MControl implements ActionListener, DocumentListener {
    /* Das Panel **/
    private MPanel p;
    /* Das Frame */
    private MFrame f;
    /* Die Logik*/
    private GewinnModel m;

    /**
     * Konstruktor wo die Variablen erstmal
     * gesetzt werden und die der ActionListener
     * zu der Userzahl hingefügt.
     */
    public MControl() {
        this.p = new MPanel(this);
        this.f = new MFrame(p);
        this.m = new GewinnModel();
        this.p.zahl.addActionListener(this);
    }

    /**
     * Die methode action() wird aufgerufen,
     * wenn der Knopf enabled ist
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (p.nochmal.isEnabled()) {
            this.action();
        }
    }

    /**
     * Diese methode holt sich die Nummer aus dem Textfield
     * in der Panelklasse und übergibt sie der Modelklasse,
     * diese berechnet dann die neuen Zahlen und übergibt sie
     * dem Panel, damit sie neu gesetzt werden.
     * Außerdem gibt es ein paar Consolen-Ausgaben,
     * um die Richtigkeit der Ausgaben im GUI zu überprüfen
     */
    public void action() {
        int spielerZahl = p.getZahl();
        m.berechneRunde(spielerZahl);

        int[] z = new int[3];
        z[0] = m.getComputerZahl();
        z[1] = m.getGesamtPunkte();
        z[2] = m.getRundenErgebnis();

        p.setZahlen(z);

        if (z[1] >= 100 || z[1] <= 0) {
            p.nochmal.setEnabled(false);
            this.p.zahl.removeActionListener(this);
        }

        p.revalidate();
        p.repaint();

        // Debug-Ausgabe
        System.out.println("SpielerZahl: " + spielerZahl);
        System.out.println("ComputerZahl: " + z[0]);
        System.out.println("GesamtPunkte: " + z[1]);
        System.out.println("RundenErgebnis: " + z[2]);
    }

    /**
     * Diese Methode wird aufgerufen, wenn
     * in ein Textfeld immer etwas geschrieben wird
     * Es gibt ein paar Debug-Ausgaben, die als diese gekennzeichnet
     * sind hier wird vor allem geschaut ob der Button aktiviert wird
     * oder deaktiviert, wenn eine ungültige Eingabe getätigt wurde.
     * @param e the document event
     */
    @Override
    public void insertUpdate(DocumentEvent e) {
        Document d = e.getDocument();
        try {
            String inputText = d.getText(0, d.getLength());
            int z = Integer.parseInt(inputText);

            // Debug-Ausgabe
            System.out.println("Eingabe: " + inputText);
            System.out.println("Parsed Zahl: " + z);

            if (m.zahlIsValid(z) && (m.getGesamtPunkte() < 100 && m.getGesamtPunkte() > 0)) {
                p.nochmal.setEnabled(true);
            } else {
                p.nochmal.setEnabled(false);
            }
        } catch (NumberFormatException ex) {
            p.nochmal.setEnabled(false);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Wenn etwas aus einem Textfeld gelöscht wird, wird
     * das selbe ausgeführt wie in insertUpdate()
     * @param e the document event
     */
    @Override
    public void removeUpdate(DocumentEvent e) {
        insertUpdate(e);
    }
    /**
     * Wenn etwas in einem Textfeld geändert wird, wird
     * das selbe ausgeführt wie in insertUpdate()
     * @param e the document event
     */
    @Override
    public void changedUpdate(DocumentEvent e) {
        insertUpdate(e);
    }
    /**
     * Hier wird ein neus Control Panel angelegt
     * Das hier ist die main-Methode, die beim
     * Aufruf der Methode ausgeführt wird
     * @param args Commandline argument
     */
    public static void main(String[] args) {
        MControl c = new MControl();
    }
}
