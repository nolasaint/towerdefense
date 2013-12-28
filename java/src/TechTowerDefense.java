package net.nolasaint.towerdefense;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JOptionPane;
import javax.swing.Timer;

import net.nolasaint.towerdefense.entity.Entity;
import net.nolasaint.towerdefense.entity.monsters.AdvancedMonster;
import net.nolasaint.towerdefense.entity.monsters.BasicMonster;
import net.nolasaint.towerdefense.entity.monsters.Monster;
import net.nolasaint.towerdefense.entity.monsters.ScalableMonster;
import net.nolasaint.towerdefense.entity.monsters.SpeedMonster;
import net.nolasaint.towerdefense.entity.monsters.StrongMonster;
import net.nolasaint.towerdefense.entity.towers.Tower;
import net.nolasaint.towerdefense.exceptions.NotOnPathException;
import net.nolasaint.towerdefense.gui.GameGui;

/**
 * This class serves as the Driver for the project. It instantiates a GUI and
 * runs the game's engine.
 *
 * @author Evan Bailey
 * @version 1.2
 */
public class TechTowerDefense {

    private static final int BASE_MONEY = 100;
    private static final int MAX_HEALTH = 35;
    private static final int MAX_MONSTERS = 16;
    
    private static boolean gameOver = false;
    private static boolean paused = false;
    private static Path path;
    private static int health, money, score, waveCount;
    private static GameGui gameGui;
    private static HashMap<Monster, Timer> monsters = 
            new HashMap<Monster, Timer>();
    private static HashMap<Tower, Timer> towers =
            new HashMap<Tower, Timer>();
    private static Timer tickTimer;

