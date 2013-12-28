package net.nolasaint.towerdefense.attacks;

import java.awt.Point;
import java.awt.geom.Ellipse2D;

import net.nolasaint.towerdefense.TechTowerDefense;
import net.nolasaint.towerdefense.entity.monsters.Monster;

/**
 * This class defines an Area Attack, which attacks all Monsters in range.
 *
 * @author Evan Bailey
 * @version 1.0
 */
public class AreaAttack extends Attack {

    private final int damage;
    private final int radius;

    /**
     * Constructs a new AreaAttack.
     */
    public AreaAttack(int damage, int radius) {
        super(radius);

        this.damage = damage;
        this.radius = radius;

    }

    @Override
    public void perform(Point center) {
        Ellipse2D.Double range = new Ellipse2D.Double(center.getX() - radius,
                center.getY() - radius, radius * 2, radius * 2);
        Monster[] monstersArray =
                new Monster[TechTowerDefense.getMonsters().size()];
        monstersArray = TechTowerDefense.getMonsters().toArray(monstersArray);

        for (int i = monstersArray.length - 1; i >= 0; i--) {
            if (range.contains(monstersArray[i].getPosition())) {
                monstersArray[i].damage(damage);

            }

        }

    }

}
