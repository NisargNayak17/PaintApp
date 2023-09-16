import javafx.scene.canvas.GraphicsContext;
/**
 * This interface illustrates the draw method for all the shapes
 *
 * @author Nisarg Nayak
 */
public interface Drawable {

    /**
     * Every shape must be able to draw
     * @param gc the graphics context to draw on
     */
    void draw(GraphicsContext gc);
}
