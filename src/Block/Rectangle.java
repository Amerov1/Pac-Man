package Block;

public class Rectangle {

    @Override
    public String toString() {
        return "Rectangle \n[x=" + x + ",\n y=" + y + ",\n width=" + width
                + ",\n height=" + height + ",\n topLeft=" + topLeft
                + ",\n topRight=" + topRight + ",\n bottomRight=" + bottomRight
                + ",\n bottomLeft=" + bottomLeft + "]";
    }

    private int x;
    private int y;
    private int width;
    private int height;
    private Point topLeft;
    private Point topRight;
    private Point bottomRight;
    private Point bottomLeft;
    private Line left;
    private Line right;
    private Line bottom;
    private Line top;

    public Rectangle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.topLeft = new Point(x, y);
        this.topRight = new Point(x + width, y);
        this.bottomRight = new Point(x + width, y + height);
        this.bottomLeft = new Point(x, y + height);
        this.top = new Line(topLeft, topRight);
        this.bottom = new Line(bottomLeft, bottomRight);
        this.left = new Line(topLeft, bottomLeft);
        this.right = new Line(topRight, bottomRight);

    }

    public Line getLeft() {
        return left;
    }

    public Line getRight() {
        return right;
    }

    public Line getBottom() {
        return bottom;
    }

    public Line getTop() {
        return top;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Point getTopLeft() {
        return this.topLeft;
    }

    public Point getTopRight() {
        return this.topRight;
    }

    public Point getBottomRight() {
        return this.bottomRight;
    }

    public Point getBottomLeft() {
        return this.bottomLeft;
    }

    public Rectangle getRectangle() {
        return this;
    }

    public boolean intersects(int otherX, int otherY, int otherWidth, int otherHeight) {
        return (x < otherX + otherWidth
                && x + width > otherX
                && y < otherY + otherHeight
                && y + height > otherY);

    }
}
