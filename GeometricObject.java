import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * This is the abstract class and, it implements the Drawable interface. it illustrates
 * the color,size and x,y coordinates of the shape.
 *
 * @author Nisarg Nayak
 */
public abstract class GeometricObject implements Drawable{

    /** the color of the shape **/
    private Color color;

    /** the size of the shape **/
    private double size;

    /** the x coordinate of the shape **/
    private double x;

    /** the y coordinate of the shape **/
    private double y;

    /**
     * Create a Geometric object having x,y,size and color
     * @param x the x coordinate
     * @param y the y coordinate
     * @param size the size of the shape
     * @param color the color of the shape
     */
    public GeometricObject(double x, double y,double size,Color color) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.color = color;

    }

    /**
     * Draw the object on the canvas
     * @param gc The GraphicsContext to draw on
     */
    public abstract void draw(GraphicsContext gc);

    /**
     * get the color of the shape
     * @return the color
     */
    public Color getColor() {
        return color;
    }

    /**
     * set the color of the shape
     * @param color the color
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Get the size of the shape
     * @return the size
     */
    public double getSize() {
        return size;
    }

    /***
     * set the size of the shape
     * @param size the size
     */
    public void setSize(double size) {
        this.size = size;
    }

    /**
     * get the x coordinate
     * @return the x coordinate
     */
    public double getX() {
        return x;
    }

    /**
     * set the x coordinate
     * @return the x coordinate
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * get the y coordinate
     * @return the y coordinate
     */
    public double getY() {
        return y;
    }

    /**
     * set the y coordinate
     * @param y the y coordinate
     */
    public void setY(double y) {
        this.y = y;
    }
}
