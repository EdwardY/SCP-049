//graphics imports
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;

//utilities (just a datastructure) imports
import java.util.ArrayList;
import java.util.HashMap;

/**
 * [Earthquake.java]
 * The {@code Earthquake} {@code Event} lasts one turn and mostly targets building damage
 * @author Vivian Dai
 * @version 1.0 on December 24, 2020
 */

class Earthquake extends PhysicalEvent{
    /**
     * The constructor for new {@code Earthquakes}, passes values into the superclass based on level, only lasts 1 turn
     * @param effectAmount the amount the {@code Earthquake} will affect {@code Buildings} (it will affect {@code NPCs} less)
     * @param x the x center location of the {@code Earthquake}
     * @param y the y center location of the {@code Earthquake}
     */
    public Earthquake(int level, int x, int y){
        super(1, level*200, level, new Rectangle(x - (level * Building.SIZE), y - (level * Building.SIZE), (level * Building.SIZE)*2, (level * Building.SIZE)*2), Toolkit.getDefaultToolkit().getImage("./assets/earthquake.png"));
    }

    /**
     * The constructor for the {@code Earthquake} class, passes values into the superclass based on level, only lasts 1 turn
     * @param effectAmount the amount the {@code Earthquake} will affect {@code Buildings} (it will affect {@code NPCs} less)
     * @param timeLeft the time left in the {@code Earthquake}
     * @param x the top left x location of the {@code Earthquake}
     * @param y the top left y location of the {@code Earthquake}
     */
    public Earthquake(int level, int timeLeft, int x, int y){
        super(timeLeft, level*200, level, new Rectangle(x, y, (level * Building.SIZE)*2, (level * Building.SIZE)*2), Toolkit.getDefaultToolkit().getImage("./assets/earthquake.png"));
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
     * {@code Buildings} get damaged by effectAmount while {@code Humans} get damaged by a tenth of effectAmount rounded up
     * @param game the {@code Game} that the {@code Earthquake} affects
     */
    @Override
    public void affect(Game game){
        ArrayList<Building> buildings = game.getBuildings();
        HashMap<Integer, Human> humans = game.getHumanMap();
        for(int i = 0;i < buildings.size();i++){
            if(this.getAoe().contains(buildings.get(i).getX(), buildings.get(i).getY(), Building.SIZE, Building.SIZE)){
                buildings.get(i).takeDamage(this.getEffectAmount());
            }
        }
        for(int key:humans.keySet()){
            if(this.getAoe().contains(humans.get(key).getX(), humans.get(key).getY(), NPC.SIZE, NPC.SIZE)){
                humans.get(key).takeDamage((int)Math.ceil(this.getEffectAmount()/10));
            }
        }
    }

    /**
     * Draws the {@code Earthquake} on its area of effect
     * @param g the {@code Graphics} to draw the {@code Image} on
     */
    public void draw(Graphics g){
        g.drawImage(this.getSprite(), this.getAoe().x, this.getAoe().y, this.getAoe().width, this.getAoe().height, null);
    }
}
