package net.nolasaint.towerdefense.attacks;

import java.awt.Point;
import java.awt.geom.Ellipse2D;

import net.nolasaint.towerdefense.TechTowerDefense;
import net.nolasaint.towerdefense.entity.monsters.Monster;

/**
 * This class defines a Basic Attack, which attacks only one Monster at a time.
 *
 * @author Evan Bailey
 * @version 2.0
 */
public class BasicAttack extends Attack {

    private final int damage;
    private final int radius;

    /**
     * Constructs a new BasicAttack.
     */
    public BasicAttack(int damage, int radius) {
        super(radius);

        this.damage = damage;
        this.radius = radius;

    }

    @Override
    public void perform(Point center) {
        boolean hasAttacked = false;
        Ellipse2D.Double range = new Ellipse2D.Double(center.getX() - radius,
                center.getY() - radius, radius * 2, radius * 2);
        Monster[] monstersArray =
                new Monster[TechTowerDefense.getMonsters().size()];
        monstersArray = TechTowerDefense.getMonsters().toArray(monstersArray);

        // Attacks first monster in range (first being furthest on path)
        for (int i = monstersArray.length - 1; i >= 0; i--) {
            if (range.contains(monstersArray[i].getPosition())
                    && !hasAttacked) {
                monstersArray[i].damage(damage);
                hasAttacked = true;

            }

        }

    }

}
