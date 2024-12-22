package Main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import Entity.Cheese;
import Block.Wall;
import Entity.Ghost;
import Entity.KeyHandler;
import Entity.Player;




public class MyPanel extends JPanel implements Runnable{
int FPS;
public boolean running;	
public	Thread gameThread;
private Wall wall;
private Player player1;
private KeyHandler 	kh;
private Ghost ghost1;
public char direction='r';
private Cheese cheese;
private GameState gs;
private JButton button;
public	MyPanel(int x,int y,int width,int height)
	{ 
	  running=true;
	    FPS=14;
		setBackground(Color.BLACK);
		setPreferredSize(new Dimension(width,height));
		wall=new Wall();
		kh=new KeyHandler();
        addKeyListener(kh);
	    player1=new Player(kh,wall);
	   
	    cheese=new Cheese(wall,player1);
	    gs=new GameState(player1,cheese,this,kh);
	    ghost1=new Ghost(wall,player1,gs,this);
	    setFocusable(true);
	    button = new JButton("Click Me");
	    startRunning();
	   
	}
public void setRunning(boolean run)
{
	this.running=run;
}
	public void startRunning()
	{
		gameThread=new Thread(this);
		gameThread.start();
	}
    public void setDirection(char d)
    {
    	this.direction=d;
    }
    public char getDirection()
    {
    	return this.direction;
    }
    
	public void paint(Graphics g)
	{
		super.paint(g);
		Graphics2D g2d= (Graphics2D) g;
		
		
		if(gs.gameState != gs.gameStart)
		{
		cheese.draw(g2d);
		wall.draw(g2d);
       player1.draw(g2d);
       ghost1.draw(g2d);
       
		}
		gs.draw(g2d);
	}
	public  void update()
	{
		gs.update();
		
		if(running)
		{
			player1.update();
        cheese.update();
        ghost1.update();
		}
	}
	@Override
	public void run() {
		double  drawInterval=1000000000/FPS;
		double nextDrawTime= drawInterval+System.nanoTime();

		while(gameThread!=null)
		{
			
		update();
		repaint();
		
		try {
			double remainingTime= nextDrawTime -System.nanoTime();
			remainingTime=remainingTime/1000000;
			if(remainingTime<0)
				remainingTime=0;
			Thread.sleep((long)remainingTime);
			nextDrawTime+=drawInterval;
		   } catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		   }
		  
		}
		                              
		
	}
	


}
