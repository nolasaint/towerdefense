package net.nolasaint.towerdefense.gui;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import net.nolasaint.towerdefense.TechTowerDefense;

/**
 * This class defines the JMenuBar for TechTowerDefense.
 *
 * @author Evan Bailey
 * @version 1.0
 */
public class GameMenu extends JMenuBar {

    private JMenu gameMenu, helpMenu;
    private JMenuItem pauseItem, resumeItem, resetItem, settingsItem, exitItem;

    private class PauseAction extends AbstractAction {
        public PauseAction() {
            super("Pause");

        }

        @Override
        public void actionPerformed(ActionEvent e) {
            TechTowerDefense.setPaused(true);
            pauseItem.setEnabled(false);
            resumeItem.setEnabled(true);

        }

    }

    private class ResumeAction extends AbstractAction {
        public ResumeAction() {
            super("Resume");

        }

        @Override
        public void actionPerformed(ActionEvent e) {
            TechTowerDefense.setPaused(false);
            pauseItem.setEnabled(true);
            resumeItem.setEnabled(false);

        }

    }

    private class ResetAction extends AbstractAction {
        public ResetAction() {
            super("Reset");

        }

        @Override
        public void actionPerformed(ActionEvent e) {
            TechTowerDefense.promptReset();

        }

    }

    private class ExitAction extends AbstractAction {
        public ExitAction() {
            super("Exit");

        }

        @Override
        public void actionPerformed(ActionEvent e) {
            TechTowerDefense.promptExit();

        }

    }

    /**
     * Constructs a new GameMenu.
     */
    public GameMenu() {
        buildGameMenu();

    }

    private void buildGameMenu() {
        gameMenu = new JMenu("Game");
        //helpMenu = new JMenu("Help");
        pauseItem = new JMenuItem();
        resumeItem = new JMenuItem();
        resetItem = new JMenuItem();
        //settingsItem = new JMenuItem("Settings");
        exitItem = new JMenuItem();

        pauseItem.setAction(new PauseAction());
        resumeItem.setAction(new ResumeAction());
        resetItem.setAction(new ResetAction());
        exitItem.setAction(new ExitAction());

        gameMenu.add(pauseItem);
        gameMenu.add(resumeItem);
        gameMenu.add(resetItem);
        //gameMenu.add(settingsItem);
        gameMenu.add(exitItem);

        add(gameMenu);
        //add(helpMenu);

    }

    /**
     * Updates all components and repaints.
     */
    public void update() {
        pauseItem.setEnabled(!TechTowerDefense.isPaused());
        resumeItem.setEnabled(TechTowerDefense.isPaused());

        repaint();

    }

}
