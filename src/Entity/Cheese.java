package Entity;

import java.awt.Graphics2D;
import java.awt.Image;


import java.util.ArrayList;

import javax.swing.ImageIcon;






import Block.Point;
import Block.Wall;

public class Cheese extends Entity {


private Image img;
Wall wall;
Player player;
public ArrayList<Point> locations;
int width;
int height;
	public Cheese(Wall wall,Player player)
	{height=32;
	width=32;
		this.wall=wall;
	this.player=player;
		img=new ImageIcon("C:\\Users\\Alaa\\Desktop\\github\\PacmanNow\\src\\pacman\\cheese.png").getImage();
         locations= new ArrayList<Point>();
			for(int y=30;y<=620;y+=30)
			{
				for(int x=100;x<=810;x+=40)
				{
				this.x=x;
				this.y=y;
					if(check(wall,this)!=true)
					{
					
					locations.add(new Point(x,y));
			
					}
				}
			}
	}
	public void check(Entity e1)
	{
		for(Point p : locations)
		if(this!=null&& e1!=null)
		{
	        if (p.getX() < e1.x + e1.width && p.getX() + width > e1.x && p.getY() < e1.y + e1.height && p.getY() + height > e1.y) 
	        {player.score++;
	             p.setX(-30);
	             p.setY(-30);
	             
	        }
		}
	}
	public void update()
	{
		check(player);
	}
	
	public void draw(Graphics2D g2d)
	{
	//	System.out.println("locations.size() "+locations.size());
       for(Point p: locations)
       {
    	   if(p!=null)
		g2d.drawImage(img, p.getX(), p.getY(), width, height, null);
       }
       
		
	}
}