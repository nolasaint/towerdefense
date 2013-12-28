package net.nolasaint.towerdefense.entity.monsters;

import java.awt.Color;
import java.awt.Graphics;

/**
 * This class defines a very tanky Monster in the game.
 * It has high health but low speed, making it a tough kill.
 *
 * @author Evan Bailey
 * @version 1.1
 */
public final class StrongMonster extends Monster {

    /**
     * Constructs a new StrongMonster.
     */
    public StrongMonster() {
        super("Strong Monster", 18, 15, 10);

    }

    @Override
    public void draw(Graphics g) {
        final int radius = getSize();

        g.setColor(Color.DARK_GRAY);
        g.fillOval((int) getPosition().getX() - radius,
                (int) getPosition().getY() - radius, radius * 2, radius * 2);
        drawHealthBar(g);

    }

}
