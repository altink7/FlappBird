package objects;

import loader.GraphicsLoader;
import objects.base.FloatingGameObject;
import play.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Ground extends FloatingGameObject {
    public static final int DRAW_IMAGE_Y_VALUE = 600;
    private final BufferedImage image = GraphicsLoader.loadGraphics("pictures/ground.png");
    private int x1 = 0;
    private int x2 = Game.WIDTH;

    public Ground() {
        super(0, 0, Game.WIDTH, Game.HEIGHT-DRAW_IMAGE_Y_VALUE);
    }

    public void tick() {
        this.x1 = (int)((float)this.x1 - HORIZONTAL_VELOCITY);
        this.x2 = (int)((float)this.x2 - HORIZONTAL_VELOCITY);
        if (this.x1 + Game.WIDTH < 0) {
            this.x1 = Game.WIDTH;
        }

        if (this.x2 + Game.WIDTH < 0) {
            this.x2 = Game.WIDTH;
        }
    }

    public void render(Graphics g) {
        g.drawImage(this.image, this.x1, DRAW_IMAGE_Y_VALUE, null);
        g.drawImage(this.image, this.x2, DRAW_IMAGE_Y_VALUE, null);
    }
}