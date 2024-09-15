import javax.swing.*;
/**
 * MVC View/Frame
 * @author Clarissa Czipin
 * @version 2024-09-15
 */
public class MFrame extends JFrame {
    /**
     * Konstruktor für das Frame
     */
    public MFrame(MPanel p){
        super("Gewinnspielo");
        this.add(p);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack(); this.setLocationRelativeTo(null);
        this.setVisible(true);
        setSize(400,200);
    }
}
