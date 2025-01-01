package Block;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import Entity.Entity;

public class Wall {

    private ArrayList<Rectangle> block;
    private int[][] map;
    private  int cellWidth;
    private int cellHeight;
    private Image imgWall;
    public int[][] getMap()
    {
    	return map;
    }
    public Wall(int cellSize) {
    	imgWall = new ImageIcon("C:\\Users\\Alaa\\Desktop\\github\\PacmanNow\\src\\pacman\\wall.png").getImage();
    	createMap();
    	cellWidth=cellSize;
    	cellHeight=cellSize;
        block = new ArrayList<Rectangle>();
        initWall();
    }
    public int getMapWidth()
    {
    	return map[0].length;
    }
    public int getMapHeight()
    {
    	return map.length;
    }
	public void createMap()
	{
        map=new int[][]{
        		
        		{0,0,0,0,0,0,0,0,0,0,0},
        		{1,1,1,1,1,1,1,1,1,1,1},
        		{1,3,3,3,3,3,3,3,3,3,1},
        		{1,3,3,3,4,3,3,3,3,3,1},
        		{1,3,3,1,3,3,3,1,3,4,1},
        		{1,3,3,1,1,1,1,1,3,3,1},
        		{1,3,3,3,3,3,3,3,3,3,1},
        		{0,3,2,3,3,1,3,3,3,3,0},
        		{1,3,3,3,1,1,1,0,0,0,1},
        		{1,3,3,3,3,1,3,3,3,4,1},
        		{1,3,3,3,3,3,3,3,3,3,1},
        		{1,1,1,1,1,1,1,1,1,1,1}
        		
            };
	}
	public void initWall()
	{
		for(int row=0;row<getMapHeight();row++)
		{
			for(int col=0;col<getMapWidth();col++)
			{
				if(map[row][col]==1)
				{
					block.add(new Rectangle(col*cellWidth,row*cellHeight,cellWidth,cellHeight));
				}
			}
		}
	}
    public Rectangle[] getWall() {
        Rectangle arr[] = new Rectangle[block.size()];
        arr = block.toArray(arr);
        return arr;
    }
    public boolean check(Entity entity)
    {
    	if(entity == null)
    	{
    		return false;
    	}
    	else
    	{
    	for(Rectangle r:block)
    		return r.intersects(entity.getX(), entity.getY(), entity.getWidth(), entity.getHeight());
    	}
    	return false;
    }
    public void draw(Graphics2D g2d) {
        for (Rectangle r : block) {
            g2d.drawImage(imgWall, r.getX(), r.getY(), r.getWidth(), r.getHeight(), null);
    //        g2d.setColor(Color.RED);
    //        g2d.drawRect(r.getX(), r.getY(), r.getWidth(), r.getHeight()); // Zeichne die Umrandung der Rechtecke
        }
    }
}
