import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

/**
 * [Fire.java]
 * The {@code Fire} is a type of {@code PhysicalEvent} that lasts several turns and can spread
 * @author Vivian Dai
 * @version 1.0 on December 24, 2020
 */

class Fire extends PhysicalEvent{
    /**
     * Constructor for the {@code Fire} class
     * @param timeLeft how much longer the {@code Fire} has to burn
     * @param effectAmount damage done per turn
     * @param level the level of the {@code Fire}
     * @param aoe the area which the {@code Fire} can affect
     * @param sprite the {@code Image} to draw for the {@code Fire}
     */
    public Fire(int timeLeft, int effectAmount, int level, Rectangle aoe, Image sprite){
        super(timeLeft, effectAmount, level, aoe, sprite);
    }

    /**
     * Does damage effectAmount damage to everything inside the aoe
     * @param game the {@code Game} that gets affected by this {@code Fire}
     */
    @Override
    public void affect(Game game){
        //TODO: implement fire effect after game object is made
    }
    /**
     * Draws the {@code Fire} sprite in some random places in the area of effect
     * @param g the {@code Graphics} to draw the {@code Fire} on
     */
    public void draw(Graphics g){
        for(int i = 0;i < (this.getAoe().width + this.getAoe().height)/10;i++){
            g.drawImage(this.getSprite(), (int)(Math.random()*this.getAoe().width) + this.getAoe().x, (int)(Math.random()*this.getAoe().height) + this.getAoe().y, null);
        } 
        //TODO: restrict the bottom right boundaries after getting a fire sprite
    }
}
