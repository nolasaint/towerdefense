package net.nolasaint.towerdefense.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Set;

import javax.swing.JPanel;

import net.nolasaint.towerdefense.TechTowerDefense;
import net.nolasaint.towerdefense.entity.Entity;
import net.nolasaint.towerdefense.entity.towers.Tower;

/**
 * This class defines GamePanel, the JPanel on which the action of the game
 * takes place (read: where everything gets drawn).
 *
 * @author Evan Bailey
 * @version 1.0
 */
public class GamePanel extends JPanel {

    private static boolean mouseInFrame;
    private static Tower selectedTower = null;

    private class ClickListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            if (null != selectedTower && null != selectedTower.getPosition()) {
                 if (mouseInFrame) {
                     TechTowerDefense.addTower(selectedTower);
                     selectedTower = null;

                 }

            } else if (mouseInFrame && e.getButton() == MouseEvent.BUTTON2) {
                // TODO check if tower exists @ position

            }

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            mouseInFrame = true;

        }

        @Override
        public void mouseExited(MouseEvent e) {
            mouseInFrame = false;

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

    }

    private class MotionListener implements MouseMotionListener {

        @Override
        public void mouseDragged(MouseEvent e) {

        }

        @Override
        public void mouseMoved(MouseEvent e) {
            if (null != selectedTower && mouseInFrame) {
                selectedTower.setPosition(e.getPoint());

            }

        }

    }

    /**
     * Constructs a new GamePanel with specified dimensions.
     *
     * @param   width   - this GamePanel's width
     * @param   height  - this GamePanel's height
     */
    public GamePanel(int width, int height) {
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(width, height));
        addMouseListener(new ClickListener());
        addMouseMotionListener(new MotionListener());

    }

    private void paintRanges(Graphics g) {
        for (Tower t : TechTowerDefense.getTowers()) {
            t.drawRange(g);

        }

    }

    private void paintEntities(Graphics g, Set<? extends Entity> entities) {
        for (Entity e : entities) {
            e.draw(g);

        }

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        paintRanges(g);
        if (null != selectedTower && null != selectedTower.getPosition()) {
            selectedTower.drawRange(g);

        }

        paintEntities(g, TechTowerDefense.getTowers());
        if (null != selectedTower && null != selectedTower.getPosition()) {
            selectedTower.draw(g);

        }

        TechTowerDefense.getPath().draw(g);
        paintEntities(g, TechTowerDefense.getMonsters());

    }

    /**
     * Returns the type of Tower currently selected.
     *
     * @return  the type of Tower currently selected, or null if none
     */
    public static Tower getSelectedTower() {
        return selectedTower;

    }

    /**
     * Sets the currently selected Tower.
     *
     * @param   t   - the type of Tower currently selected
     */
    public static void setSelectedTower(Tower t) {
        selectedTower = t;

    }

}
