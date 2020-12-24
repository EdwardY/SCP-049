/**
 * [DestroyableAndRepairable.java]
 * An interface that defines methods necessary for things that can have a changeable health
 * @author Vivian Dai
 * @version 1.0 on December 24, 2020
 */
interface DestroyableAndRepairable {
    public void takeDamage(int damage);
    public void repair(int repair);
}
