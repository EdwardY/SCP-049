//graphics imports
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;

//data structures
import java.util.ArrayList;

/**
 * [Fire.java]
 * The {@code Fire} is a type of {@code PhysicalEvent} that lasts several turns and can spread
 * @author Vivian Dai
 * @version 1.0 on December 24, 2020
 */

class Fire extends PhysicalEvent{
    /**
     * Constructor for new {@code Fires}, passes parameters into the super constructor based on level
     * @param level the level of the {@code Fire}
     * @param x the center x position of the {@code Fire}
     * @param y the center y position of the {@code Fire}
     */
    public Fire(int level, int x, int y){
        //TOOD: fire, adjust values later when known
        super(level*2, level*10, level, new Rectangle(x - (level * Building.SIZE), y - (level * Building.SIZE), (level * Building.SIZE)*2, (level * Building.SIZE)*2), Toolkit.getDefaultToolkit().getImage("./assets/fire.png"));
    }

    /**
     * Constructor for the {@code Fire} class, passes parameters into the super constructor based on level
     * @param level the level of the {@code Fire}
     * @param timeLeft the time left for the {@code Fire}
     * @param x the center x position of the {@code Fire}
     * @param y the center y position of the {@code Fire}
     */
    public Fire(int level, int timeLeft, int x, int y){
        //TOOD: fire, adjust values later when known
        super(timeLeft, level*10, level, new Rectangle(x - (level * Building.SIZE), y - (level * Building.SIZE), (level * Building.SIZE)*2, (level * Building.SIZE)*2), Toolkit.getDefaultToolkit().getImage("./assets/fire.png"));
    }
    
    /**
     * Calculates the cost based on the level
     * @param level the intended level
     * @return the final cost
     */
    public static int getCostByLevel(int level){
        return level*4;
    }

    /**
     * Does damage effectAmount damage to all {@code Buildings} and {@code Humans} inside the area of effect
     * @param game the {@code Game} that gets affected by this {@code Fire}
     */
    @Override
    public void affect(Game game){
        ArrayList<Building> buildings = game.getBuildings();
        ArrayList<Human> humans = game.getHumans();
        for(int i = 0;i < buildings.size();i++){
            if(this.getAoe().contains(buildings.get(i).getX(), buildings.get(i).getY(), Building.SIZE, Building.SIZE)){
                buildings.get(i).takeDamage(this.getEffectAmount());
            }
        }
        for(int i = 0;i < humans.size();i++){
            if(this.getAoe().contains(humans.get(i).getX(), humans.get(i).getY(), NPC.SIZE, NPC.SIZE)){
                humans.get(i).takeDamage(this.getEffectAmount());
            }
        }
    }
    /**
     * Draws the {@code Fire} sprite in the area of effect
     * @param g the {@code Graphics} to draw the {@code Fire} on
     */
    public void draw(Graphics g){
        g.drawImage(this.getSprite(), this.getAoe().x, this.getAoe().y, this.getAoe().width, this.getAoe().height, null);
    }
}
