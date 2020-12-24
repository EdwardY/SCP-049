/**
 * [DestroyableAndRepairable.java]
 * An interface that defines methods necessary for things that can have a changeable health
 * @author Vivian Dai
 * @version 1.0 on December 24, 2020
 */
interface DestroyableAndRepairable {
    /**
     * Takes damage amount of damage by decreasing the health by damage amount
     * @param damage the amount of damage to take
     */
    public void takeDamage(int damage);
    /**
     * Adds repair amount of health back 
     * @param repair the amount of health to repair by
     */
    public void repair(int repair);
}
