package Entity;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import Block.Rectangle;
import Block.Wall;
import Main.StateOfGame;

public class Player extends Entity{

	
private	Image img,center,up,down,left,right;
	KeyHandler kh;
	int countNum;
	int chooseIm;
	int live;
	private Wall wall;
	char direction;
	char oldDirection ;
private	int velocity;
private	int score;
private	int velocityX;
private	int velocityy;
public List<Rectangle> lives;
	public Player(KeyHandler kh,int cellSize)
	{direction=' ';
		oldDirection = 'n';
	lives=new ArrayList<Rectangle>();
	lives.add(new Rectangle(0,0,32,32));
	lives.add(new Rectangle(32,0,32,32));
	lives.add(new Rectangle(32*2,0,32,32));
	score=0;
	countNum=0;
	velocityX=0;
	velocityy=0;
	wall=new Wall(cellSize);
		width=cellSize;
		height=cellSize;
		velocity = cellSize/4;
	    center = new ImageIcon("C:\\Users\\Alaa\\Desktop\\github\\PacmanNow\\src\\pacman\\new.png").getImage();
	    up= new ImageIcon("C:\\Users\\Alaa\\Desktop\\github\\PacmanNow\\src\\pacman\\up.png").getImage();
	    down= new ImageIcon("C:\\Users\\Alaa\\Desktop\\github\\PacmanNow\\src\\pacman\\down.png").getImage();
	    right= new ImageIcon("C:\\Users\\Alaa\\Desktop\\github\\PacmanNow\\src\\pacman\\right.png").getImage();
	    left = new ImageIcon("C:\\Users\\Alaa\\Desktop\\github\\PacmanNow\\src\\pacman\\left.png").getImage();
	    img=center;
	    this.kh=kh;
	    createPlayer();
	}
	public void setDefault()
	{
		score=0;
		velocityX=0;
		velocityy=0;
		countNum=0;
		createPlayer();
	}
	public void createPlayer()
	{
		int[][]_map=wall.getMap();
		for(int row=0;row<_map.length;row++)
		{
			for(int col=0;col<_map[0].length;col++)
			{
				if(_map[row][col]==2)
				{
					x=col * width;
					y = row * height;
				}
			}
		}
	}
	public void increaseScore()
	{
		score++;
	}
	public void move()
	{
		x+=velocityX;
		y+=velocityy;
		if(check(wall,this))
		{
			x-=velocityX;
			y-=velocityy;
		}
		
	}
	public int getScore()
	{
		return score;
	}
	public void updateDirection()
	{

		updateVelocity();
			x+=velocityX;
			y+=velocityy;
		if(check(wall,this))
		{
			x-=velocityX;
			y-=velocityy;
			if(kh.getDirection()!=kh.getPreDirection())
			{kh.setDirection(kh.getPreDirection());
			updateVelocity();
			}
		}
		kh.setPreDirection(' ');
	}
	public void updateVelocity()
	{
	if(kh.getState()==StateOfGame.GameIsRunning)
	{
		if(kh.getDirection()=='D')
		{
		velocityy=velocity;
		velocityX=0;
		
		}else if(kh.getDirection()=='U')
		{ 
		velocityy=-velocity;
		velocityX=0;
			
		}else if(kh.getDirection()=='R')
		{
		velocityy=0;
		velocityX=velocity;

		}else if(kh.getDirection()=='L')
		{
		velocityy=0;
		velocityX=-velocity;
		}
	  }
	}
	public void update()
	{		
		countNum++;
		if(countNum>10)
		{
			if(chooseIm==0)
			{
				chooseIm=1;
			}else if(chooseIm==1)
			{
				chooseIm=0;
			}
		}
		updateDirection();	
	}
	public void draw(Graphics2D g2d)
	{AffineTransform old=g2d.getTransform();
		img=center;
        switch(kh.getDirection())
        {
        case 'U':
        {
        	if(chooseIm==0)
			{
			img=center;
			}else if(chooseIm==1)
			{
				img=up;
			}
        }break;
        case 'D':
        {
        	if(chooseIm==0)
			{
			img=center;
			}else if(chooseIm==1)
			{
				img=down;
			}
        }break;
        case 'R':
        {
        	if(chooseIm==0)
			{
			img=center;
			}else if(chooseIm==1)
			{
				img=right;
			}
        }break;
        case 'L':
        {
        	if(chooseIm==0)
			{
			img=center;
			}else if(chooseIm==1)
			{
				img=left;
			}
        }break;
        }
		g2d.drawImage(img, x, y,width,height, null);
		for(Rectangle r:lives)
		g2d.drawImage(center,r.getX(),r.getY(),r.getWidth(),r.getHeight(),null);
    //    g2d.setColor(Color.RED);
    //    g2d.drawRect(x,y, width, height); //
		g2d.setTransform(old);
	}
}
