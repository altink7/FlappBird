package objects;

import constants.GameConstants;
import loader.GraphicsLoader;
import objects.base.FloatingGameObject;
import play.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Ground extends FloatingGameObject {
    private final BufferedImage image = GraphicsLoader.loadGraphics("pictures/ground.png");
    private int x1 = 0;
    private int x2 = GameConstants.WIDTH;

    public Ground() {
        super(0, 0, GameConstants.WIDTH, GameConstants.HEIGHT-GameConstants.DRAW_IMAGE_Y_VALUE);
    }

    public void tick() {
        this.x1 = (int)((float)this.x1 - GameConstants.HORIZONTAL_VELOCITY);
        this.x2 = (int)((float)this.x2 - GameConstants.HORIZONTAL_VELOCITY);
        if (this.x1 + GameConstants.WIDTH < 0) {
            this.x1 = GameConstants.WIDTH;
        }

        if (this.x2 + GameConstants.WIDTH < 0) {
            this.x2 = GameConstants.WIDTH;
        }
    }

    public void render(Graphics g) {
        g.drawImage(this.image, this.x1, GameConstants.DRAW_IMAGE_Y_VALUE, null);
        g.drawImage(this.image, this.x2, GameConstants.DRAW_IMAGE_Y_VALUE, null);
    }
}