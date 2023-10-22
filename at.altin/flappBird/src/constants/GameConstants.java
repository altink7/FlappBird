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
     * The initial X position of the bird.
     */
    public static final int BIRD_POS_X = 50;

    /**
     * The X position for displaying the score.
     */
    public static final int SCORE_X_POS = 216;

    /**
     * The Y position for displaying the score.
     */
    public static final int SCORE_Y_POS = 40;

    /**
     * The font size for displaying the score.
     */
    public static final int SCORE_FONT_SIZE = 48;

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
     * The starting height of the bird.
     */
    public static final int BIRD_STARTING_HEIGHT = 36;

    /**
     * The starting X position of the tubes.
     */
    public static final int X_START_POSITION_TUBE = 500;

    /**
     * The width of the tubes.
     */
    public static final int TUBE_WIDTH = 78;

    /**
     * The gravity affecting the bird's downward movement.
     */
    public static final float GRAVITY = 0.3F;

    /**
     * The maximum speed at which the bird can move to the right.
     */
    public static final float MAX_SPEED = 5.0F;

    /**
     * The horizontal velocity at which the ground and tubes move to the left.
     */
    public static final float HORIZONTAL_VELOCITY = 3.0F;

    /**
     * The Y position for drawing ground images.
     */
    public static final int DRAW_IMAGE_Y_VALUE = 600;

    /**
     * The size of the ground.
     */
    public static final int GROUND_SIZE = 168;
}
