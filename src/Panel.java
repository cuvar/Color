import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class Panel extends JPanel{

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Main.setRGB();
        g.setColor(new Color(Main.red, Main.green, Main.blue));
        g.fillRect(Main.width/2 - 185, 25, 370, 170);

        Toolkit.getDefaultToolkit().sync();
    }
}
