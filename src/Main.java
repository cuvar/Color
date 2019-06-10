import java.awt.AWTException;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Main extends JFrame implements ChangeListener, ActionListener, KeyListener{

    static int width = 415;
    static int height = 450;

    static int red;
    static int green;
    static int blue;
    static String hex = "#808080";
    static boolean go = false;
    static int mode = 0;

    static JPanel container;
    static JPanel panel1;
    static Panel panel2;

    static JMenuBar bar;
    static JMenu menuFile;
    static JMenu menuMode;
    static JMenuItem close;
    static JMenuItem rgb;
    static JMenuItem hsb;

    static JSlider redSlider;
    static JSlider greenSlider;
    static JSlider blueSlider;
    static JSlider greySlider;
    static JTextField redArea;
    static JTextField greenArea;
    static JTextField blueArea;
    static JTextField greyArea;
    static JTextField hexArea;
    static JLabel redLabel;
    static JLabel greenLabel;
    static JLabel blueLabel;
    static JLabel greyLabel;

    //Constructor
    //
    public Main() {
        setSize(width, height);
        setTitle("Color");
        setLocationRelativeTo(null);
        setResizable(false);
        Timer t = new Timer(50, this);

        addKeyListener(this);
        setFocusable(true);



        //JPanel
        container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        panel1 = new JPanel();
        panel1.setLayout(null);
        panel1.setFocusable(true);
//		panel1.setBackground(new Color(255, 0, 0));										//DEBUG
        panel2 = new Panel();
        panel2.setLayout(null);
        panel2.setFocusable(true);



        //Controlslider
        redSlider = new JSlider(0, 255, 128);
        redSlider.setBounds(100, 20, 300, 30);
        redSlider.setMajorTickSpacing(50);
        redSlider.setMinorTickSpacing(25);
        redSlider.setPaintTicks(true);
        redSlider.addChangeListener(this);
        greenSlider = new JSlider(0, 255, 128);

        greenSlider.setBounds(100, 70, 300, 30);
        greenSlider.setMajorTickSpacing(50);
        greenSlider.setMinorTickSpacing(25);
        greenSlider.createStandardLabels(1);
        greenSlider.setPaintTicks(true);
        greenSlider.addChangeListener(this);

        blueSlider = new JSlider(0, 255, 128);
        blueSlider.setBounds(100, 120, 300, 30);
        blueSlider.setMajorTickSpacing(50);
        blueSlider.setMinorTickSpacing(25);
        blueSlider.createStandardLabels(1);
        blueSlider.setPaintTicks(true);
        blueSlider.addChangeListener(this);

        greySlider = new JSlider(0, 255, 128);
        greySlider.setBounds(100, 170, 300, 30);
        greySlider.setMajorTickSpacing(50);
        greySlider.setMinorTickSpacing(25);
        greySlider.createStandardLabels(1);
        greySlider.setPaintTicks(true);
        greySlider.addChangeListener(this);



        //ControlTextFields
        redArea = new JTextField();
        redArea.setBounds(50, 20, 50, 20);
        redArea.setHorizontalAlignment(0);
        redArea.setText(Integer.toString(redSlider.getValue()));
        redArea.addActionListener(this);

        greenArea = new JTextField();
        greenArea.setBounds(50, 70, 50, 20);
        greenArea.setHorizontalAlignment(0);
        greenArea.setText(Integer.toString(greenSlider.getValue()));
        greenArea.addActionListener(this);

        blueArea = new JTextField();
        blueArea.setBounds(50, 120, 50, 20);
        blueArea.setHorizontalAlignment(0);
        blueArea.setText(Integer.toString(blueSlider.getValue()));
        blueArea.addActionListener(this);

        greyArea = new JTextField();
        greyArea.setBounds(50, 170, 50, 20);
        greyArea.setHorizontalAlignment(0);
        greyArea.setText(Integer.toString(greySlider.getValue()));
        greyArea.addActionListener(this);

        hexArea = new JTextField();
        hexArea.setBounds(width/2 - 35, 0, 70, 20);
        hexArea.setHorizontalAlignment(0);
        hexArea.setText(hex);
        hexArea.addActionListener(this);



        //ControlLabel
        redLabel = new JLabel("R");
        redLabel.setBounds(10, 20, 40, 20);

        greenLabel = new JLabel("G");
        greenLabel.setBounds(10, 70, 40, 20);

        blueLabel = new JLabel("B");
        blueLabel.setBounds(10, 120, 40, 20);

        greyLabel = new JLabel("Grey");
        greyLabel.setBounds(10, 170, 40, 20);



        //JPanel Adds
        panel1.add(redLabel);
        panel1.add(greenLabel);
        panel1.add(blueLabel);
        panel1.add(greyLabel);

        panel1.add(redSlider);
        panel1.add(greenSlider);
        panel1.add(blueSlider);
        panel1.add(greySlider);

        panel1.add(redArea);
        panel1.add(greenArea);
        panel1.add(blueArea);
        panel1.add(greyArea);
        panel2.add(hexArea);

        container.add(panel1);
        container.add(panel2);
        add(container);


    }



    //Main
    //
    public static void main(String[] args) {
        Main frm = new Main();
        frm.setVisible(true);
    }



    //setRGB - RGB durch Slider setzen
    //
    static void setRGB() {
        red = redSlider.getValue();
        green = greenSlider.getValue();
        blue = blueSlider.getValue();
    }

    //setSliderAndArea
    //
    static void setSliderAndArea(int r, int g, int b) {
        redSlider.setValue(r);
        greenSlider.setValue(g);
        blueSlider.setValue(b);

        redArea.setText(Integer.toString(r));
        greenArea.setText(Integer.toString(g));
        blueArea.setText(Integer.toString(b));
    }



    //setRGB(string) - RGB durch Hex setzen
    //
    static void setRGB(String h) {
        String rh;
        String gh;
        String bh;

        rh = hex.substring(1, 3);
        gh = hex.substring(3,5);
        bh = hex.substring(5);

        //System.out.println("Red Substring: " + hex.substring(1,3));						//DEBUG
        //System.out.println("Values of RH/GH/BH: "+ rh + "" + gh + "" + bh); 			//DEBUG

        red = Integer.parseInt(rh, 16);
        green = Integer.parseInt(gh, 16);
        blue = Integer.parseInt(bh, 16);

        //System.out.println("RGB: "+ red + " " + green + " " + blue);					//DEBUG
        setSliderAndArea(red, green, blue);
    }



    //setHex - setze Hex durch RGB
    //
    static void setHex() {
        String hr, hg, hb;

        hr = Integer.toHexString(red).toUpperCase();
        hg = Integer.toHexString(green).toUpperCase();
        hb = Integer.toHexString(blue).toUpperCase();

        //falls hex noch einstellig ist, füge null hinzu
        if(hr.length() == 1)
            hr = "0" + hr;
        if(hg.length() == 1)
            hg = "0" + hg;
        if(hb.length() == 1)
            hb = "0" + hb;

        hex = "#" + hr + hg + hb;
    }




    //stateChangeListener - Wenn Slider ver�ndert wird
    //
    @Override
    public void stateChanged(ChangeEvent e) {
        if(e.getSource() == redSlider) {
            redArea.setText(Integer.toString(redSlider.getValue()));
            red = redSlider.getValue();
            setRGB();
        }

        if(e.getSource() == greenSlider) {
            greenArea.setText(Integer.toString(greenSlider.getValue()));
            green = greenSlider.getValue();
            setRGB();
        }

        if(e.getSource() == blueSlider) {
            blueArea.setText(Integer.toString(blueSlider.getValue()));
            blue = blueSlider.getValue();
            setRGB();
        }

        if(e.getSource() == greySlider) {
            greyArea.setText(Integer.toString(greySlider.getValue()));
            red = green = blue = greySlider.getValue();
            redSlider.setValue(greySlider.getValue());
            greenSlider.setValue(greySlider.getValue());
            blueSlider.setValue(greySlider.getValue());
        }

        setHex();
        hexArea.setText(hex);
        panel2.repaint();
    }


    public void pickColor(){
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e3) {
            e3.printStackTrace();
        }
        BufferedImage image = robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
        try {
            ImageIO.write(image, "JPG", new File("screenshot.jpg"));
        } catch (IOException e2) {
            e2.printStackTrace();
        }


        Color c = new Color(image.getRGB(this.getX(), this.getY()));
        setSliderAndArea(c.getRed(), c.getGreen(), c.getBlue());

        robot = null;
    }



    //ActionListener
    //
    @Override
    public void actionPerformed(ActionEvent e) {

        //Wenn TextField ver�ndert wird
        if(e.getSource() == redArea) {
            redSlider.setValue(Integer.parseInt(redArea.getText()));
            red = Integer.parseInt(redArea.getText());
        }

        if(e.getSource() == greenArea) {
            greenSlider.setValue(Integer.parseInt(greenArea.getText()));
            green = Integer.parseInt(greenArea.getText());
        }

        if(e.getSource() == blueArea) {
            blueSlider.setValue(Integer.parseInt(blueArea.getText()));
            blue = Integer.parseInt(blueArea.getText());
        }

        if(e.getSource() == greyArea) {
            greySlider.setValue(Integer.parseInt(greyArea.getText()));
            red = green = blue = Integer.parseInt(greyArea.getText());
        }

        if(e.getSource() == hexArea) {
            if(!hexArea.getText().contains("#")){
                hexArea.setText( "#" + hexArea.getText());
                System.out.println("Area Text: " + hexArea.getText());					//DEBUG
            }

            if(hexArea.getText().length() != 7) {
                hexArea.setText(hex);
            } else {
                hex = hexArea.getText();
            }

            System.out.println("Hex After ActionEvent: " + hex);						//DEBUG
            setRGB(hex);

        }

        panel2.repaint();

    }


    //KeyListener
    //
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }
        if(key == KeyEvent.VK_P) {
            pickColor();
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

}
