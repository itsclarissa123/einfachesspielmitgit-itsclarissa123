import javax.swing.*;
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
    public int i = 0;

    public MPanel(MControl c){ reset(c);}
    public void reset(MControl c){
        this.setLayout(new BorderLayout());
        clear();
        setFixtexte();
        setNochmal(c);
        JPanel a = setPoints(this.punkte);
        JPanel b = setZahlen();
        JPanel d = new JPanel();
        d.setLayout(new GridLayout(2,1));
        d.add(a);
        d.add(b);
        this.add(d, BorderLayout.CENTER);
        this.add(this.nochmal, BorderLayout.SOUTH);


    }
    public void setZahlen(int[] z){
        if(i == 0){

        }else {
            this.punkte[0] = new JLabel("" + z[0]);
            this.punkte[1] = new JLabel("" + z[1]);
            this.computer = new JLabel("" + z[2]);
        }
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
        this.nochmal.setEnabled(false);
    }
    public JPanel setPoints(JLabel[] punkte){
        JPanel g = new JPanel();
        g.setLayout(new GridLayout(3,2));
        g.add(this.fixtexte[0]).setBackground(Color.GRAY);
        g.add(this.fixtexte[1]).setBackground(Color.GRAY);
        g.add(punkte[0]);
        g.add(punkte[1]);
        g.add(this.fixtexte[2]).setBackground(Color.GRAY);
        g.add(this.fixtexte[3]).setBackground(Color.GRAY);
        return g;
    }

    public JPanel setZahlen(){
        JPanel g = new JPanel();
        g.setLayout(new GridLayout(1,2));
        g.add(zahl);
        g.add(computer);
        return g;
    }

    public void clear(){
        this.computer = new JLabel("");
        this.punkte[1] = new JLabel("");
        this.zahl = new JTextField("");
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
