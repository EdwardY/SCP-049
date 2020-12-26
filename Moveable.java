/**
 * [Moveable.java]
 * Defines methods for things that should be able to move
 * @author Vivian Dai
 * @version 1.0 on December 24, 2020
 */

public interface Moveable {
    /**
     * Translates xDistance horizontally and yDistance vertically
     * @param xDistance the horizontal distance to translate
     * @param yDistance the vertical distance to translate
     */
    public void translate(int xDistance, int yDistance);
}
