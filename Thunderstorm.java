import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

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
     * positions are set by the position of aoe.
     * </p>
     * @param aoe the area which the {@code Thunderstorm} can affect
     * @param sprite the {@code Image} to draw when drawing a {@code Thunderstorm}
     * @param strikes the number of strikes the {@code Thunderstom} has
     */
    public Thunderstorm(Rectangle aoe, Image sprite, int strikes){
        super(Integer.MAX_VALUE, 1, aoe, sprite);
        this.strikesLeft = strikes;
        this.xStrikePos = aoe.x;
        this.yStrikePos = aoe.y;
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
        //TODO: write in the thunderstorm effect after creating game class
    }

    /**
     * Draws the sprite in the strike position
     * @param g the {@code Graphics} to draw the {@code Thunderstom} on
     */
    public void draw(Graphics g){
        g.drawImage(this.getSprite(), xStrikePos, yStrikePos, null);
    }
}
