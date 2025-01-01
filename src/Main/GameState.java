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

	Player player;
	Cheese cheese;
   MyPanel mypanel;
   public int select;
   public boolean b;
   private int cellSize;
   public KeyHandler kh;
	public GameState(Player player,Cheese cheese,MyPanel mypanel, KeyHandler kh)
	{  cellSize=32;
		select=0;
		MyPanel.setStateOfGame(StateOfGame.GameStart);
		this.kh=kh;
		this.player=player;
		this.cheese=cheese;
		this.mypanel=mypanel;

	}
	public void update()
	{
		if(StateOfGame.GameStart== MyPanel.getStateOfGame() &&kh.getDirection()=='D' && select==0)
		{
			select=1;
		}else if(StateOfGame.GameStart== MyPanel.getStateOfGame() &&kh.getDirection()=='U' && select==1)
		{
			select=0;
		}
		
		if(StateOfGame.GameStart== MyPanel.getStateOfGame() &&select==0&&kh.enterPressed)
		{
			kh.setDirection(' ');
			MyPanel.setStateOfGame(StateOfGame.GameIsRunning);
			
		}else if(StateOfGame.GameStart== MyPanel.getStateOfGame() &&select==1&&kh.enterPressed)
		{
			System.exit(0);
			
		}
		
		if(StateOfGame.GameIsRunning== MyPanel.getStateOfGame() &&0==cheese.shapes.size())//Here is the issue
		{
			MyPanel.setStateOfGame(StateOfGame.GameEndWin);
			//b=false;//just one time per game
		}else if(MyPanel.getStateOfGame()==StateOfGame.GameIsRunning &&kh.spacePressed)
		{
			MyPanel.setStateOfGame(StateOfGame.GamePause);
		}else if(MyPanel.getStateOfGame()==StateOfGame.GamePause &&!kh.spacePressed)
		{
			MyPanel.setStateOfGame(StateOfGame.GameIsRunning);
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
		{case GameEndLost :
			 MyPanel.setStateOfGame(StateOfGame.GameEndLost);
				g2d.setColor(new Color(0,0,0,125));
				g2d.fillRect(0, 0, cellSize*11, cellSize*11);
				text="Game Over";
				f=new Font(text,Font.BOLD,cellSize);
				g2d.setFont(f);
			    g2d.setColor(Color.WHITE);
			    g2d.drawString(text,3*cellSize, 3*cellSize);
	   break;
		case GameEndWin :
			g2d.setColor(new Color(0,0,0,125));
			g2d.fillRect(0, 0, 32*11, 32*11);
			 text="You Win";
			 f=new Font(text,Font.BOLD,mypanel.getPanelWidth()/10);
			g2d.setFont(f);
		    g2d.setColor(Color.WHITE);
		    g2d.drawString(text,3*32, 3*32);	
		    break;
		case GameIsRunning :
		String textScore="Score :"+player.getScore();
		g2d.setColor(Color.WHITE);
		g2d.setFont(new Font(textScore,1,32));
		g2d.drawString(textScore,Font.ITALIC,650);
		break;
		case GamePause :
			g2d.setColor(new Color(0,0,0,125));
			g2d.fillRect(0, 0, 32*11, 32*11);
			 text="Pause";
			 f=new Font(text,Font.BOLD,mypanel.getPanelWidth()/10);
			g2d.setFont(f);
		    g2d.setColor(Color.WHITE);
		    g2d.drawString(text,4*32, 3*32);	
		    break;
		case GameStart :
			g2d.setPaint(new Color(0,0,0));
			g2d.fillRect(0,0, 1000, 800);
			Image img1=new ImageIcon("C:\\Users\\Alaa\\Desktop\\github\\PacmanNow\\src\\pacman\\imagesnew.png").getImage();
			Image img2=new ImageIcon("C:\\Users\\Alaa\\Desktop\\github\\PacmanNow\\src\\pacman\\cheese.png").getImage();
			 text="PecMan";
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
			 f=new Font(text,Font.BOLD,mypanel.getPanelWidth()/10);
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
