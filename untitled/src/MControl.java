import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
        this.action();
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
        }

        p.revalidate();
        p.repaint();
    }

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
            // keine Aktion bei ungültiger Zahl
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        m.setSpielerZahl(z);
        if (m.zahlIsValid(z) && (m.getGesamtPunkte() < 100 && m.getGesamtPunkte() > 0)) {
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