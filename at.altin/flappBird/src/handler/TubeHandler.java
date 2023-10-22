package handler;

import constants.GameConstants;
import objects.Tube;
import objects.helper.TubeType;
import play.Game;

import java.util.Random;

public class TubeHandler {
    private static final Random random = new Random();
    public static int area;
    public static int spacing;
    public static int minSize;
    public static int maxSize;
    public static int delay;
    public static int now;

    static {
        //bereich in dem die rohre spawnen
        area = GameConstants.HEIGHT - GameConstants.GROUND_SIZE;
        //abstand zwischen den rohren
        spacing = 170;
        //minimale groesse der rohre
        minSize = 40;
        //maximale groesse der rohre
        maxSize = area - spacing - minSize;
        //delay zwischen den rohren
        delay = 2;
    }

    public TubeHandler() {
    }

    public static void spawnTube() {
        int heightTop;
        heightTop = random.nextInt(maxSize) + 1;
        while (heightTop < minSize) {
            heightTop = random.nextInt(maxSize) + 1;
        }

        int heightBottom = area - spacing - heightTop;
        Tube tubeTop = new Tube(GameConstants.X_START_POSITION_TUBE, 0, GameConstants.TUBE_WIDTH, heightTop, TubeType.TOP);
        Tube tubeBottom = new Tube(GameConstants.X_START_POSITION_TUBE, heightTop + spacing, GameConstants.TUBE_WIDTH, heightBottom, TubeType.BOTTOM);
        ObjectHandler.addObject(tubeTop);
        ObjectHandler.addObject(tubeBottom);
    }

    public static void tick() {
        if (now < delay) {
            ++now;
        } else {
            spawnTube();
            now = 0;
        }

    }
}
