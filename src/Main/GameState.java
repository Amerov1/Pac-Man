package Main;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;

import javax.swing.ImageIcon;

import Entity.Cheese;
import Entity.KeyHandler;
import Entity.Player;


public class GameState {

   private Player player;
   private Cheese cheese;
   private MyPanel myPanel;
   private int select;
   private int cellSize;
   private KeyHandler kh;
   
	public GameState(Player player,Cheese cheese,MyPanel mypanel, KeyHandler kh)
	{   cellSize=32;
		select=0;
		MyPanel.setStateOfGame(StateOfGame.Start);
		this.kh=kh;
		this.player=player;
		this.cheese=cheese;
		this.myPanel=mypanel;

	}
	public void update()
	{
		if(StateOfGame.Start== MyPanel.getStateOfGame() &&kh.getDirection()=='D' && select==0)
		{
			select=1;
		}else if(StateOfGame.Start== MyPanel.getStateOfGame() &&kh.getDirection()=='U' && select==1)
		{
			select=0;
		}
		
		if(StateOfGame.Start== MyPanel.getStateOfGame() &&select==0&&kh.getEnterPressed())
		{
			kh.setDirection(' ');
			MyPanel.setStateOfGame(StateOfGame.Is_Running);
			
		}else if(StateOfGame.Start== MyPanel.getStateOfGame() &&select==1&&kh.getEnterPressed())
		{
			System.exit(0);
			
		}
		
		if(StateOfGame.Is_Running== MyPanel.getStateOfGame() &&0==cheese.getShapesSize())//Here is the issue
		{
			MyPanel.setStateOfGame(StateOfGame.End_Win);
			//b=false;//just one time per game
		}else if(MyPanel.getStateOfGame()==StateOfGame.Is_Running &&kh.getSpacePressed())
		{
			MyPanel.setStateOfGame(StateOfGame.Pause);
		}else if(MyPanel.getStateOfGame()==StateOfGame.Pause &&!kh.getSpacePressed())
		{
			MyPanel.setStateOfGame(StateOfGame.Is_Running);
		}
	}
	public void draw(Graphics2D g2d)
	{ 
		Font f;
		String text;
		AffineTransform old=g2d.getTransform();
	g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
	g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
	g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
	g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);

		switch(MyPanel.getStateOfGame())
		{case End_Lost :
			 MyPanel.setStateOfGame(StateOfGame.End_Lost);
				g2d.setColor(new Color(0,0,0,125));
				g2d.fillRect(0, 0, cellSize*11, cellSize*11);
				text="Game Over";
				f=new Font(text,Font.BOLD,cellSize);
				g2d.setFont(f);
			    g2d.setColor(Color.WHITE);
			    g2d.drawString(text,3*cellSize, 3*cellSize);
	   break;
		case End_Win :
			g2d.setColor(new Color(0,0,0,125));
			g2d.fillRect(0, 0, 32*11, 32*11);
			 text="You Win";
			 f=new Font(text,Font.BOLD,myPanel.getPanelWidth()/10);
			g2d.setFont(f);
		    g2d.setColor(Color.WHITE);
		    g2d.drawString(text,3*32, 3*32);	
		    break;
		case Is_Running :
		String textScore="Score :"+player.getScore();
		g2d.setColor(Color.WHITE);
		g2d.setFont(new Font(textScore,1,32));
		g2d.drawString(textScore,Font.ITALIC,650);
		break;
		case Pause :
			g2d.setColor(new Color(0,0,0,125));
			g2d.fillRect(0, 0, 32*11, 32*11);
			 text="Pause";
			 f=new Font(text,Font.BOLD,myPanel.getPanelWidth()/10);
			g2d.setFont(f);
		    g2d.setColor(Color.WHITE);
		    g2d.drawString(text,4*32, 3*32);	
		    break;
		case Start :
			g2d.setPaint(new Color(0,0,0));
			g2d.fillRect(0,0, 1000, 800);
			Image img1=new ImageIcon("C:\\Users\\Alaa\\Desktop\\github\\Pac-man\\PacmanNow\\src\\pacman\\imagesnew.png").getImage();
			Image img2=new ImageIcon("C:\\Users\\Alaa\\Desktop\\github\\Pac-man\\PacmanNow\\src\\pacman\\cheese.png").getImage();
			 text="Pac-Man";
			g2d.setFont(g2d.getFont().deriveFont(Font.BOLD,2*cellSize));
			g2d.setPaint(Color.GRAY);
			g2d.drawString(text, cellSize-6, 3*32);
			g2d.setPaint(Color.WHITE);
			g2d.setFont(g2d.getFont().deriveFont(Font.BOLD,2*cellSize));
			g2d.drawString(text, cellSize, 3*32);
			g2d.drawImage(img1, 32, 3*32, 2*cellSize,2*cellSize,null);
			g2d.drawImage(img2, 2*32, 3*32, 2*cellSize, 2*cellSize,null);
			g2d.drawImage(img2, 3*32, 3*32, 2*cellSize, 2*cellSize,null);
			g2d.drawImage(img2, 4*32, 3*32, 2*cellSize, 2*cellSize,null);	
			//MENU
			
			g2d.setFont(g2d.getFont().deriveFont(Font.BOLD,50f));
			text="Start Playing";
			 f=new Font(text,Font.BOLD,myPanel.getPanelWidth()/10);
			g2d.setFont(f);
		        g2d.setColor(Color.WHITE);
		        g2d.drawString(text,2*cellSize, 7*cellSize);			
		
			g2d.setFont(g2d.getFont().deriveFont(Font.BOLD,cellSize));
			text="Quit";
			 f=new Font(text,Font.BOLD,cellSize);
			g2d.setFont(f);
		        g2d.setColor(Color.WHITE);
		        g2d.drawString(text,2*32, 9*32);
			if(select==0){
			g2d.setFont(g2d.getFont().deriveFont(Font.BOLD,50f));
			g2d.drawString(">",32, 7*32);	
			}else if(select==1){
			g2d.setFont(g2d.getFont().deriveFont(Font.BOLD,50f));
			g2d.drawString(">",32, 9*32);	
			}
			g2d.setTransform(old);
			break;
		}
	}
	
}
