import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * This class illustrates the circle shape having x,y coordinates,color and radius.
 * it extends the GeometricObject and also overrides the draw method.
 *
 * @author Nisarg Nayak
 */
public class Circle extends GeometricObject{

    /** the x coordinate of the circle **/
    private double x;

    /** the y coordinate of the circle **/
    private double y;

    /** the radius of the circle **/
    private double radius;

    /** the color of the circle **/
    private Color color;

    /**
     * create circle having x,y,radius and color
     * @param x the x coordinate
     * @param y the y coordinate
     * @param radius the radius of the circle
     * @param color the color of the circle
     */
    public Circle(double x,double y,double radius,Color color) {
        super(x,y,radius,color);
        if(x>700 || y>450){
            throw new IllegalArgumentException("x and y must be less than canvas size");
        }
            this.x = x;
            this.y = y;
        if(radius <0){
            throw new ArithmeticException("Radius of circle can not be zero");
        }
            this.radius = radius;
            this.color = color;
    }

    /**
     * Overridden method of draw
     * @param gc The GraphicsContext to draw on.
     */
    @Override
    public void draw(GraphicsContext gc){

        gc.strokeOval(x - radius, y - radius, radius * 2, radius * 2);
        gc.setStroke(color);
        gc.setLineWidth(radius / 4);

    }
}
