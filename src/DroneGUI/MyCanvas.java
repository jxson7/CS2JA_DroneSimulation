package DroneGUI;
import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

/**
 * @author shsmchlr, jason jay dookarun for: fillCanvas
 *  Class to handle a canvas, used by different GUIs
 */
public class MyCanvas {
    int xCanvasSize;                // constants for relevant sizes, default values set
    int yCanvasSize;
    GraphicsContext gc;

    /**
     * constructor sets up relevant Graphics context and size of canvas
     *
     * @param g
     */
    public MyCanvas(GraphicsContext g, int xcs, int ycs) {
        gc = g;
        xCanvasSize = xcs;
        yCanvasSize = ycs;
    }

    public int getXCanvasSize() {
        return xCanvasSize;
    }

    public int getYCanvasSize() {
        return yCanvasSize;
    }

    /**
     * clear the canvas
     */
    public void clearCanvas() {
        gc.clearRect(0, 0, xCanvasSize, yCanvasSize);        // clear canvas
    }

    /**
     * drawImage ... draws object defined by given image at position and size
     *
     * @param i  image
     * @param x  x position	in range 0..1
     * @param y: the y position
     * @param sz size
     */
    public void drawImage(Image i, double x, double y, double sz) {
        // to draw centred at x,y, give top left position and x,y size
        // sizes/position in range 0.. canvas size
        gc.drawImage(i, x - sz / 2, y - sz / 2, sz, sz);
    }

    /**
     * @param x: dimensions of x value of canvas
     * @param y: y value of the canvas
     */
    public void fillCanvas(int x, int y) {
        gc.setFill(Color.GHOSTWHITE);
        gc.fillRect(0, 0, x, y);
        gc.setStroke(Color.BLACK);
        gc.strokeRect(0, 0, x, y);
    }

    /**
     * Show Text .. by writing string s at position x,y
     *
     * @param x
     * @param y
     * @param s
     */
    public void showText(double x, double y, String s) {
        gc.setTextAlign(TextAlignment.CENTER);                            // set horizontal alignment
        gc.setTextBaseline(VPos.CENTER);                                // vertical
        gc.setFill(Color.WHITE);                                        // colour in white
        gc.fillText(s, x, y);                                            // print score as text
    }
}
