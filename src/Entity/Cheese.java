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
import Block.Rectangle;
import Block.Wall;

public class Cheese extends Entity {

	
    private Image img;
    private Player player;
    private List<Point> shapes;
    private Wall wall;
    public Cheese(Wall wall,Player player,int cellSize) {
    	this.wall=wall;
        height=cellSize;
        width= cellSize;
        this.player = player;
        img = new ImageIcon("C:\\Users\\Alaa\\Desktop\\github\\PacmanNow\\src\\pacman\\cheese.png").getImage();
        shapes = new ArrayList<Point>();
        CreateCheese();
    }
    public void setDefault()
    {shapes.clear();
    	CreateCheese();
    }
    public void CreateCheese() {
        int[][] _map = wall.getMap();
        for (int row = 0; row < _map.length; row++) {
            for (int col = 0; col < _map[0].length; col++) {
                if (_map[row][col] == 3) { // Nur Zellen mit "0" sind für Käse
                    int x = col * width;  // Spalte -> Pixel-X
                    int y = row * height; // Zeile -> Pixel-Y
                    shapes.add(new Point(x, y));        // Käseposition in Pixeln speichern
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
            if (isOverlapping) {
                player.increaseScore(); // Punkte erhöhen, wenn Überlappung erkannt wird
                return false; // Entferne das Shape, da es getroffen wurde
            }
            return true; // Behalte das Shape in der Liste
        }).collect(Collectors.toList());
    }

    public int getShapesSize()
    {
    	return shapes.size();
    }

    public void update() {
        check();
    }

    public void draw(Graphics2D g2d) {
        //	system.out.println("locations.size() "+locations.size());
    //	AffineTransform oldT=g2d.getTransform();
   // 	System.out.println("size"+shapes.size());
        for (Point p : shapes) {
        	//g2d.translate(p.getX(), p.getY());
            g2d.drawImage(img, p.getX(), p.getY(),width, height, null);
        }
    //    g2d.setTransform(oldT);
    }
}
