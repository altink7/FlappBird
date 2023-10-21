package objects;

import loader.GraphicsLoader;
import objects.base.FloatingGameObject;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Ground extends FloatingGameObject {
    private final BufferedImage image = GraphicsLoader.loadGraphics("pictures/ground.png");
    private int x1 = 0;
    private int x2 = 432;

    public Ground() {
        super(0, 0, 432, 168);
    }

    public void tick() {
        this.x1 = (int)((float)this.x1 - HORIZONTAL_VELOCITY);
        this.x2 = (int)((float)this.x2 - HORIZONTAL_VELOCITY);
        if (this.x1 + 432 < 0) {
            this.x1 = 432;
        }

        if (this.x2 + 432 < 0) {
            this.x2 = 432;
        }
    }

    public void render(Graphics g) {
        g.drawImage(this.image, this.x1, 600, null);
        g.drawImage(this.image, this.x2, 600, null);
    }
}