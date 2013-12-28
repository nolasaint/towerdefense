package net.nolasaint.towerdefense.attacks;

import java.awt.Point;

/**
 * This class represents an Attack which Towers perform.
 *
 * @author Evan Bailey
 * @version 1.0
 */
public abstract class Attack {

    private int radius;

    /**
     * Constructs a new Attack with the specified radius.
     *
     * @param   radius  - this Attack's radius
     */
    public Attack(int radius) {
        this.radius = radius;

    }

    /**
     * Performs this Attack.
     *
     * @param   center  - the center-point of this Attack 
     */
    public abstract void perform(Point center);

    /**
     * Returns the radius of this Attack.
     *
     * @return  this Attack's radius
     */
    public int getRadius() {
        return radius;

    }

}
