package Entity;

import Block.Rectangle;
import Block.Wall;

public class Entity {

    protected int x;
    protected int y;
    protected int width;
    protected int height;

    public Entity()
    {
    	x=0;
    	y=0;
    	width=0;
    	height=0;
    }
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
public void setWidth(int w)
{
	width=w;
}
public int getWidth()
{
	return width;
}
public int getHeight()
{
	return height;
}
public void setHeight(int h)
{
	height=h;
}
    public boolean check(Wall wall,Entity e) {
    	boolean bb=false;
        for (Rectangle r : wall.getWall()) {
        	bb=r.intersects(e.getX(), e.getY(), e.getWidth(), e.getHeight());
        	
                if (bb) //we put extra width and height because they are tiny comared to the wall
                {
                    return true;
                }
            
        }
        return false;
    }
}
