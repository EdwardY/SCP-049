//graphics imports
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;

//data structures
import java.util.ArrayList;
import java.util.HashMap;

/**
 * [Tornado.java]
 * A moving disaster
 * @author Vivian Dai
 * @version 1.0 on December 24, 2020
 */

class Tornado extends PhysicalEvent implements Moveable{

    /** The total time for how long the {@code Tornado} lasts */
    private int totalTime;

    /**
     * The constructor for the {@code Tornado} class, gives values based on level
     * @param level the level of the {@code Tonado}
     * @param x the center x position which the {@code Tornado} starts at
     * @param y the center y position which the {@code Tornado} starts at
     */
    public Tornado(int level, int x, int y){
        super(level*2, level*15, level, new Rectangle(x - level, y - level, level*2, level*2), Toolkit.getDefaultToolkit().getImage("./assets/tornado.png"));
        this.totalTime = level*2;
    }

    /**
     * The constructor for the {@code Tornado} class when time left is known, gives values based on level
     * @param level the level of the {@code Tornado}
     * @param timeLeft the time left for the {@code Tornado}
     * @param x the center x position which the {@code Tornado} starts at
     * @param y the center y position which the {@code Tornado} starts at
     */
    public Tornado(int level, int timeLeft, int x, int y){
        super(timeLeft, level*3, level, new Rectangle(x - level, y - level, level*2, level*2), Toolkit.getDefaultToolkit().getImage("./assets/tornado.png"));
        this.totalTime = level*2;
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
     * <p>
     * Does math based what portion of the lifetime of the {@code Tornado} has passed and the effectAmount (inital damage) to find 
     * amount of damage the {@code Tornado} should do in this turn.
     * </p>
     * @param game the {@code Game} this {@code Tornado} is affecting
     */
    @Override
    public void affect(Game game){
        int damage = (int)((this.getTimeLeft()/this.totalTime)*this.getEffectAmount());
        ArrayList<Building> buildings = game.getBuildings();
        HashMap<Integer, Human> humans = game.getHumanMap();
        for(int i = 0;i < buildings.size();i++){
            if(this.getAoe().contains(buildings.get(i).getX(), buildings.get(i).getY(), Building.SIZE, Building.SIZE)){
                buildings.get(i).takeDamage(damage);
            }
        }
        for(int key: humans.keySet()){
            if(this.getAoe().contains(humans.get(key).getX(), humans.get(key).getY(), NPC.SIZE, NPC.SIZE)){
                humans.get(key).takeDamage(damage);
            }
        }
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