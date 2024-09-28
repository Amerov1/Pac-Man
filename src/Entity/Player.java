package Entity;


import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

import Block.Wall;

public class Player extends Entity{

	
public	Image img,center,up1,up2,up3,down1,down2,down3,left1,left2,left3,right1,right2,right3;
	KeyHandler kh;
	int countNum;
	int chooseIm;
	int live;
	char direction ;
	Wall wall;
public	int score;
	public Player(KeyHandler kh,Wall wall)
	{direction = 'n';
	live=1;
	score=0;
	countNum=0;
		x=150;
		width=32;
		height=32;
		y=330;
		speed=10;
	    center = new ImageIcon("C:\\Users\\Alaa\\Desktop\\github\\PacmanNow\\src\\pacman\\new.png").getImage();
	    up1= new ImageIcon("C:\\Users\\Alaa\\Desktop\\github\\PacmanNow\\src\\pacman\\up.png").getImage();
	    down1= new ImageIcon("C:\\Users\\Alaa\\Desktop\\github\\PacmanNow\\src\\pacman\\down.png").getImage();
	    right1= new ImageIcon("C:\\Users\\Alaa\\Desktop\\github\\PacmanNow\\src\\pacman\\right.png").getImage();
	    left1 = new ImageIcon("C:\\Users\\Alaa\\Desktop\\github\\PacmanNow\\src\\pacman\\left.png").getImage();
        this.wall=wall;
	    img=center;
	    this.kh=kh;
	}
	public void update()
	{//if( )
		System.out.println("score "+score);
		if(kh.downPressed)
		{
		direction='d';
		y+=speed;
		if(check(wall,this))
			y-=speed;
		
		}else if(kh.upPressed)
		{ direction='u';
			y-=speed;
			if(check(wall,this))
				y+=speed;
			
		}else if(kh.rightPressed)
		{direction='r';
		if(x>=900)
		{
			x=-30;
		}
			x+=speed;
			if(check(wall,this))
				x-=speed;
		}else if(kh.leftPressed)
		{direction='l';
		if(x<=-30)
		{
			x=900;
		}
			x-=speed;
			if(check(wall,this))
				x+=speed;
		}
		
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
	}
	public void draw(Graphics2D g2d)
	{
		img=center;
        switch(direction)
        {
        case 'u':
        {
        	if(chooseIm==0)
			{
			img=center;
			}else if(chooseIm==1)
			{
				img=up1;
			}
        }break;
        case 'd':
        {
        	if(chooseIm==0)
			{
			img=center;
			}else if(chooseIm==1)
			{
				img=down1;
			}
        }break;
        case 'r':
        {
        	if(chooseIm==0)
			{
			img=center;
			}else if(chooseIm==1)
			{
				img=right1;
			}
        }break;
        case 'l':
        {
        	if(chooseIm==0)
			{
			img=center;
			}else if(chooseIm==1)
			{
				img=left1;
			}
        }break;
        }
		 g2d.drawImage(img, x, y,width,height, null);
	}
}
