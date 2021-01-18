//graphics imports
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
     * Constructor for the {@code DuberTextButton}
     * @param text the text to write in the button
     * @param boundaries the boundaries of the button
     */
    DuberTextButton(String text, Rectangle boundaries){
        super(boundaries);
        this.text = text;
        this.x = boundaries.x + 18;
        this.y = boundaries.y + 18;
    }

    /**
     * Draws the {@code DuberTextButton} if active
     * @param g the {@code Graphics} to draw on
     */
    public void draw(Graphics g){
        if(active){    
            g.setColor(Color.WHITE);
            g.fillRect(super.boundaries.x, super.boundaries.y, super.boundaries.width, super.boundaries.height);
            g.setColor(Color.BLACK);
            g.drawRect(super.boundaries.x, super.boundaries.y, super.boundaries.width, super.boundaries.height);
            g.setFont(new Font("Courier", Font.BOLD, 18));
            g.drawString(text, x, y);
        }
    }

    /**
     * Gets the text
     * @return text, the String for the text this button contains
     */
    public String getText(){
        return this.text;
    }
}
