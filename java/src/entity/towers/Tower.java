package net.nolasaint.towerdefense.entity.towers;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import net.nolasaint.towerdefense.TechTowerDefense;
import net.nolasaint.towerdefense.attacks.Attack;
import net.nolasaint.towerdefense.entity.Entity;

/**
 * This class represents a Tower, destroyer of Monsters!
 *
 * @author Evan Bailey
 * @version 1.1
 */
public abstract class Tower extends Entity {

    private final Attack attack;
    private final int cost;

    private boolean focused = false;

    private class AttackListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!TechTowerDefense.isPaused()) {
                attack();

            }

        }

    }

    /**
     * Constructs a Tower with specified name, action interval, attack, and
     * position
     *
     * @param   name            - this Tower's name
     * @param   actionInterval  - this Tower's action interval (its attack
     *                            speed)
     * @param   size            - this Tower's size
     * @param   attack          - this Tower's attack
     * @param   position        - this Tower's position
     * @param   cost            - this Tower's cost
     */
    public Tower(String name, int actionInterval, int size, Attack attack,
            Point position, int cost) {
        super(name, actionInterval, size, position);

        this.attack = attack;
        this.cost = cost;

    }

    @Override
    public ActionListener getListener() {
        return new AttackListener();

    }

    /**
     * Returns the cost of this Tower.
     *
     * @return  the price of this Tower.
     */
    public int getCost() {
        return cost;

    }

    /**
     * Returns whether this Tower is currently in focus.
     *
     * @return  true if this Tower is in focus
     */
    public boolean isFoused() {
        return focused;

    }

    /**
     * Sets whether this Tower is currently in focus.
     *
     * @param   focused - whether this Tower is currently in focus
     */
    public void setFocused(boolean focused) {
        this.focused = focused;

    }

    /**
     * Returns a Tower instance based on the passed string.
     *
     * @param   towerName   - the name of the type of Tower desired
     * @return  an instance of the Tower with the specified name
     */
    public static Tower parseTower(String towerName) {
        if (towerName.contains("Basic Tower")) {
            return new BasicTower(null);

        } else if (towerName.contains("Advanced Tower")) {
            return new AdvancedTower(null);

        } else if (towerName.contains("Power Tower")) {
            return new PowerTower(null);

        } else if (towerName.contains("Speed Tower")) {
            return new SpeedTower(null);

        } else if (towerName.contains("Tech Tower")) {
            return new TechTower(null);

        } else {
            return null;

        }

    }

    /**
     * Performs this Tower's attack.
     * Subclasses may override this for various reasons, such as alternating
     * attacks, failed attacks, etc...
     */
    public void attack() {
        attack.perform(getPosition());

    }

    /**
     * Draws this Tower's attack range.
     *
     * @param   g   - the Graphics object with which to draw
     */
    public void drawRange(Graphics g) {
        if (focused) {
            g.setColor(Color.YELLOW);

        } else {
            g.setColor(new Color(238, 238, 238));

        }
        g.fillOval((int) (getPosition().getX() - attack.getRadius()),
                (int) (getPosition().getY() - attack.getRadius()),
                attack.getRadius() * 2, attack.getRadius() * 2);

    }

}
