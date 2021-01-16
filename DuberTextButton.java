import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * [DuberTextButton.java]
 * A slightly less painful button than JButtons
 * @author Vivian Dai
 * @version 1.0 on January 16, 2021
 */
class DuberTextButton extends DuberButton{
    /** The text to write in the button */
    private String text;
    /** Location to draw the text */
    private int x, y;

    /**
     * Constructor for the {@code DuberButton}
     * @param text the text to write in the button
     * @param boundaries the boundaries of the button
     */
    DuberTextButton(String text, Rectangle boundaries){
        super(boundaries);
        this.text = text;
        //TODO: the x and y might be slightly scuffed because size of each character is not being considered
        this.x = (int)boundaries.getCenterX() - ((text.length()/(int)text.lines().count())/2);
        this.y = (int)boundaries.getCenterY() - ((int)text.lines().count())/2;
    }

    /**
     * Draws the {@code DuberButton}
     * @param g the {@code Graphics} to draw on
     */
    public void draw(Graphics g){
        g.setFont(new Font("Courier", Font.BOLD, 18));
        g.setColor(Color.BLACK);
        g.drawString(text, x, y);
    }
}
