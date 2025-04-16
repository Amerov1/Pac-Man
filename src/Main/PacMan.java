package Main;

import javax.swing.JFrame;

public class PacMan extends JFrame {

	private final int xPanel = 0;
	private final int yPanel = 0;
	private final int tileSize=32;
	private final int widthPanel = tileSize*11;
	private final int heightPanel = tileSize*12;
	
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
