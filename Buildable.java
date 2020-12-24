/**
 * Buildable.java
 * Abstract class for all the buildings, allowing them to be built
 * @author Edward Yang
 * @version 1.0 on December 24th 2020
 */

interface Buildable {
    

    /**
     * An abstract method that builds a building 
     * @param price The amount of money required to build a building
     */
    public void build(int price);
}
