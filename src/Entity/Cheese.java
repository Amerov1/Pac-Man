package Entity;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.swing.ImageIcon;

import Block.Point;
import Block.Wall;

public class Cheese extends Entity {

	
    private Image img;
    Wall wall;
    Player player;
    public List<Point> shapes;


    public Cheese(Wall wall, Player player) {
    	
        setHeight( 32);
        setWidth( 32);
        this.wall = wall;
        this.player = player;
        img = new ImageIcon("C:\\Users\\Alaa\\Desktop\\github\\PacmanNow\\src\\pacman\\cheese.png").getImage();
        shapes = new ArrayList<Point>();
        CreateCheese();
    }
    public void CreateCheese()
    {
    	for( setY(50);getY()<=600;setY(getY()+20))
    	{
    		for( setX(50);getX()<=600;setX(getX()+20))
    		{
    			if(wall.check(this)==false)
    			{
    				shapes.add(new Point(getX(),getY()));
    			}
    		}
    	}
    }
    public void check() {
        shapes = shapes.stream().filter(i -> {
            // Überprüfe, ob `x` mit dem Spieler überlappt
            boolean isOverlapping = 
            		(i.getX() < player.getX() + player.getWidth()
                            && i.getX() + this.width > player.getX()
                            && i.getY() < player.getY() + player.getHeight()
                            && i.getY() + this.height > player.getY());
System.out.println("score :"+player.getScore());
System.out.println("cheese number :"+shapes.size());
            if (isOverlapping) {
                player.increaseScore(); // Punkte erhöhen, wenn Überlappung erkannt wird
                return false; // Entferne das Shape, da es getroffen wurde
            }
            return true; // Behalte das Shape in der Liste
        }).collect(Collectors.toList());
    }


    public void update() {
        check();
    }

    public void draw(Graphics2D g2d) {
        //	system.out.println("locations.size() "+locations.size());
    //	AffineTransform oldT=g2d.getTransform();
    	System.out.println("size"+shapes.size());
        for (Point p : shapes) {
        	//g2d.translate(p.getX(), p.getY());
            g2d.drawImage(img, p.getX(), p.getY(), getWidth(), getHeight(), null);
        }
    //    g2d.setTransform(oldT);
    }
}
