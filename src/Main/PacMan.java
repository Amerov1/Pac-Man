package Main;
//import java.awt.Dimension;

import java.awt.Dimension;


//import javax.swing.JButton;
import javax.swing.JFrame;

import Block.Wall;

public class PacMan extends JFrame {

    final int xPanel = 0;
    final int yPanel = 0;
    int tileSize=32;
    final int widthPanel = tileSize*11;
    final int heightPanel = tileSize*12;
    MyPanel mypanel;

    PacMan() {
        this.setTitle("Pecman");
        //  setpreferedsize + setlayout(null) don't work  together
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setResizable(false);
        
        this.setSize(widthPanel, heightPanel);
        mypanel = new MyPanel(xPanel, yPanel, widthPanel, heightPanel,tileSize);
        this.add(mypanel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

}
