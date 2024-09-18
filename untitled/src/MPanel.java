import javax.swing.*;
import java.awt.*;

/**
 * MVC Panel/View
 * @author Clarissa Czipin
 * @version 20224-09-15
 */
public class MPanel extends JPanel{
    /* Der Nocheinmal-Button zum Wiederholen des Spieles */
    public JButton nochmal = new JButton();
    /* Das Textfeld, indem der User seine Zahl eingibt */
    public JTextField zahl = new JTextField();
    /* Die fixen Texte die den Wert beschreiben, der darunter steht */
    public JLabel[] fixtexte = new JLabel[4];
    /* Die Aktuellen und gesamten Punkte */
    public JLabel[] punkte = new JLabel[2];
    /* Die generierte Zahl vom Computer */
    public JLabel computer = new JLabel();

    /**
     * Konstruktor für MPanel-Klasse
     * @param c Controlklasse für Events
     */
    public MPanel(MControl c){
        this.punkte[0] = new JLabel("30");
        this.punkte[1] = new JLabel("");
        clear();
        reset(c);
    }

    /**
     * setzt die Zahlen, und das generelle
     * Layout neu zusammen und hier wird es
     * auch erst erstellt.
     * @param c die Control Klasse für die Events
     */
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

    /**
     * Setzt die Farben der Felder, je nach übergebener Zahlen.
     * Setzt die Zahlen in die richtige Box.
     * Setzt einen Text, wenn die Gesamtpunkte > 100 oder < 0 sind.
     *
     * @param z die Zahlenwerte, die aus der Berechnung heraus kamen
     */
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

    /**
     * Die Fixtexte, werden festgelegt.
     * Fixtexte sind jene Texte, die sich
     * im Laufe des Spieles nicht verändern.
     */
    public void setFixtexte(){
        this.fixtexte[0] = new JLabel("Punkte gesamt:");
        this.fixtexte[1] = new JLabel("Aktuelles Ergebnis:");
        this.fixtexte[2] = new JLabel("Zahl für die nächste Runde:");
        this.fixtexte[3] = new JLabel("Computerzahl:");
    }

    /**
     * Hier werden alle wichtigen Einstellungen
     * Für den noch einmal BUtton getroffen.
     * ZB. setzten des Buttons, Listeners hinzufügen,
     * der button ist vorerst nicht drückbar.
     * @param c die Klasse der Listeners
     */
    public void setNochmal(MControl c){
        this.nochmal = new JButton("Noch einmal!");
        this.nochmal.addActionListener(c);
        this.zahl.getDocument().addDocumentListener(c);
        this.zahl.setHorizontalAlignment(JTextField.CENTER);
        Font font = new Font("Arial", Font.PLAIN, 24);
        this.zahl.setFont(font);
        this.nochmal.setEnabled(false);
    }

    /**
     * Hier werden alle Punkte bis auf die
     * Spielerzahl und die Computerzahl,
     * und die Fixtexte
     * in die Panels geschrieben und
     * die Standardfarbe gesetzt.
     * @return ein vollständiges Panel der ersten drei Zeilen
     *          inkl akt und gesamt Punkten und den dixen Texten.
     */
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
        this.fixtexte[3].setHorizontalAlignment(JTextField.CENTER);

        g2.setBackground(Color.WHITE);
        g1.setBackground(Color.WHITE);
        return g;
    }

    /**
     * Hier werden die Computer Zahl und die
     * Spielerzahl in ein Panel geschrieben und
     * zusammengefasst.
     * @return ein Panel mit Computer- und Spielerzahl
     */
    public JPanel setZahlen(){
        JPanel g = new JPanel();
        g.setLayout(new GridLayout(1,2));
        g.add(zahl);
        g.add(computer);
        g.setBackground(Color.WHITE);
        return g;
    }

    /**
     * setzt die Werte der aktuellen Punkte
     * Computerzahl, und die Userzahl werden
     * wieder leer angezeigt und die Farbe der
     * aktuellen Punkte wird wieder auf weiß gesetzt
     */
    public void clear(){
        this.zahl.setText("");
        this.computer.setText("");
        this.computer.setHorizontalAlignment(JTextField.CENTER);
        Font font = new Font("Arial", Font.PLAIN, 24);
        this.computer.setFont(font);
        this.punkte[1].setText("");
        if(this.punkte[1].getParent() != null){
            this.punkte[1].getParent().setBackground(Color.WHITE);
            this.punkte[1].setHorizontalAlignment(JTextField.CENTER);
        }
    }

    /**
     * Die Zahl aus dem Textfeld wird aus
     * dem Textfeld und in einen Int geparst.
     * @return die eingegebene Userzahl
     */
    public int getZahl() {
        int i = 11;
        try {
            i = Integer.parseInt(zahl.getText());
        }catch(NumberFormatException n){
        }
        return i;
    }
}
