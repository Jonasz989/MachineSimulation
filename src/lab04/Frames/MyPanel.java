package lab04.Frames;

import lab04.Objects.GObject;

import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.io.Serial;
import javax.swing.JPanel;

public class MyPanel extends JPanel {

    public GObject g;
    @Serial
    private static final long serialVersionUID = 1L;

    public MyPanel() { //tworzenie panelu
        this.setSize(800, 800);
        this.setLayout(new GridBagLayout());
        g = new Manipulator(); //nowy obiekt g do manipulatora, ktory potem wyswietlamy

    }

    @Override
    protected void paintComponent(Graphics arg0) {
        super.paintComponent(arg0);
        if (g != null) {
            g.drawMe(arg0);
        }
    }

}