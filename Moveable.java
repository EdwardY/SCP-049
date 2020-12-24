/**
 * [Moveable.java]
 * Defines methods for things that should be able to move
 * @author Vivian Dai
 * @version 1.0 on December 24, 2020
 */

public interface Moveable {
    /**
     * Moves a distance amount up
     * @param distance the distance to move
     */
    public void moveUp(int distance);

    /**
     * Moves a distance amount down
     * @param distance the distance to move
     */
    public void moveDown(int distance);

    /**
     * Moves a distance amount left
     * @param distance the distance to move
     */
    public void moveLeft(int distance);
    
    /**
     * Moves a distance amount right
     * @param distance the distance to move
     */
    public void moveRight(int distance);
}
