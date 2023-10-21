package handler;

import display.GameObject;

import java.awt.*;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ObjectHandler {

    public static List<GameObject> list = Collections.synchronizedList(new LinkedList<>());

    public ObjectHandler() {
    }

    public static void addObject(GameObject o) {
        list.add(o);
    }

    public static void removeObject(GameObject o) {
        list.remove(o);
    }

    public static void render(Graphics g) {
        GameObject temp;

        int i = 0;
        while (i < list.size()) {
            temp = list.get(i);
            temp.render(g);
            ++i;
        }

    }

    public static void tick() {
        GameObject temp;

        int i = 0;
        while (i < list.size()) {
            temp = list.get(i);
            temp.tick();
            ++i;
        }

    }
}