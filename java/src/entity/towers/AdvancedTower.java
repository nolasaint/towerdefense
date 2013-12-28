package net.nolasaint.towerdefense.entity.towers;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import net.nolasaint.towerdefense.attacks.Attack;
import net.nolasaint.towerdefense.attacks.BasicAttack;

/**
 * This class represents an Advanced Tower, having medium attack speed and
 * range but low power.
 *
 * @author Evan Bailey
 * @version 1.1
 */
public class AdvancedTower extends Tower {

    private static Attack attack = new BasicAttack(1, 75);

    /**
     * Constructs a new AdvancedTower at the specified coordinates.
     *
     * @param   p   - this Tower's location
     */
    public AdvancedTower(Point p) {
        super("Advanced Tower", 800, 10, attack, p, 150);

    }

    @Override
    public void draw(Graphics g) {
        final int radius = getSize();

        g.setColor(Color.CYAN); //darkgray
        g.fillRect((int) getPosition().getX() - radius,
                (int) getPosition().getY() - radius, radius * 2, radius * 2);

    }

}
