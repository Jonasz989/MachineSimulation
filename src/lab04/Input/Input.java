package lab04.Input;

import javax.swing.*;
import java.awt.*;
public class Input extends JPanel {

    public JButton startButton;
    public TextField tflenght1, tfskok, tfdlugosc, tfheight, tflength2;

    public Input(){

        //-----------------------------------------------------------------------------
        //whole Input
        this.setSize(300,1080);
        this.setLocation(0,0);
        this.setBackground(Color.getHSBColor(100,100,100));
        this.setLayout(null);

        //Labels

        //tytułowa
        JLabel labelTitle = new JLabel("Symulacja");
        labelTitle.setSize(100,50);
        labelTitle.setLocation(100,0);
        labelTitle.setLayout(null);

        //parametr l1
        JLabel ll1 = new JLabel("Dlugosc l1");
        ll1.setSize(200,50);
        ll1.setLocation(45,150);
        ll1.setLayout(null);

        //parametr l2
        JLabel ll2 = new JLabel("Parameter l2");
        ll2.setSize(200,50);
        ll2.setLayout(null);
        ll2.setLocation(45,220);

        //parametr h
        JLabel lh = new JLabel("Parametr h");
        lh.setSize(200,50);
        lh.setLayout(null);
        lh.setLocation(45,290);

        //parametr d
        JLabel ld = new JLabel("Parametr d");
        ld.setSize(200,50);
        ld.setLayout(null);
        ld.setLocation(45, 360);

        JLabel ls = new JLabel("Parametr s");
        ls.setSize(200,50);
        ls.setLocation(45,430);
        ls.setLayout(null);

//------------------------------------------------------------------------------
        //TextFields

        //dla parametru l1
        tflenght1 = new TextField();
        tflenght1.setLocation(45,190);
        tflenght1.setText("0");

        //dla l2
        tflength2 = new TextField();
        tflength2.setLocation(45,260);
        tflength2.setText("0");

        //dla parametru h
        tfheight = new TextField();
        tfheight.setLocation(45,330);
        tfheight.setText("0");

        //dla parametru d
        tfdlugosc = new TextField();
        tfdlugosc.setLocation(45,400);
        tfdlugosc.setText("0");

        //dla parametru s
        tfskok = new TextField();
        tfskok.setLocation(45,470) ;
        tfskok.setText("0");

//-----------------------------------------------------------------------------
        //Buttons
        startButton = new JButton("Start symulacji");
        startButton.setSize(150,50);
        startButton.setLayout(null);
        startButton.setLocation(80,700);
        startButton.addActionListener(e -> {});



        //dodawanie do pola Input
        this.add(labelTitle);

        this.add(ll1);
        this.add(tflenght1);

        this.add(ls);
        this.add(tfskok);

        this.add(ld);
        this.add(tfdlugosc);

        this.add(lh);
        this.add(tfheight);

        this.add(ll2);
        this.add(tflength2);

        this.add(startButton);
    }

}
