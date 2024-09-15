import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MControl implements ActionListener, DocumentListener {
    private MPanel p;
    private MFrame f;
    private GewinnModel m;

    public MControl() {
        this.p = new MPanel(this);
        this.f = new MFrame(p);
        this.m = new GewinnModel();
        this.p.zahl.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (p.nochmal.isEnabled()) {
            this.action();
        }
    }

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

    public static void main(String[] args) {
        MControl c = new MControl();
    }

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

    @Override
    public void removeUpdate(DocumentEvent e) {
        insertUpdate(e);
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        insertUpdate(e);
    }
}
