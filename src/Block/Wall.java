package Block;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class Wall  {


	private ArrayList<Rectangle> block;

	public Wall()
	{block=new ArrayList<Rectangle>(14);
		block.add(new Rectangle(10,0,850,30));
		block.add(new Rectangle(10,20,30,150));
		block.add(new Rectangle(10,150,100,30));
		block.add(new Rectangle(100,150,30,150));
		block.add(new Rectangle(10,280,120,30));
		block.add(new Rectangle(10,380,120,30));
		block.add(new Rectangle(10,400,30,150));
		block.add(new Rectangle(10,520,100,30));
		block.add(new Rectangle(100,520,30,150));
		block.add(new Rectangle(100,660,760,30));
		block.add(new Rectangle(850,0,30,290));
	    block.add(new Rectangle(850,380,30,310));//
		block.add(new Rectangle(730,280,150,30));
		block.add(new Rectangle(730,370,150,30));
        block.add(new Rectangle(250,450,40,150));
        block.add(new Rectangle(280,100,320,40));
        block.add(new Rectangle(600,450,40,150));
        block.add(new Rectangle(250,450,390,40));
        block.add(new Rectangle(420,180,40,220));
        block.add(new Rectangle(320,260,250,40));
	}
	public Rectangle [] getWall()
	{Rectangle arr[]=new Rectangle[block.size()];
			arr=block.toArray(arr);
		return arr;
	}

	public  void draw(Graphics2D g2d)
	{
        for(Rectangle r : block){
        	g2d.setPaint(Color.BLACK);         	
g2d.drawRoundRect(r.getX(),r.getY(),r.getWidth(),r.getHeight(),20,20);
        	g2d.setPaint(Color.BLUE);  
g2d.fillRoundRect(r.getX(),r.getY(),r.getWidth(),r.getHeight(),10,10);
        }
	}
}
