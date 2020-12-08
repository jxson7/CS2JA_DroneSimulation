package DroneGUI;



import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.text.TextAlignment;

/**
 * @author shsmchlr
 *  Class to handle a canvas, used by different GUIs
 */
public class MyCanvas {
    int xCanvasSize;				// constants for relevant sizes, default values set
    int yCanvasSize;
    GraphicsContext gc;

    /**
     * constructor sets up relevant Graphics context and size of canvas
     * @param g: graphic context
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
        gc.clearRect(0,  0,  xCanvasSize,  yCanvasSize);		// clear canvas
    }
    /**
     * drawImage ... draws object defined by given image at position and size
     * @param i		image
     * @param x		x position	in range 0..1
     * @param y:    y position
     * @param sz	size
     */
    public void drawImage (Image i, double x, double y, double sz) {
        // to draw centred at x,y, give top left position and x,y size
        // sizes/position in range 0.. canvassize
        gc.drawImage(i, x - sz/2, y - sz/2, sz, sz);
    }

    /**
     * function to convert char c to actual colour used
     * @param c
     * @return Color
     */
    Color colFromChar (char c){
        Color ans = Color.BLACK;
        switch (c) {
            case 'y' :	ans = Color.YELLOW;
                break;
            case 'w' :	ans = Color.WHITE;
                break;
            case 'r' :	ans = Color.RED;
                break;
            case 'g' :	ans = Color.GREEN;
                break;
            case 'b' :	ans = Color.BLUE;
                break;
            case 'o' :	ans = Color.ORANGE;
                break;
        }
        return ans;
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
     * set the fill colour to color c
     * @param c: colour c
     */
    public void setFillColour (Color c) {
        gc.setFill(c);
    }
    /**
     * show the ball at position x,y , radius r in colour defined by col
     * @param x: x coordinate
     * @param y: y coordinate
     * @param rad: radius
     * @param col: colour
     */
    public void showCircle(double x, double y, double rad, char col) {
        setFillColour(colFromChar(col));			// set the fill colour
        showCircle(x, y, rad);						// show the circle
    }

    /**
     * show the ball in the current colour at x,y size rad
     * @param x: x val
     * @param y: y val
     * @param rad: radius
     */
    public void showCircle(double x, double y, double rad) {
        gc.fillArc(x-rad, y-rad, rad*2, rad*2, 0, 360, ArcType.ROUND);	// fill circle
    }

    /**
     * Show Text .. by writing string s at position x,y
     * @param x: x val
     * @param y: y val
     * @param s: string
     */
    public void showText (double x, double y, String s) {
        gc.setTextAlign(TextAlignment.CENTER);							// set horizontal alignment
        gc.setTextBaseline(VPos.CENTER);								// vertical
        gc.setFill(Color.WHITE);										// colour in white
        gc.fillText(s, x, y);											// print score as text
    }

    /**
     * Show Int .. by writing int i at position x,y
     * @param x: x val
     * @param y: y val
     * @param i: integer i
     */
    public void showInt (double x, double y, int i) {
        showText (x, y, Integer.toString(i));
    }
}
