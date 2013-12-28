package net.nolasaint.towerdefense.entity.towers;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import net.nolasaint.towerdefense.attacks.Attack;
import net.nolasaint.towerdefense.attacks.BasicAttack;

/**
 * This class represents a Basic Tower, having low attack speed, range, and
 * power.
 *
 * @author Evan Bailey
 * @version 1.0
 */
public class BasicTower extends Tower {

    private static Attack attack = new BasicAttack(1, 50);

    /**
     * Constructs a new BasicTower at the specified coordinates.
     *
     * @param   p   - this Tower's location
     */
    public BasicTower(Point p) {
        super("Basic Tower", 1300, 8, attack, p, 50);

    }

    @Override
    public void draw(Graphics g) {
        final int radius = getSize();

        g.setColor(Color.ORANGE); //pink
        g.fillRect((int) getPosition().getX() - radius,
                (int) getPosition().getY() - radius, radius * 2, radius * 2);

    }

}
