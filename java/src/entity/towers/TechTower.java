package net.nolasaint.towerdefense.entity.towers;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import net.nolasaint.towerdefense.attacks.AreaAttack;
import net.nolasaint.towerdefense.attacks.Attack;

/**
 * This class represents a Tech Tower, having medium attack speed and range,
 * but area-of-effect damage.
 *
 * @author Evan Bailey
 * @version 1.1
 */
public class TechTower extends Tower {

    private static Attack attack = new AreaAttack(1, 65);

    /**
     * Constructs a new TechTower at the specified coordinates.
     *
     * @param   p   - this Tower's location
     */
    public TechTower(Point p) {
        super("Power Tower", 800, 11, attack, p, 1000);

    }

    @Override
    public void draw(Graphics g) {
        final int radius = getSize();

        g.setColor(new Color(176, 158, 127));
        g.fillRect((int) getPosition().getX() - radius,
                (int) getPosition().getY() - radius, radius * 2, radius * 2);

    }

}
