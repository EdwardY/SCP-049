import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

/**
 * [Tornado.java]
 * A moving disaster
 * @author Vivian Dai
 * @version 1.0 on December 24, 2020
 */

class Tornado extends PhysicalEvent implements Moveable{
    /**
     * The constructor for the {@code Tornado} class
     * @param timeLeft the amount of time the {@code Tornado} has left
     * @param effectAmount the original amount of damage the {@code Tornado} starts with
     * @param level the level of the {@code Tonado}
     * @param aoe the area which the {@code Tornado} can affect
     * @param sprite the {@code Image} to draw when displaying the {@code Tornado}
     */
    public Tornado(int timeLeft, int effectAmount, int level, Rectangle aoe, Image sprite){
        super(timeLeft, effectAmount, level, aoe, sprite);
    }

    /**
     * <p>
     * Does math based what portion of the lifetime of the {@code Tornado} has passed and the effectAmount (inital damage) to find 
     * amount of damage the {@code Tornado} should do in this turn.
     * </p>
     * @param game the {@code Game} this {@code Tornado} is affecting
     */
    @Override
    public void affect(Game game){
        //TODO: implement tornado effect after creating the game
    }

    /**
     * Draws the sprite for the {@code Tornado} on the {@code Graphics} g
     * @param g the {@code Graphics} to draw the {@code Tornado} on
     */
    public void draw(Graphics g){
        g.drawImage(this.getSprite(), this.getAoe().x, this.getAoe().y, this.getAoe().width, this.getAoe().height, null);
    }

    /**
     * Translates the {@code Tornado} xDistance horizontally and yDistance vertically
     * @param xDistance the horizontal distance to translate
     * @param yDistance the vertical distance to translate
     */
    public void translate(int xDistance, int yDistance){
        this.getAoe().translate(xDistance, yDistance);
    }
}