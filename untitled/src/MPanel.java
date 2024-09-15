import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

/**
 * MVC Panel/View
 * @author Clarissa Czipin
 * @version 20224-09-15
 */
public class MPanel extends JPanel{
    public JButton nochmal = new JButton();
    public JTextField zahl = new JTextField();
    public JLabel[] fixtexte = new JLabel[4];
    public JLabel[] punkte = new JLabel[2];
    public JLabel computer = new JLabel();

    public MPanel(MControl c){
        this.punkte[0] = new JLabel("30");
        this.punkte[1] = new JLabel("");
        clear();
        reset(c);
    }
    public void reset(MControl c){
        this.setLayout(new BorderLayout());
        setFixtexte();
        setNochmal(c);
        JPanel a = setPoints();
        JPanel b = setZahlen();
        JPanel d = new JPanel();
        d.setLayout(new GridLayout(2,1));
        d.add(a);
        d.add(b);
        this.add(d, BorderLayout.CENTER);
        this.add(this.nochmal, BorderLayout.SOUTH);


    }
    public void setZahlen(int[] z) {
        JPanel ergebnisPanel = (JPanel) this.punkte[1].getParent();
        if (z[2] == 5 || z[2] == 20) {
            this.punkte[1].setText("" + z[2]);
            ergebnisPanel.setBackground(Color.GREEN);
        } else {
            this.punkte[1].setText("" + z[2]);
            ergebnisPanel.setBackground(Color.RED);
        }
        this.computer.setText("" + z[0]);

        JPanel punktePanel = (JPanel) this.punkte[0].getParent();
        if (z[1] >= 100) {
            this.punkte[0].setText("GEWONNEN!");
            punktePanel.setBackground(Color.GREEN);
        } else if (z[1] <= 0) {
            this.punkte[0].setText("VERLOREN!");
            punktePanel.setBackground(Color.RED);
        } else {
            this.punkte[0].setText("" + z[1]);
            punktePanel.setBackground(Color.WHITE);
        }

        this.revalidate();
        this.repaint();
    }


    public void setFixtexte(){
        this.fixtexte[0] = new JLabel("Punkte gesamt:");
        this.fixtexte[1] = new JLabel("Aktuelles Ergebnis:");
        this.fixtexte[2] = new JLabel("Zahl für die nächste Runde:");
        this.fixtexte[3] = new JLabel("Computerzahl:");
    }
    public void setNochmal(MControl c){
        this.nochmal = new JButton("Noch einmal!");
        this.nochmal.addActionListener(c);
        this.zahl.getDocument().addDocumentListener(c);
        this.nochmal.setEnabled(false);
    }
    public JPanel setPoints() {
        JPanel g = new JPanel();
        JPanel g1 = new JPanel();
        JPanel g2 = new JPanel();
        g.setLayout(new GridLayout(3, 2));

        g.add(this.fixtexte[0]);
        g.add(this.fixtexte[1]);
        g1.add(this.punkte[0]);
        g.add(g1);
        g2.add(this.punkte[1]);
        g.add(g2);
        g.add(this.fixtexte[2]);
        g.add(this.fixtexte[3]);

        g2.setBackground(Color.WHITE);
        g1.setBackground(Color.WHITE);
        return g;
    }


    public JPanel setZahlen(){
        JPanel g = new JPanel();
        g.setLayout(new GridLayout(1,2));
        g.add(zahl);
        g.add(computer);
        g.setBackground(Color.WHITE);
        return g;
    }

    public void clear(){
        this.zahl.setText("");
        this.computer.setText("");
        this.punkte[1].setText("");
        if(this.punkte[1].getParent() != null){
            this.punkte[1].getParent().setBackground(Color.WHITE);
        }
    }

    public int getZahl() {
        int i = 11;
        try {
            i = Integer.parseInt(zahl.getText());
        }catch(NumberFormatException n){
        }
        return i;
    }
}