    private static class TickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            TechTowerDefense.tick();

        }

    }

    private static <T extends Number> T assureInRange(T item, T min, T max) {
        if (null != min && item.doubleValue() < min.doubleValue()) {
            return min;

        } else if (null != max && item.doubleValue() > max.doubleValue()) {
            return max;

        } else {
            return item;

        }

    }

    private static Dimension parseArgs(String[] args) {
        Dimension dim = new Dimension(600, 600);

        if (args.length != 2) {
            return dim;

        } else {
            try {
                dim.setSize(new Integer(args[0]), new Integer(args[1]));

                return dim;

            } catch (Exception ex) {
                System.out.println("Invalid arguments passed, supplying "
                        + "default parameters");

                return dim;

            }

        }

    }

    private static Timer buildTimer(Entity e) {
        ActionListener listener = e.getListener();
        int interval = e.getActionInterval();

        return new Timer(interval, listener);

    }

    private static void clearTimers() {
        for (Timer t : monsters.values()) {
            t.stop();

        }

        for (Timer t : towers.values()) {
            t.stop();

        }

    }

    private static void reset() {
        clearTimers();
        path = new Path(gameGui.getGamePanel().getSize());
        monsters = new HashMap<Monster, Timer>();
        towers = new HashMap<Tower, Timer>();

        health = MAX_HEALTH;
        money = BASE_MONEY;
        score = 0;
        waveCount = 0;
        gameOver = false;

        setPaused(false);

    }

    private static void checkDead() {
        HashSet<Monster> dead = new HashSet<Monster>();

        for (Monster m : monsters.keySet()) {
            if (m.getHealth() == 0) {
                monsters.get(m).stop();

                if (m instanceof BasicMonster) {
                    money += 10;

                } else if (m instanceof AdvancedMonster) {
                    money += 20;

                } else if (m instanceof SpeedMonster) {
                    money += 25;

                } else if (m instanceof StrongMonster) {
                    money += 30;

                } else if (m instanceof ScalableMonster) {
                    money += 35;

                }

                score++;
                dead.add(m);

            }

        }

        for (Monster m : dead) {
            monsters.remove(m);

        }

    }

    private static void checkComplete() {
        HashSet<Monster> completed = new HashSet<Monster>();

        for (Monster m : monsters.keySet()) {
            if (m.isAtEndOfPath()) {
                monsters.get(m).stop();
                completed.add(m);

                health--;

            }

        }

        for (Monster m : completed) {
            monsters.remove(m);

        }
        
    }

    private static void checkGameOver() {
        int choice;

        if (health == 0) {
            setPaused(true);
            choice = JOptionPane.showConfirmDialog(gameGui,
                    "Game over, play again?", "Game Over!",
                    JOptionPane.YES_NO_OPTION);

            if (JOptionPane.YES_OPTION == choice) {
                reset();
                setPaused(false);

            } else if (JOptionPane.NO_OPTION == choice) {
                gameOver = true;

            }

        }

    }

    private static void startMonsterTimers() {
        for (Timer t : monsters.values()) {
            t.start();

        }

    }

    /**
     * Returns the current Path.
     *
     * @return  the current Path
     */
    public static Path getPath() {
        return path;

    }

    /**
     * Returns the next Point along the current Path with respect to the
     * supplied Point.
     *
     * @param   p   - the current Point along the Path
     * @return  the next Point along the Path
     */
    public static Point getPointAfter(Point p) {
        try {
            return path.getNextPoint(p);

        } catch (NotOnPathException ex) {
            return null;

        }

    }

    /**
     * Returns a Set containing all currently living Monsters (not guaranteed
     * to be alive, but very likely).
     *
     * @return  a Set containing currently active Monsters
     */
    public static Set<Monster> getMonsters() {
        return monsters.keySet();

    }

    /**
     * Adds a new Monster to the list of currently active Monsters, waiting
     * a specified amount of time before allowing the Monster to move.
     *
     * @param   m           - the Monster to be added
     * @param   waitTime    - the initial delay for the Monster
     * @param   start       - whether to start the Monster's Timer immediately
     */
    public static void addMonster(Monster m, int waitTime, boolean start) {
        monsters.put(m, buildTimer(m));
        monsters.get(m).setInitialDelay(waitTime);
        if (start) {
            monsters.get(m).start();

        }

    }

    /**
     * Returns a Set containing all currently placed Towers.
     *
     * @return  a Set containing active Towers
     */
    public static Set<Tower> getTowers() {
        return towers.keySet();

    }

    /**
     * Adds a Tower to the list of active Towers.
     *
     * @param   t   - the Tower to be added
     */
    public static void addTower(Tower t) {
        towers.put(t, buildTimer(t));
        towers.get(t).start();

    }

    /**
     * Gets the player's current health.
     *
     * @return  the player's health
     */
    public static int getHealth() {
        return health;

    }

    /**
     * Sets the player's current health, allowing no less than 0 and no more
     * than MAX_HEALTH.
     *
     * @param   health  - the desired health for the player
     */
    public static void setHealth(int health) {
        TechTowerDefense.health = assureInRange(health, 0, MAX_HEALTH);

    }

    /**
     * Gets the player's current balance.
     *
     * @return  the player's balance
     */
    public static int getMoney() {
        return money;

    }

    /**
     * Sets the player's current balance, allowing no less than 0.
     *
     * @param   money   - the desired balance for the player
     */
    public static void setMoney(int money) {
        TechTowerDefense.money = assureInRange(money, 0, null);

    }

    /**
     * Gets the player's current score.
     *
     * @return  the player's score
     */
    public static int getScore() {
        return score;

    }

    /**
     * Returns whether or not the game is currently paused.
     *
     * @return  true if the game is currently paused, else false
     */
    public static boolean isPaused() {
        return paused;

    }

    /**
     * Sets paused status.
     *
     * @param   paused  - whether to pause the game or resume it.
     */
    public static void setPaused(boolean paused) {
        if (!gameOver) {
            TechTowerDefense.paused = paused;

        }

    }

    /**
     * Sets the player's score, allowing no less than 0.
     *
     * @param   score   - the desired score for the player
     */
    public static void setScore(int score) {
        TechTowerDefense.score = assureInRange(score, 0, null);

    }

    /**
     * Spawns a new wave of Monsters.
     */
    public static void spawnWave() {
        int initialMonsterCount = 2 * (waveCount + 1);
        int trueCount = 0;

        if (monsters.isEmpty() && !paused) {
            while (trueCount < MAX_MONSTERS) {
                if (initialMonsterCount > 40) {
                    addMonster(new ScalableMonster(15, (int) (waveCount / 5)),
                            1000 * trueCount, false);

                } else if (initialMonsterCount > 30
                        && initialMonsterCount <= 40) {
                    addMonster(new StrongMonster(), 1000 * trueCount, false);

                } else if (initialMonsterCount > 20
                        && initialMonsterCount <= 30) {
                    addMonster(new SpeedMonster(), 800 * trueCount, false);

                } else if (initialMonsterCount > 10
                        && initialMonsterCount <= 20) {
                    addMonster(new AdvancedMonster(), 800 * trueCount, false);

                } else if (initialMonsterCount > 0
                        && initialMonsterCount <= 10) {
                    addMonster(new BasicMonster(), 800 * trueCount, false);

                }

                trueCount++;
                initialMonsterCount--;

            }

            waveCount++;

        }

        startMonsterTimers();

    }

    /**
     * Prompts if the user wants to exit the game.
     */
    public static void promptExit() {
        int result;

        setPaused(true);
        result = JOptionPane.showConfirmDialog(gameGui,
                "Really quit Tech Tower Defense?", "Really quit?",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (JOptionPane.YES_OPTION == result) {
            System.exit(0);

        } else {
            setPaused(false);

        }

    }

    /**
     * Displays a prompt asking the player if they really want to reset the
     * game.
     */
    public static void promptReset() {
        int result;

        setPaused(true);
        result = JOptionPane.showConfirmDialog(gameGui,
                "Are you sure you want to restart?", "Really restart?",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (JOptionPane.YES_OPTION == result) {
            reset();

        } else {
            setPaused(false);

        }

    }

    /*
     * Horribly-named method which behaves similarly to a threads-based game's
     * tick engine. Updates game components.
     */
    private static void tick() {
        checkDead();
        checkComplete();
        checkGameOver();
        gameGui.update();

    }

    /**
     * This method is the entry-point for the program. Equivalent to a main()
     * method.
     *
     * @param   args    - the command-line arguments
     */
    public static void run(String[] args) {
        Dimension dim = parseArgs(args);
        tickTimer = new Timer(15, new TickListener());
        gameGui = new GameGui((int) dim.getWidth(), (int) dim.getHeight());

        reset();
        gameGui.setLocationRelativeTo(null);
        gameGui.setVisible(true);
        paused = false;

        tickTimer.start();

    }

}
