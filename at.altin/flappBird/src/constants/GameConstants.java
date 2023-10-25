package constants;

/**
 * Constants for the Flappy Bird game.
 */
public class GameConstants {
    /**
     * The width of the game window.
     */
    public static final int WIDTH = 432;

    /**
     * The height of the game window.
     */
    public static final int HEIGHT = 768;

    /**
     * The Y position for drawing images.
     */
    public static final int IMAGE_Y = 130;

    /**
     * The number of image buffers.
     */
    public static final int NUM_BUFFERS = 3;

    /**
     * The amount of ticks per second.
     */
    public static final double AMOUNT_OF_TICKS = 60.0D;

    /**
     * The time in nanoseconds per tick.
     */
    public static final double NANOSECONDS_TICK = 1.0E9D / AMOUNT_OF_TICKS;

    /**
     * The horizontal velocity at which the ground and tubes move to the left.
     */
    public static float HORIZONTAL_VELOCITY = 3.0F;


}
