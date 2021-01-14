//graphics imports
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;

/**
 * [Snow.java]
 * A class with an {@code Event} that can slow down production and building/upgrades of {@code Buildings}
 * @author Vivian Dai
 * @version 1.0 on December 24, 2020
 */

class Snow extends PhysicalEvent{
    /**
     * Constructor for the {@code Snow} class, gives parent constructor values based on level
     * @param level the level of the {@code Snow}
     * @param x the middle x position of the {@code Snow}
     * @param y the middle y position of the {@code Snow}
     */ 
    public Snow(int level, int x, int y){
        super(level*2, level*3, level, new Rectangle(x - level, y - level, level*2, level*2), Toolkit.getDefaultToolkit().getImage("./assets/tornado.png"));
    }

    /**
     * Constructor for the {@code Snow} class when time left is known
     * @param level the level of the {@code Snow}
     * @param timeLeft the time left for the {@code Snow}
     * @param x the middle x position of the {@code Snow}
     * @param y the middle y position of the {@code Snow}
     */ 
    public Snow(int level, int timeLeft, int x, int y){
        super(timeLeft, level*3, level, new Rectangle(x - level, y - level, level*2, level*2), Toolkit.getDefaultToolkit().getImage("./assets/tornado.png"));
    }

    /**
     * Calculates the cost based on the level
     * @param level the intended level
     * @return the final cost
     */
    public static int getCostByLevel(int level){
        return level*5;
    }
    
    /**
     * Slows down production, building, and upgrading in the area of effect by a factor of effectAmount
     * @param game the {@code Game} the {@code Snow} is affecting
     */
    @Override
    public void affect(Game game){
        //TODO: implement snow effect after game class is made
    }

    /**
     * Draws the {@code Image} for {@code Snow} on the {@code Graphics} g
     * @param g the {@code Graphics} to draw on
     */
    public void draw(Graphics g){
        g.drawImage(this.getSprite(), this.getAoe().x, this.getAoe().y, this.getAoe().width, this.getAoe().height, null);
    }
}
