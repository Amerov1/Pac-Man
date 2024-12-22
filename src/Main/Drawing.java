package Main;
//import java.awt.Dimension;

//import javax.swing.JButton;
import javax.swing.JFrame;

public class Drawing extends JFrame {

    final int xPanel = 0;
    final int yPanel = 0;
    final int widthPanel = 890;
    final int heightPanel = 800;
    MyPanel mypanel;

    Drawing() {
        this.setTitle("Pecman");
        //  setpreferedsize + setlayout(null) don't work  together

        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setResizable(false);
        mypanel = new MyPanel(xPanel, yPanel, widthPanel, heightPanel);
// Set width and height accordingly
        // button.setVisible(true);

        this.add(mypanel);

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

}
