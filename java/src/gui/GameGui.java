package net.nolasaint.towerdefense.gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;

/**
 * GameGui serves as the GUI for Tech Tower Defense. It contains the option
 * panel, game canvas, and menu bar.
 *
 * @author Evan Bailey
 * @version 0.2
 */
public class GameGui extends JFrame {

    private GameMenu menuBar;
    private ControlPanel controlPanel;
    private GamePanel gamePanel;

    /**
     * Constructs a GUI for Tech Tower Defense using the supplied parameters.
     *
     * @param   width   - the starting width of this GUI's canvas
     * @param   height  - the starting height of this GUI's canvas
     */
    public GameGui(final int width, final int height) {
        super("Tech Tower Defense");

        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuBar = new GameMenu();
        controlPanel = new ControlPanel();
        gamePanel = new GamePanel(width, height);

        setJMenuBar(menuBar);
        add(controlPanel, BorderLayout.LINE_START);
        add(gamePanel, BorderLayout.CENTER);

        // Makes sure game canvas is specified size
        pack();

    }

    /**
     * Returns the current GameMenu.
     *
     * @return  the current GameMenu
     */
    public GameMenu getGameMenu() {
        return menuBar;

    }

    /**
     * Returns the current ControlPanel.
     *
     * @return  the current ControlPanel
     */
    public ControlPanel getControlPanel() {
        return controlPanel;

    }

    /**
     * Returns the current GamePanel.
     *
     * @return  the current GamePanel
     */
    public GamePanel getGamePanel() {
        return gamePanel;

    }

    /**
     * Refreshes (repaints) all Gui components.
     */
    public void update() {
        gamePanel.repaint();
        controlPanel.update();
        menuBar.update();

    }

}
