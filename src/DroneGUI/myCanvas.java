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
 *  The following file has been copied and inherited from R J Mitchell from the University of Reading
 */
public class myCanvas {
    int xCanvasSize = 400;				// constants for relevant sizes
    int yCanvasSize = 400;
    GraphicsContext gc;

    /**
     * onstructor sets up relevant Graphics context and size of canvas
     * @param g
     */
    public myCanvas(GraphicsContext g, int xcs, int ycs) {
        gc = g;
        xCanvasSize = xcs;
        yCanvasSize = ycs;
    }
    /**
     * get size in x of canvas
     * @return xsize
     */
    public int getXCanvasSize() {
        return xCanvasSize;
    }
    /**
     * get size of xcanvas in y
     * @return ysize
     */
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
     * drawIt ... draws object defined by given image at position and size
     * @param i		image
     * @param x		xposition	in range 0..1
     * @param y
     * @param sz	size
     */
    public void drawIt (Image i, double x, double y, double sz) {
        // to draw centred at x,y, give top left position and x,y size
        // sizes/position in range 0..1, so scale to canvassize
        gc.drawImage(i, xCanvasSize * (x - sz/2), yCanvasSize*(y - sz/2), xCanvasSize*sz, yCanvasSize*sz);
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

    public void setFillColour (Color c) {
        gc.setFill(c);
    }
    /**
     * show the ball at position x,y , radius r in colour defined by col
     * @param x
     * @param y
     * @param rad
     * @param col
     */
    public void showCircle(double x, double y, double rad, char col) {
        setFillColour(colFromChar(col));									// set the fill colour
        gc.fillArc(x-rad, y-rad, rad*2, rad*2, 0, 360, ArcType.ROUND);	// fill circle
    }

    /**
     * show circle in current colour atx,y size rad
     * @param x
     * @param y
     * @param rad
     */
    public void showCircle(double x, double y, double rad) {
        gc.fillArc(x-rad, y-rad, rad*2, rad*2, 0, 360, ArcType.ROUND);	// fill circle
    }

    /**
     * Show Text .. by writing string s at position x,y
     * @param x
     * @param y
     * @param s
     */
    public void showText (double x, double y, String s) {
        gc.setTextAlign(TextAlignment.CENTER);							// set horizontal alignment
        gc.setTextBaseline(VPos.CENTER);								// vertical
        gc.setFill(Color.WHITE);										// colour in white
        gc.fillText(s, x, y);						// print score as text
    }

    /**
     * Show Int .. by writing int i at position x,y
     * @param x
     * @param y
     * @param i
     */
    public void showInt (double x, double y, int i) {
        showText (x, y, Integer.toString(i));
    }
}