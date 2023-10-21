package objects.base;

import display.GameObject;

public abstract class FloatingGameObject extends GameObject {
    /** Gravitaet wie der Bird nach unten faellt */
    public static final float GRAVITY = 0.3F;

    /** Maximale Geschwindigkeit wie der Bird nach rechts fliegt */
    public static final float MAX_SPEED = 5.0F;

    /** Horizontale Geschwindigkeit wie Boden und Rohre nach links kommen */
    public static final float HORIZONTAL_VELOCITY = 3.0F;

    public FloatingGameObject(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public abstract void tick();
}
