package net.nolasaint.towerdefense.gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

import net.nolasaint.towerdefense.TechTowerDefense;
import net.nolasaint.towerdefense.entity.towers.AdvancedTower;
import net.nolasaint.towerdefense.entity.towers.BasicTower;
import net.nolasaint.towerdefense.entity.towers.PowerTower;
import net.nolasaint.towerdefense.entity.towers.SpeedTower;
import net.nolasaint.towerdefense.entity.towers.TechTower;
import net.nolasaint.towerdefense.entity.towers.Tower;

/**
 * This class defines the control panel for TechTowerDefense.
 *
 * @author Evan Bailey
 * @version 1.0
 */
public class ControlPanel extends JPanel {

    private JButton buildTowerButton, nextWaveButton, sellTowerButton;
    private JLabel healthLabel, moneyLabel, scoreLabel, focusedTowerLabel,
            focusedTowerSpeedLabel, focusedTowerDamageLabel,
            focusedTowerRangeLabel;
    private JList<String> towerList;

    private class BuildAction extends AbstractAction {
        public BuildAction() {
            super("Buy Tower");

        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int money = TechTowerDefense.getMoney();
            String selection = towerList.getSelectedValue();
            Tower selectedTower = Tower.parseTower(selection);

            if (selectedTower.getCost() <= money
                    && null == GamePanel.getSelectedTower()) {
                TechTowerDefense.setMoney(money - selectedTower.getCost());
                GamePanel.setSelectedTower(selectedTower);

            }

        }

    }

    private class WaveAction extends AbstractAction {
        public WaveAction() {
            super("Launch Next Wave");

        }

        @Override
        public void actionPerformed(ActionEvent e) {
            TechTowerDefense.spawnWave();

        }

    }

    private class SellAction extends AbstractAction {
        public SellAction() {
            super("Sell Selected Tower");

        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO sell focused Tower

        }

    }

    /**
     * Constructs a new ControlPanel.
     */
    public ControlPanel() {
        buildControlPanel();

    }

    private JList<String> buildTowerList() {
        BasicTower basicTower = new BasicTower(null);
        AdvancedTower advancedTower = new AdvancedTower(null);
        PowerTower powerTower = new PowerTower(null);
        SpeedTower speedTower = new SpeedTower(null);
        TechTower techTower = new TechTower(null);

        String[] towerOptions = {"[$" + basicTower.getCost() + "] Basic Tower",
                "[$" + advancedTower.getCost() + "] Advanced Tower",
                "[$" + speedTower.getCost() + "] Speed Tower",
                "[$" + powerTower.getCost() + "] Power Tower",
                "[$" + techTower.getCost() + "] Tech Tower"};

        return new JList<String>(towerOptions);

    }

    private Component buildPadding(int width, int height) {
        return Box.createRigidArea(new Dimension(width, height));

    }

    private void buildControlPanel() {
        healthLabel = new JLabel("Health: "
                + TechTowerDefense.getHealth());
        moneyLabel = new JLabel("Money: $"
                + TechTowerDefense.getMoney());
        scoreLabel = new JLabel("Score: 0");
        towerList = buildTowerList();
        buildTowerButton = new JButton("Buy Tower");
        nextWaveButton = new JButton("Launch Next Wave");

        buildTowerButton.setAction(new BuildAction());
        nextWaveButton.setAction(new WaveAction());

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        healthLabel.setAlignmentX(LEFT_ALIGNMENT);
        add(healthLabel);
        add(buildPadding(0, 10));

        moneyLabel.setAlignmentX(LEFT_ALIGNMENT);
        add(moneyLabel);
        add(buildPadding(0, 10));

        scoreLabel.setAlignmentX(LEFT_ALIGNMENT);
        add(scoreLabel);
        add(buildPadding(0, 50));

        towerList.setAlignmentX(LEFT_ALIGNMENT);
        add(towerList);
        add(buildPadding(0, 20));

        buildTowerButton.setAlignmentX(LEFT_ALIGNMENT);
        add(buildTowerButton);
        add(buildPadding(0, 50));

        nextWaveButton.setAlignmentX(LEFT_ALIGNMENT);
        add(nextWaveButton);

    }

    /**
     * Updates all components.
     */
    public void update() {
        healthLabel.setText("Health: " + TechTowerDefense.getHealth());
        moneyLabel.setText("Money: $" + TechTowerDefense.getMoney());
        scoreLabel.setText("Score: " + TechTowerDefense.getScore());

        repaint();

    }

}
