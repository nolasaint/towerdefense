package net.nolasaint.towerdefense.entity.monsters;

import java.awt.Color;
import java.awt.Graphics;

/**
 * This class defines a Monster which scales based on the game's wave count.
 *
 * @author Evan Bailey
 * @version 1.1
 */
public final class ScalableMonster extends Monster {

    /**
     * Constructs a new ScalableMonster.
     *
     * @param   speed   - this Monster's speed
     * @param   health  - this Monster's health
     */
    public ScalableMonster(int speed, int health) {
        super("Scalable Monster", speed, 13, health);

    }

    @Override
    public void draw(Graphics g) {
        final int radius = getSize();

        g.setColor(new Color(172, 108, 255));
        g.fillOval((int) getPosition().getX() - radius,
                (int) getPosition().getY() - radius, radius * 2, radius * 2);
        drawHealthBar(g);

    }

}

