package net.nolasaint.towerdefense.entity.monsters;

import java.awt.Color;
import java.awt.Graphics;

/**
 * This class defines a very fast-moving Monster in the game.
 * It has medium health and high speed speed, making it an elusive but
 * vulnerable target.
 *
 * @author Evan Bailey
 * @version 1.1
 */
public final class SpeedMonster extends Monster {

    /**
     * Constructs a new SpeedMonster.
     */
    public SpeedMonster() {
        super("Speed Monster", 8, 11, 4);

    }

    @Override
    public void draw(Graphics g) {
        final int radius = getSize();

        g.setColor(Color.RED);
        g.fillOval((int) getPosition().getX() - radius,
                (int) getPosition().getY() - radius, radius * 2, radius * 2);
        drawHealthBar(g);

    }

}
