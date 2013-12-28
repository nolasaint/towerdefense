package net.nolasaint.towerdefense.entity.monsters;

import java.awt.Color;
import java.awt.Graphics;

/**
 * This class defines a slightly challenging Monster in the game.
 * It has medium health and low speed, making it a rather easy target.
 *
 * @author Evan Bailey
 * @version 1.1
 */
public final class AdvancedMonster extends Monster {

    /**
     * Constructs a new AdvancedMonster.
     */
    public AdvancedMonster() {
        super("Advanced Monster", 15, 13, 5);

    }

    @Override
    public void draw(Graphics g) {
        final int radius = getSize();

        g.setColor(Color.CYAN);
        g.fillOval((int) getPosition().getX() - radius,
                (int) getPosition().getY() - radius, radius * 2, radius * 2);
        drawHealthBar(g);

    }

}
