package objects;

import display.GameObject;
import handler.ObjectHandler;
import objects.base.FloatingGameObject;
import objects.helper.TubeType;
import play.Game;
import loader.GraphicsLoader;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tube extends FloatingGameObject {
    TubeType type;
    BufferedImage tubeBlock;
    BufferedImage tube;

    public Tube(int x, int y, int width, int height, TubeType type) {
        super(x, y, width, height);
        this.type = type;
        this.tube = GraphicsLoader.loadGraphics("pictures/tube.png");
        if (type == TubeType.BOTTOM) {
            this.tubeBlock = GraphicsLoader.loadGraphics("pictures/tubebottomdown.png");
        } else if (type == TubeType.TOP) {
            this.tubeBlock = GraphicsLoader.loadGraphics("pictures/tubebottomtop.png");
        }

    }

    public void tick() {
        this.x = (int)((float)this.x - HORIZONTAL_VELOCITY);
        if (this.x + this.width < 0) {
            ObjectHandler.removeObject(this);
            if (this.type == TubeType.TOP) {
                ++Game.score;
            }
        }

    }

    public void render(Graphics g) {
        if (this.type == TubeType.BOTTOM) {
            g.drawImage(this.tube, this.x, this.y, 72, this.height, null);
            g.drawImage(this.tubeBlock, this.x - 3, this.y, null);
        } else if (this.type == TubeType.TOP) {
            g.drawImage(this.tube, this.x, this.y, 72, this.height, null);
            g.drawImage(this.tubeBlock, this.x - 3, this.y + this.height - 36, null);
        }

    }
}
