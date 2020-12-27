import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

/**
 * [Earthquake.java]
 * The {@code Earthquake} {@code Event} lasts one turn and mostly targets building damage
 * @author Vivian Dai
 * @version 1.0 on December 24, 2020
 */

class Earthquake extends PhysicalEvent{
    /**
     * The constructor for the {@code Earthquake} class, passes parameters into the superclass, only lasts 1 turn
     * @param effectAmount the amount the {@code Earthquake} will affect {@code Buildings} (it will affect {@code NPCs} less)
     * @param aoe the range {@code Earthquake} can affect
     * @param sprite the {@code Image} to use for the {@code Earthquake}
     */
    public Earthquake(int duration, int timeLeft, int effectAmount, Rectangle aoe, Image sprite){
        super(1, effectAmount, aoe, sprite);
    }

    /**
     * {@code Buildings} get damaged by effectAmount while {@code Humans} get damaged by a tenth of effectAmount rounded up
     * @param game the {@code Game} that the {@code Earthquake} affects
     */
    @Override
    public void affect(Game game){
        //TODO: implement earthquake after game is made
    }

    /**
     * Draws the {@code Earthquake} on its area of effect
     * @param g the {@code Graphics} to draw the {@code Image} on
     */
    public void draw(Graphics g){
        g.drawImage(this.getSprite(), this.getAoe().x, this.getAoe().y, this.getAoe().width, this.getAoe().height, null);
        //TODO: maybe improve this later to only draw where there are buildings or something
    }
}
