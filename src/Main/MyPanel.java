package Main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

import Entity.Cheese;
import Block.Rectangle;
import Block.Wall;
import Entity.Ghost;
import Entity.KeyHandler;
import Entity.Player;




public class MyPanel extends JPanel implements ActionListener{
	
private Player player1;
private KeyHandler 	kh;
private Ghost ghost1;
public char direction='r';
private Cheese cheese;
private GameState gs;
private Wall wall;
private Timer gameLoop;
private static StateOfGame SOG;
private int panelWidth;
private int panelHeight;
	public	MyPanel(int x,int y,int width,int height,int cellSize)
	{ 
		setBackground(Color.BLACK);
		panelWidth=width;
		panelHeight=height;
		setPreferredSize(new Dimension(width,panelHeight));
		wall=new Wall(cellSize);
		 SOG= StateOfGame.GameStart;
		kh=new KeyHandler(SOG);
  	    player1=new Player(kh,cellSize);
        addKeyListener(kh);
	    cheese=new Cheese(wall,player1,cellSize);
	    gs=new GameState(player1,cheese,this,kh);
	    ghost1=new Ghost(wall,player1,gs,this,cellSize);
	    setFocusable(true);
	    startRunning(); 
	}
	public int getPanelWidth()
	{
		return panelWidth;
	}
	public int getPanelHeight()
	{
		return panelHeight;
	}
	public static void setStateOfGame(StateOfGame s)
	{
	SOG=s;
	}
	public static StateOfGame getStateOfGame()
	{
		return SOG;
	}
	public void startRunning()
	{
		gameLoop =new Timer(50,this);
		gameLoop.start();
	}
    public void setDirection(char d)
    {
    	this.direction=d;
    }
    public char getDirection()
    {
    	return this.direction;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        draw(g2d); // Call your draw method here
    }
    public void setDefault()
    {
    	player1.setDefault();
    	cheese.setDefault();
    	ghost1.setDefault();
    }
    public void draw(Graphics2D g2d)
	{
		AffineTransform old=g2d.getTransform();
		
		
		if(SOG == StateOfGame.GameIsRunning)
		{
		wall.draw(g2d);
		cheese.draw(g2d);
        player1.draw(g2d);
        ghost1.draw(g2d);
		}
		gs.draw(g2d);
		
		g2d.setTransform(old);
	}
	public void update()
	{kh.update(SOG);
		gs.update();
		
		//System.out.println(SOG);
		if(SOG==StateOfGame.GameIsRunning)
		{
	        cheese.update();
			player1.update();
            ghost1.update();
		}
		if((SOG==StateOfGame.GameEndLost||
			SOG==StateOfGame.GameEndWin) &&kh.spacePressed)
		{
			SOG=StateOfGame.GameIsRunning;
			setDefault();
			kh.setDirection(' ');
			kh.setSpacePressed(false);
		}
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		update();
		repaint();
	}
	


}
