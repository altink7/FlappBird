package handler;

import objects.Tube;
import objects.helper.TubeType;

import java.util.Random;

public class TubeHandler {
    private static final Random random = new Random();
    public static int groundSize = 168;
    public static int area;
    public static int spacing;
    public static int minSize;
    public static int maxSize;
    public static int delay;
    public static int now;

    static {
        area = 768 - groundSize;
        spacing = 170;
        minSize = 40;
        maxSize = area - spacing - minSize;
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
        Tube tubeTop = new Tube(500, 0, 78, heightTop, TubeType.TOP); // Adjust the x-coordinate
        Tube tubeBottom = new Tube(500, heightTop + spacing, 78, heightBottom, TubeType.BOTTOM); // Adjust the x-coordinate
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
