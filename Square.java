import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * This class illustrates the square shape having x,y coordinates,color and size.
 * it extends the GeometricObject and also overrides the draw method.
 *
 * @author Nisarg Nayak
 */
public class Square extends GeometricObject{

    /** the x coordinate for the square **/
    private double x;

    /** the y coordinate for the square **/
    private double y;

    /** the height for the square **/
    private double height;

    /** the color for the square **/
    private Color color;

    /**
     * Creates the square having x,y,size and color
     * @param x the x coordinate
     * @param y the y coordinate
     * @param size the size of the square
     * @param color the color of the square
     */
    public Square(double x, double y, double size,Color color) {
        super(x,y,size,color);

        if(x>700 || y>450){
            throw new IllegalArgumentException("x and y must be less than canvas size");
        }
        this.x = x;
        this.y = y;

        if(size<0){
            throw new ArithmeticException("Size of the rectangle must be greater than  zero");
        }
        this.height = size;

        this.color = color;
    }

    /**
     * Overridden method of draw
     * @param gc the graphics context to draw on
     */
    @Override
    public void draw(GraphicsContext gc){
        gc.strokeRect(x,y,height,height);
        gc.setStroke(color);
    }

}
