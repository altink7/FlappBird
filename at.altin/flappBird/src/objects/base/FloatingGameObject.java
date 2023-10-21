package objects.base;

import display.GameObject;

public abstract class FloatingGameObject extends GameObject {
    public static final float GRAVITY = 0.3F;
    public static final float MAX_SPEED = 5.0F;
    public static final float HORIZONTAL_VELOCITY = 3.0F;

    public FloatingGameObject(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public abstract void tick();
}
