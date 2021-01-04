import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import java.util.ArrayList;

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
     * The constructor for the {@code Tornado} class
     * @param timeLeft the amount of time the {@code Tornado} has left
     * @param effectAmount the original amount of damage the {@code Tornado} starts with
     * @param level the level of the {@code Tonado}
     * @param aoe the area which the {@code Tornado} can affect
     * @param sprite the {@code Image} to draw when displaying the {@code Tornado}
     */
    public Tornado(int timeLeft, int effectAmount, int level, Rectangle aoe, Image sprite){
        super(timeLeft, effectAmount, level, aoe, sprite);
        this.totalTime = timeLeft;
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
        ArrayList<Human> humans = game.getHumans();
        for(int i = 0;i < buildings.size();i++){
            if(this.getAoe().contains(buildings.get(i).getX(), buildings.get(i).getY())){
                buildings.get(i).takeDamage(damage);
            }
        }
        for(int i = 0;i < humans.size();i++){
            if(this.getAoe().contains(humans.get(i).getX(), humans.get(i).getY())){
                humans.get(i).takeDamage(damage);
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