package objects.base;

import display.GameObject;

public abstract class FloatingGameObject extends GameObject {

    public FloatingGameObject(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public abstract void tick();
}
