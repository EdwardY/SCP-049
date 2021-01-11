//graphics imports
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;

//data structures useds
import java.util.ArrayList;

/**
 * [Thunderstorm.java]
 * {@code Thunderstorm} class, can hit {@code Buildings} with lightning
 * @author Vivian Dai
 * @version 1.0 on December 24, 2020
 */

class Thunderstorm extends PhysicalEvent{
    /** The number of strikes left in the {@code Thunderstorm} */
    private int strikesLeft;
    /** The position for where the next strike will be */
    private int xStrikePos, yStrikePos;
    
    /**
     * <p>
     * The constructor for the {@code Thunderstorm} class. The {@code Thunderstorm} can go on infinitely long as long as the strike 
     * isn't getting used. The strike from the {@code Thunderstorm} will level down a {@code Building} by 1 level. The next strike 
     * positions are set by the x and y parameters.
     * </p>
     * @param level the level of the {@code Thunderstorm}
     * @param x the middle of the x of where the {@code Thunderstorm} will be
     * @param y the middle of the y of where the {@code Thunderstorm} will be
     */
    public Thunderstorm(int level, int x, int y){
        //TODO: thunderstomrm, adjust things later
        super(Integer.MAX_VALUE, 1, level, new Rectangle(x - level, y - level, level*2, level*2), Toolkit.getDefaultToolkit().getImage("./assets/lightning.png"));
        this.strikesLeft = level;
        this.xStrikePos = x;
        this.yStrikePos = y;
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
     * Gets how many strikes the {@code Thunderstorm} has left
     * @return strikesLeft, the number of strikes left
     */
    public int getStrikesLeft(){
        return this.strikesLeft;
    }

    /**
     * Sets the next position for the {@code Thunderstorm} to strike if it is in the aoe
     * @param xPos the x coordinate of the new place to set as a strike point
     * @param yPos the y coordinate of the new place to set as a strike point
     */
    public void setNextStrikePos(int xPos, int yPos){
        if(this.getAoe().contains(xPos, yPos)){
            this.xStrikePos = xPos;
            this.yStrikePos = yPos;
        }
    }

    /**
     * Levels down the {@code Building} that contains the point made by xStrikePos and yStrikePos
     * @param game the {@code Game} this {@code Thunderstorm} affects
     */
    @Override
    public void affect(Game game){
        ArrayList<Building> buildings = game.getBuildings();
        for(int i = 0;i < buildings.size();i++){
            Rectangle buildingArea = new Rectangle(buildings.get(i).getX(), buildings.get(i).getX(), Building.SIZE, Building.SIZE);
            if(buildingArea.contains(this.xStrikePos, this.yStrikePos)){
                buildings.get(i).downgrade();
            }
        }
    }

    /**
     * Draws the sprite in the strike position
     * @param g the {@code Graphics} to draw the {@code Thunderstom} on
     */
    public void draw(Graphics g){
        g.drawImage(this.getSprite(), xStrikePos, yStrikePos, null);
    }
}
