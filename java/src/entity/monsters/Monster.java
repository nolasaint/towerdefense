package net.nolasaint.towerdefense.entity.monsters;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import net.nolasaint.towerdefense.TechTowerDefense;
import net.nolasaint.towerdefense.entity.Entity;

/**
 * This class represents a Monster, a type of entity with health and whose
 * action is movement.
 *
 * @author Evan Bailey
 * @version 1.1
 */
public abstract class Monster extends Entity {

    private final int maxHealth;

    private int health;
    private boolean atEndOfPath = false;
    private boolean invincible = true;

    private class MovementListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!TechTowerDefense.isPaused()) {
                invincible = false;
                move();

            }

        }

    }

    /**
     * Constructs a Monster with the specified name, action interval, health,
     * and location.
     *
     * @param   name            - this Monster's name
     * @param   actionInterval  - this Monster's action interval (its movement
     *                            speed
     * @param   size            - this Monster's size
     * @param   health          - this Monster's health
     */
    public Monster(String name, int actionInterval, int size, int health) {
        super(name, actionInterval, size,
                TechTowerDefense.getPath().getEntryPoint());

        maxHealth = health;
        this.health = health;

    }

    private void setHealth(int health) {
        if (health > this.health) {
            health = maxHealth;

        } else if (health < 0) {
            health = 0;

        }

        this.health = health;

    }

    @Override
    public ActionListener getListener() {
        return new MovementListener();

    }

    /**
     * Returns the current health of this Monster.
     *
     * @return  this Monster's health.
     */
    public final int getHealth() {
        return health;

    }

    /**
     * Returns whether or not this Monster has completed the Path.
     *
     * @return  true if this Monster has completed the Path, else false
     */
    public final boolean isAtEndOfPath() {
        return atEndOfPath;

    }

    /**
     * Does the specified amount of damage to this Monster.
     *
     * @param   damage  - the amount of damage to be dealt to this Monster
     */
    public void damage(int damage) {
        if (!invincible) {
            setHealth(health - damage);

        }

    }

    /**
     * Heals this Monster by the specified amount.
     *
     * @param   amount  - the amount of health to grant this Monster
     */
    public void heal(int amount) {
        setHealth(health + amount);

    }

    /**
     * Moves this Monster along the path.
     * Subclasses may override this for various reasons, such as paralysis,
     * movement boosts, etc...
     */
    public void move() {
        Point nextPoint = TechTowerDefense.getPointAfter(getPosition());

        if (null == nextPoint) {
            atEndOfPath = true;

        } else {
            setPosition(nextPoint);

        }

    }

    /**
     * Draws this Monster's health bar.
     *
     * @param   g       - the Graphics object with which to draw
     */
    public void drawHealthBar(Graphics g) {
        final int radius = getSize();
        final int barMaxWidth = radius * 2;
        int barHealth;

        if (health == maxHealth) {
            barHealth = barMaxWidth;

        } else {
            barHealth = (int) (((double) health / (double) maxHealth)
                    * barMaxWidth);

        }

        g.setColor(Color.GRAY);
        g.fillRect(((int) getPosition().getX()) - radius,
                ((int) getPosition().getY()) - (radius * 2), barMaxWidth, 5);
        g.setColor(Color.GREEN);
        g.fillRect(((int) getPosition().getX()) - radius,
                ((int) getPosition().y) - (radius * 2), barHealth, 5);

    }

}
