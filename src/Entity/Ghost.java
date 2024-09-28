package Entity;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.Timer;

import Block.Rectangle;
import Block.Wall;
import Main.GameState;
import Main.MyPanel;


public class Ghost  implements ActionListener
{
Wall wall;
Image img1,img2,img3;
Timer time;
int x1,y1,x2,y2,x3,y3;
int speedx1,speedy1;
int speedx2,speedy2;
int speedx3,speedy3;
int width;
int height;
Player player;
int whichOne;
char direction1;
char direction2;
char direction3;
GameState gs;
MyPanel mypanel;
public Ghost(Wall wall,Player player,GameState gs,MyPanel mypanel)
{
	this.mypanel=mypanel;
	this.gs=gs;
	this.wall=wall;
this.player=player;
   direction1 ='u';
   direction2 ='l';
   direction3 ='d';
	 x3=260;
	y3=250;
	 x1=100;
	y1=40;
	 x2=245;
	y2=40;
	width=32;
	height=32;
	speedx1=5;
	speedy1=5;
	speedx2=5;
	speedy2=5;
	speedx3=5;
	speedy3=5;
	img1= new ImageIcon("C:\\Users\\Alaa\\Desktop\\github\\PacmanNow\\src\\pacman\\ghost.png").getImage();
	img2= new ImageIcon("C:\\Users\\Alaa\\Desktop\\github\\PacmanNow\\src\\pacman\\ghost2.png").getImage();
	img3= new ImageIcon("C:\\Users\\Alaa\\Desktop\\github\\PacmanNow\\src\\pacman\\ghost3.png").getImage();
}

public boolean check(Wall wall,int x , int y)
{
	for(Rectangle r : wall.getWall())
	if(r!=null)
	{
        if (r != null && r.intersects(x, y, width, height)) {
            return true;
        }
	}
	return false;
}
public void setActions()
{
	Random random=new Random();
	int i=random.nextInt(100)+1;
	if(i>0&&i<25)
	{
		if(whichOne==1)
		direction1 ='u';
		if(whichOne==2)
		direction2 ='u';
		if(whichOne==3)
		direction3 ='u';
	}else if(i>25&&i<50)
	{
		if(whichOne==1)
		direction1 ='d';
		if(whichOne==2)
		direction2 ='d';
		if(whichOne==3)
		direction3 ='d';
	}else if(i>50&&i<75)
	{
		if(whichOne==1)
		direction1 ='r';
		if(whichOne==2)
		direction2 ='r';
		if(whichOne==3)
		direction3 ='r';
	}else if(i>75&&i<=100)
	{
		if(whichOne==1)
		direction1 ='l';
		if(whichOne==2)
		direction2 ='l';
		if(whichOne==3)
		direction3 ='l';
	}
}
public void draw(Graphics2D g2d)
{
	
	g2d.drawImage(img1,x1,y1,width,height,null);
	g2d.drawImage(img2,x2,y2,width,height,null);
	g2d.drawImage(img3,x3,y3,width,height,null);
}
public boolean check(Entity e1) {
    if (this != null && e1 != null) {
    	 /* return (x < otherX + otherWidth &&x + width > otherX &&y < otherY + otherHeight &&y + height > otherY)*/
        return (x1 < e1.x + e1.width && x1 + width > e1.x && y1 < e1.y + e1.height && y1 + height > e1.y
                || x2 < e1.x + e1.width && x2 + width > e1.x && y2 < e1.y + e1.height && y2 + height > e1.y
                || x3 < e1.x + e1.width && x3 + width > e1.x && y3 < e1.y + e1.height && y3 + height > e1.y);
    }
    return false;
}


public void update()
{
	if(check(player))
	{
		gs.gameState=gs.gameEndLost;
		gs.b=false;
	}
	if(gs.gameState==gs.gameRun)
	{switch(direction1)
	{
	case 'u':{
            y1-=speedy1;
		if(check(wall,x1,y1))
		{y1+=speedy1;
			whichOne=1;
			setActions();
		}
		
	}break;
	case 'r':{
		x1+=speedx1;
		if(check(wall,x1,y1))
		{x1-=speedx1;
			whichOne=1;
			setActions();
		}
	}break;
	case 'l':{
		x1-=speedx1;
		if(check(wall,x1,y1))
		{x1+=speedx1;
			whichOne=1;
			setActions();
		}
	}break;
	case 'd':{
		y1+=speedy1;
		if(check(wall,x1,y1))
		{
			y1-=speedy1;
			whichOne=1;
			setActions();
		}
	}break;
	}
	
	switch(direction2)
	{
	case 'u':{
            y2-=speedy2;
		if(check(wall,x2,y2))
		{y2+=speedy2;
			whichOne=2;
			setActions();
		}
		
	}break;
	case 'r':{
		x2+=speedx2;
		if(check(wall,x2,y2))
		{x2-=speedx2;
			whichOne=2;
			setActions();
		}
	}break;
	case 'l':{
		x2-=speedx2;
		if(check(wall,x2,y2))
		{x2+=speedx2;
			whichOne=2;
			setActions();
		}
	}break;
	case 'd':{
		y2+=speedy2;
		if(check(wall,x2,y2))
		{y2-=speedy2;
			whichOne=2;
			setActions();
		}
	}break;
	
	}
	
	switch(direction3)
	{
	case 'u':{
            y3-=speedy3;
		if(check(wall,x3,y3))
		{y3+=speedy3;
			whichOne=3;
			setActions();
		}
		
	}break;
	case 'r':{
		x3+=speedx3;
		if(check(wall,x3,y3))
		{x3-=speedy3;
			whichOne=3;
			setActions();
		}
	}break;
	case 'l':{
		x3-=speedx3;
		if(check(wall,x3,y3))
		{x3+=speedy3;
			whichOne=3;
			setActions();
		}
	}break;
	case 'd':{
		y3+=speedy3;
		if(check(wall,x3,y3))
		{y3-=speedy3;
			whichOne=3;
			setActions();
		}
	}break;
	
	}
	}
}

@Override
public void actionPerformed(ActionEvent arg0) {
	// TODO Auto-generated method stub

}
}
