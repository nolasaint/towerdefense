package net.nolasaint.towerdefense.entity.towers;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import net.nolasaint.towerdefense.attacks.Attack;
import net.nolasaint.towerdefense.attacks.BasicAttack;

/**
 * This class represents a Power Tower, having low attack speed and range, but
 * high damage.
 *
 * @author Evan Bailey
 * @version 1.1
 */
public class PowerTower extends Tower {

    private static Attack attack = new BasicAttack(3, 50);

    /**
     * Constructs a new PowerTower at the specified coordinates.
     *
     * @param   p   - this Tower's location
     */
    public PowerTower(Point p) {
        super("Power Tower", 1000, 12, attack, p, 450);

    }

    @Override
    public void draw(Graphics g) {
        final int radius = getSize();

        g.setColor(Color.BLACK); //white
        g.fillRect((int) getPosition().getX() - radius,
                (int) getPosition().getY() - radius, radius * 2, radius * 2);

    }

}
