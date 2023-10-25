package handler;

import constants.GameConstants;
import constants.objects.BirdConstants;
import objects.Bird;
import play.Game;

import java.util.concurrent.Semaphore;
import java.util.logging.Logger;

public class LevelHandler {
    private static final Logger logger = Logger.getLogger(LevelHandler.class.getName());

    /** The amount of points the player needs to get to the next level */
    public static final int SCORE_LEVEL_POINT = 10;

    private static boolean levelChanged = false;
    private static final Semaphore levelSemaphore = new Semaphore(1);

    /**
     * if (Game.SCORE % 2 == 0) then increase level, if level is changed, call method changeLevelMechanic()
     */
    public static void handleLevelMechanic() {
        if (Game.SCORE != 0 && Game.SCORE % SCORE_LEVEL_POINT == 0 && !levelChanged) {
            try {
                levelSemaphore.acquire();
                Game.LEVEL++;
                levelChanged = true;
                changeLevelMechanic();
                levelSemaphore.release();
            } catch (InterruptedException e) {
                logger.log(java.util.logging.Level.SEVERE, "Exception: ", e);
            }
        } else if (Game.SCORE % SCORE_LEVEL_POINT != 0) {
            levelChanged = false;
        }
    }

    /**
     * change level mechanic, do only once per level
     */
    public static void changeLevelMechanic() {
        if (Game.LEVEL % SCORE_LEVEL_POINT == 0) {
            TubeHandler.spacing -= 50;
        } else if (Game.LEVEL % 3 == 0) {
            BirdConstants.MAX_SPEED += 2.5F;
        }
    }

    public static void tick() {
        handleLevelMechanic();
    }
}
