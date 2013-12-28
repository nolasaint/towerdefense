package net.nolasaint.towerdefense.entity.monsters;

import java.awt.Color;
import java.awt.Graphics;

/**
 * This class defines the most basic Monster in the game.
 * It has low health and low speed, making it an easy-to-kill target.
 *
 * @author Evan Bailey
 * @version 1.1
 */
public final class BasicMonster extends Monster {

    /**
     * Constructs a new BasicMonster.
     */
    public BasicMonster() {
        super("Basic Monster", 25, 10, 1);

    }

    @Override
    public void draw(Graphics g) {
        final int radius = getSize();

        g.setColor(Color.ORANGE);
        g.fillOval((int) getPosition().getX() - radius,
                (int) getPosition().getY() - radius, radius * 2, radius * 2);
        drawHealthBar(g);

    }

}
