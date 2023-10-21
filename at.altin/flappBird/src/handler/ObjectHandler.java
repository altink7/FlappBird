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

        for (GameObject gameObject : list) {
            temp = gameObject;
            temp.render(g);
        }

    }

    public static void tick() {
        GameObject temp;

        for (GameObject gameObject : list) {
            temp = gameObject;
            temp.tick();
        }

    }
}