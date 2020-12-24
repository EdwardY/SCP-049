/**
 * [Event.java]
 * The superclass for all {@code Events}
 * @author Vivian Dai
 * @version 1.0 on December 24, 2020
 */

abstract class Event {
    /** How long the {@code Event} will last for */
    private int duration;
    /** How much longer the {@code Event} has left */
    private int timeLeft;
    /** The amount of effect the {@code Event} has */
    private int effectAmount;

    /**
     * Constructor for the {@code Event} class
     * @param timeLeft the number of turns the {@code Event} has left
     * @param effectAmount the magnitude of the {@code Event}
     */
    public Event(int duration, int timeLeft, int effectAmount){
        this.duration = duration;
        this.timeLeft = timeLeft;
        this.effectAmount = effectAmount;
    }

    /**
     * Gets the total duration of the {@code Event}
     * @return duration, the total duration of the {@code Event}
     */
    public int getDuration(){
        return this.duration;
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
     * Decreases the time left by one
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
