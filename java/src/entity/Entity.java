package net.nolasaint.towerdefense.entity;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionListener;

/**
 * This class represents an entity (A tower or monster, for instance).
 *
 * @author Evan Bailey
 * @version 1.1
 */
public abstract class Entity {

    private final int actionInterval, size;
    private final String name;

    private Point position;

    /**
     * Constructs an Entity with the specified name and action interval.
     *
     * @param   name            - this Entity's name
     * @param   actionInterval  - this Entity's action interval
     * @param   size            - this Entity's size
     * @param   position        - this Entity's position
     */
    public Entity(String name, int actionInterval, int size, Point position) {
        this.actionInterval = actionInterval;
        this.size = size;
        this.name = name;
        this.position = position;

    }

    /**
     * Returns an instance of this Entity's action listener in order for the
     * driver to alert this Entity to act.
     *
     * @return  an instance of this Entity's action listener
     */
    public abstract ActionListener getListener();

    /**
     * Draws this Entity with the provided Graphics object.
     *
     * @param   g   - the Graphics object with which to draw this Entity
     */
    public abstract void draw(Graphics g);

    /**
     * Returns how often this Entity's ActionListener should be activated.
     * In other words, it returns how often this Entity should do something.
     *
     * @return  this Entity's action interval
     */
    public final int getActionInterval() {
        return actionInterval;

    }

    /**
     * Returns the size of this Entity.
     *
     * @return  this Entity's size
     */
    public final int getSize() {
        return size;

    }

    /**
     * Returns this the name of this type of Entity.
     *
     * @return  this name of this type of Entity
     */
    public final String getName() {
        return name;

    }

    /**
     * Returns a Point representing the location of this Entity.
     *
     * @return  a point representing the location of this Entity
     */
    public final Point getPosition() {
        return position;

    }

    /**
     * Sets this Entity's location.
     *
     * @param   position    - the position for this Entity
     */
    public final void setPosition(Point position) {
        this.position = position;

    }

}
