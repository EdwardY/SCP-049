/**
 * [Player.java]
 * Stores information about the player
 * @author Damon Ma, Edward Yang, and Vivian Dai
 * @version 1.0 on January 5, 2021
 */

abstract class Player {
    /** Username of the player */
    private String username;

    /**
     * Constructor for the {@code Player} class
     * @param username the username of the {@code Player}
     */
    public Player(String username){
        this.username = username;
    }
}
