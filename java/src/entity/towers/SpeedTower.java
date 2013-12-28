package net.nolasaint.towerdefense.entity.towers;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import net.nolasaint.towerdefense.attacks.Attack;
import net.nolasaint.towerdefense.attacks.BasicAttack;

/**
 * This class represents a Speed Tower, having high attack speed, but low
 * power and range.
 *
 * @author Evan Bailey
 * @version 1.1
 */
public class SpeedTower extends Tower {

    private static Attack attack = new BasicAttack(1, 50);

    /**
     * Constructs a new SpeedTower at the specified coordinates.
     *
     * @param   p   - this Tower's location
     */
    public SpeedTower(Point p) {
        super("Speed Tower", 600, 9, attack, p, 300);

    }

    @Override
    public void draw(Graphics g) {
        final int radius = getSize();

        g.setColor(Color.RED); //magenta
        g.fillRect((int) getPosition().getX() - radius,
                (int) getPosition().getY() - radius, radius * 2, radius * 2);

    }

}
