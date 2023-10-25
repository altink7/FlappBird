package handler;

import play.Game;

public class LevelHandler {

    /**
     * if (Game.SCORE % 10 == 0)  then increase level, if level is changed call method changeLevelMechanic()
     */
    public static void handleLevelMechanic() {
        if (Game.SCORE % 2 == 0) {
            Game.LEVEL++;
            changeLevelMechanic();
        }
    }

    /**
     * change level mechanic
     */
    public static void changeLevelMechanic() {

    }


    public static void tick() {
        handleLevelMechanic();
    }

}
