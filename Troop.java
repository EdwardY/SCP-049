/**
 * [Troop.java]
 * An interface containing methods that are used by "troop" type objects.
 * @author Damon Ma
 * @version 1.0 on December 24, 2020
 */

interface Troop {
    /**
     * A method a troop uses to attack another NPC (non-player character).
     * @param target The target NPC of the attacking troop.
     */
    public void attack(NPC target);
}
