/**
 * [Event.java]
 * The superclass for all {@code Events}
 * @author Vivian Dai
 * @version 1.0 on December 24, 2020
 */

abstract class Event {
    /** How much longer the {@code Event} has left */
    private int timeLeft;
    /** The amount of effect the {@code Event} has */
    private int effectAmount;
    /** The level of the {@code Event} */
    private int level;

    /**
     * Constructor for the {@code Event} class
     * @param timeLeft the number of turns the {@code Event} has left
     * @param effectAmount the magnitude of the {@code Event}
     * @param level the level of the {@code Event}
     */
    public Event(int timeLeft, int effectAmount, int level){
        this.timeLeft = timeLeft;
        this.effectAmount = effectAmount;
        this.level = level;
    }

    /**
     * Gets the time left for the {@code Event}
     * @return timeLeft, the number of turns left in the {@code Event}
     */
    public int getTimeLeft(){
        return this.timeLeft;
    }

    /**
     * Gets the amount of effect the {@code Event} has
     * @return effectAmount, the amount of effect the {@code Event has}
     */
    public int getEffectAmount(){
        return this.effectAmount;
    }

    /**
     * Gets the level of the {@code Event}
     * @return level, the level of the {@code Event}
     */
    public int getLevel(){
        return this.level;
    }

    /**
     * Decreases the time left by one turn
     */
    public void decreaseTimeLeft(){
        this.timeLeft -= 1;
    }

    /**
     * Does the effect
     * @param game the {@code Game} that gets affected
     */
    public abstract void affect(Game game);
}
