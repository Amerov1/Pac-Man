package Entity;

import java.awt.Graphics2D;
import java.awt.Image;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.Timer;
import Block.Rectangle;
import Block.Wall;
import Main.GameState;
import Main.MyPanel;
import Main.StateOfGame;


public class Ghost  
{
Image img1,img2,img3;
Timer time;
private Wall wall;
int x1,y1,x2,y2,x3,y3;
int speedX1,speedY1;
int speedX2,speedY2;
int speedX3,speedY3;
int width;
int height;
Player player;
int whichOne;
char direction1;
char direction2;
char direction3;
GameState gs;
MyPanel mypanel;
public Ghost(Wall wall,Player player,GameState gs,MyPanel mypanel,int cellSize)
{
	this.wall=wall;
	this.mypanel=mypanel;
	this.gs=gs;
    this.player=player;
   direction1 ='u';
   direction2 ='l';
   direction3 ='d';

	width=cellSize;
	height=cellSize;
	speedX1=cellSize / 4;
	speedY1=cellSize / 4;
	speedX2=cellSize / 4;
	speedY2=cellSize / 4;
	speedX3=cellSize / 4;
	speedY3=cellSize / 4;
	img1= new ImageIcon(getClass().getResource("/pacman/orangeGhost.png")).getImage();
	img2= new ImageIcon(getClass().getResource("/pacman/pinkGhost.png")).getImage();
	img3= new ImageIcon(getClass().getResource("/pacman/blueGhost.png")).getImage();
	   createGhosts();
}
public void setDefault()
{
	direction1 ='u';
	direction2 ='l';
	direction3 ='d';
	createGhosts();
}
public boolean check(Wall wall,int x , int y)
{
	for(Rectangle r : wall.getWall())
	if(r!=null)
	{
        if (r != null && r.intersects(x, y, width, height)) 
        {
            return true;
        }
	}
	return false;
}
public void createGhosts()
{
	int count=1;
    int[][] _map = wall.getMap();
    for (int row = 0; row < _map.length; row++) {
        for (int col = 0; col < _map[0].length; col++) {
            if (_map[row][col] == 4) { // Nur Zellen mit "0" sind f�r K�se
                int tx = col * width;  // Spalte -> Pixel-X
                int ty = row * height; // Zeile -> Pixel-Y
                        // K�seposition in Pixeln speichern
                if(count==1)
                {
                	x1=tx;
                	y1=ty;
                	count++;
                }else if(count==2)
                {
                	x2=tx;
                	y2=ty;
                	count++;
                }else{
                	x3=tx;
                	y3=ty;
                	count++;
                }
            }
        }
    }
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
    	 /* return (x < otherX + otherWidth &&x + width > otherX &&y < otherY + otherHeight &&y + height > otherY)*/
        return (x1 < e1.x + e1.width && x1 + width > e1.x && y1 < e1.y + e1.height && y1 + height > e1.y
                || x2 < e1.x + e1.width && x2 + width > e1.x && y2 < e1.y + e1.height && y2 + height > e1.y
                || x3 < e1.x + e1.width && x3 + width > e1.x && y3 < e1.y + e1.height && y3 + height > e1.y);
}


public void update()
{
	if(check(player))
	{
		player.removeLive();
		player.createPlayer();
		this.setDefault();
		if(player.getLivesNumber()==0)
		MyPanel.setStateOfGame(StateOfGame.End_Lost);
	}
	if(MyPanel.getStateOfGame()==StateOfGame.Is_Running)
	{switch(direction1)
	{
	case 'u':{
            y1-=speedY1;
		if(check(wall,x1,y1))
		{y1+=speedY1;
			whichOne=1;
			setActions();
		}
		
	}break;
	case 'r':{
		x1+=speedX1;
	//	if(check(wall,x1,y1))
		{x1-=speedX1;
			whichOne=1;
			setActions();
		}
	}break;
	case 'l':{
		x1-=speedX1;
		if(check(wall,x1,y1))
		{x1+=speedX1;
			whichOne=1;
			setActions();
		}
	}break;
	case 'd':{
		y1+=speedY1;
		if(check(wall,x1,y1))
		{
			y1-=speedY1;
			whichOne=1;
			setActions();
		}
	}break;
	}
	
	switch(direction2)
	{
	case 'u':{
            y2-=speedY2;
		if(check(wall,x2,y2))
		{y2+=speedY2;
			whichOne=2;
			setActions();
		}
		
	}break;
	case 'r':{
		x2+=speedX2;
		if(check(wall,x2,y2))
		{x2-=speedX2;
			whichOne=2;
			setActions();
		}
	}break;
	case 'l':{
		x2-=speedX2;
		if(check(wall,x2,y2))
		{x2+=speedX2;
			whichOne=2;
			setActions();
		}
	}break;
	case 'd':{
		y2+=speedY2;
		if(check(wall,x2,y2))
		{y2-=speedY2;
			whichOne=2;
			setActions();
		}
	}break;
	
	}
	
	switch(direction3)
	{
	case 'u':{
            y3-=speedY3;
		if(check(wall,x3,y3))
		{y3+=speedY3;
			whichOne=3;
			setActions();
		}
		
	}break;
	case 'r':{
		x3+=speedX3;
		if(check(wall,x3,y3))
		{x3-=speedY3;
			whichOne=3;
			setActions();
		}
	}break;
	case 'l':{
		x3-=speedX3;
		if(check(wall,x3,y3))
		{x3+=speedY3;
			whichOne=3;
			setActions();
		}
	}break;
	case 'd':{
		y3+=speedY3;
		if(check(wall,x3,y3))
		{y3-=speedY3;
			whichOne=3;
			setActions();
		}
	}break;
	
	}
	}
}
}
