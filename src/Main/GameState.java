package Main;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import javax.swing.ImageIcon;
import Entity.Cheese;
import Entity.KeyHandler;
import Entity.Player;


public class GameState {

	Player player;
	Cheese cheese;
   MyPanel mypanel;
public StateOfGame state;
public int select;
public boolean b;
public KeyHandler kh;
	public GameState(Player player,Cheese cheese,MyPanel mypanel, KeyHandler kh)
	{   b=true;
		select=0;
		state=StateOfGame.GameStart;
		this.kh=kh;
		this.player=player;
		this.cheese=cheese;
		this.mypanel=mypanel;

	}
	public void update()
	{
		if(kh.downPressed && select==0)
		{
			select=1;
		}else if(kh.upPressed && select==1)
		{
			select=0;
		}
		
		if(select==0&&kh.enterPressed&&b)
		{
			state=StateOfGame.GameIsRunning;
			
		}
		if(player.getScore()==cheese.shapes.size())
		{
			mypanel.setRunning(false);
			state=StateOfGame.GameEndWin;
			b=false;//just one time per game
		}
		
		if(state==StateOfGame.GameIsRunning &&kh.spacePressed)
		{
			state=StateOfGame.GamePause;
		}else if(state==StateOfGame.GamePause &&kh.spacePressed)
		{
			state=StateOfGame.GameIsRunning;
		}
	}
	public void draw(Graphics2D g2d)
	{ g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
	g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
	g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
	g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);

	 if(state==StateOfGame.GameEndLost)
		{
			mypanel.setRunning(false);
			g2d.setColor(new Color(0,0,0,95));
			g2d.fillRect(0, 0, 1000, 1000);
		g2d.setFont(g2d.getFont().deriveFont(Font.BOLD,110f));
		String text="Game Over";
		 int centerx=(850/4)-10;
		 int centery= (850/4)-10;

	        int textX = centerx ;
	        int textY = centery ;
	        g2d.setColor(Color.WHITE);
	        g2d.drawString(text, textX, textY);

		}else if(state==StateOfGame.GameEndWin)
		{
			//mypanel.setRunning(false);
			g2d.setColor(new Color(0,0,0,95));
			g2d.fillRect(20, 20, 1000, 1000);
		g2d.setFont(g2d.getFont().deriveFont(Font.BOLD,110f));
		String text="You Win !";
		 int centerx=(850/4)-10;
		 int centery= (850/4)-10;

	        int textX = centerx ;
	        int textY = centery ;
	        g2d.setColor(Color.WHITE);
	        g2d.drawString(text, textX, textY);

		}else if(state==StateOfGame.GameIsRunning)
		{
		String textScore="Score :"+player.getScore();
		g2d.setColor(Color.WHITE);
		g2d.setFont(new Font(textScore,1,32));
		g2d.drawString(textScore,Font.ITALIC,650);
		}else if(state==StateOfGame.GamePause)
		{
			//PAUSE
			
			g2d.setColor(new Color(0,0,0,95));
			g2d.fillRect(100, 100, 600, 600);
			g2d.setFont(g2d.getFont().deriveFont(Font.BOLD,110f));
			 String text="Pause";
			 int centerx=(850/4)-10;
			 int centery= (850/4)-10;

		        int textX = centerx ;
		        int textY = centery ;
		        g2d.setColor(Color.WHITE);
		        g2d.drawString(text, textX, textY+100);	
		}else if(state==StateOfGame.GameStart)
		   {
			g2d.setPaint(new Color(0,0,0));
			g2d.fillRect(0,0, 1000, 800);
			Image img1=new ImageIcon("C:\\Users\\Alaa\\Desktop\\github\\PacmanNow\\src\\pacman\\imagesnew.png").getImage();
			Image img2=new ImageIcon("C:\\Users\\Alaa\\Desktop\\github\\PacmanNow\\src\\pacman\\cheese.png").getImage();
			//Image img3=new ImageIcon("C:\\Users\\Alaa\\Desktop\\pecman\\cheese.png").getImage();
			//Image img4=new ImageIcon("C:\\Users\\Alaa\\Desktop\\pecman\\cheese.png").getImage();
			String text="PecMan";
			g2d.setFont(g2d.getFont().deriveFont(Font.BOLD,150f));
			g2d.setPaint(Color.GRAY);
			g2d.drawString(text, 170, 130);
			g2d.setPaint(Color.WHITE);
			g2d.setFont(g2d.getFont().deriveFont(Font.BOLD,150f));
			g2d.drawString(text, 165, 125);
			g2d.drawImage(img1, 295, 230, player.getWdith()*6, player.getWdith()*6,null);
			g2d.drawImage(img2, 405, 290, player.getWdith()*3, player.getWdith()*3,null);
			g2d.drawImage(img2, 495, 290, player.getWdith()*3, player.getWdith()*3,null);
			g2d.drawImage(img2, 595, 290, player.getWdith()*3, player.getWdith()*3,null);	
			
			//MENU
			
			g2d.setFont(g2d.getFont().deriveFont(Font.BOLD,50f));
			text="Start Playing";
			g2d.drawString(text, 275, 525);
			
		
			g2d.setFont(g2d.getFont().deriveFont(Font.BOLD,50f));
			text="Quit";
			g2d.drawString(text, 375, 595);
			if(select==0){
			g2d.setFont(g2d.getFont().deriveFont(Font.BOLD,50f));
			g2d.drawString(">", 225, 525);	
			}else if(select==1){
			g2d.setFont(g2d.getFont().deriveFont(Font.BOLD,50f));
			g2d.drawString(">", 325, 595);	
			}
			
	   	}
	}
	
}
