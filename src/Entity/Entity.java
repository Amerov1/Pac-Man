package Entity;

import Block.Rectangle;
import Block.Wall;

public class Entity {

	public int x;
	public int y;
	public int width;
	public  int height;
	public int speed;
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public boolean check(Wall wall,Entity e)
	{
		for(Rectangle r : wall.getWall())
		if(r!=null&& e!=null)
		{
            if (r != null && r.intersects(e.x, e.y, e.width, e.height)) //we put extra width and height because they are tiny comared to the wall
            	{//some of them are under the wall
                return true;
            }
		}
		return false;
	}
	

	
	
}
