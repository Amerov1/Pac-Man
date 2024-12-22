package Entity;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;

import Block.Point;
import Block.Wall;

public class Cheese extends Entity {

	
    private Image img;
    Wall wall;
    Player player;
    public Map<Integer,Point> shapes;

    public Cheese(Wall wall, Player player) {
        height = 32;
        width = 32;
        this.wall = wall;
        this.player = player;
        img = new ImageIcon("C:\\Users\\Alaa\\Desktop\\github\\PacmanNow\\src\\pacman\\cheese.png").getImage();
        shapes = new HashMap<Integer,Point>();
    }

    public void check(Entity e1) {
        for (Integer i : shapes.keySet()) {
        	Point p= shapes.get(i);
            if (this != null && e1 != null) {
                if (p.getX() < e1.x + e1.width && p.getX() + width > e1.x && p.getY() < e1.y + e1.height && p.getY() + height > e1.y) {
                    shapes.remove(i);
                	player.increaseScore();;   
                }
            }
        }
    }

    public void update() {
        check(player);
    }

    public void draw(Graphics2D g2d) {
        //	system.out.println("locations.size() "+locations.size());
    	AffineTransform oldT=g2d.getTransform();
        for (Point p : shapes.values()) {
         //   if (p != null) {
        	g2d.translate(p.getX(), p.getY());
            g2d.drawImage(img, 0, 0, width, height, null);
          //  }
        }
        g2d.setTransform(oldT);

    }
}
