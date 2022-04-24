import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public class Tube extends GameObject {
    TubeType type;
    BufferedImage tubeBlock;
    BufferedImage tube;

    public Tube(int x, int y, int width, int height, TubeType type) {
        super(x, y, width, height);
        this.type = type;
        this.velY = 1.0F;
        this.tube = GraphicsLoader.loadGraphics("tube.png");
        if (type == TubeType.BOTTOM) {
            this.tubeBlock = GraphicsLoader.loadGraphics("tubebottomdown.png");
        } else if (type == TubeType.TOP) {
            this.tubeBlock = GraphicsLoader.loadGraphics("tubebottomtop.png");
        }

    }

    public void tick() {
        this.y = (int)((float)this.y - this.velY);
        if (this.y + this.width < 0) {
            ObjectHandler.removeObject(this);
            if (this.type == TubeType.TOP) {
                ++Game.score;
            }
        }

    }

    public void render(Graphics g) {
        if (this.type == TubeType.BOTTOM) {
            g.drawImage(this.tube, this.x, this.y, 72, this.height, (ImageObserver)null);
            g.drawImage(this.tubeBlock, this.x - 3, this.y, (ImageObserver)null);
        } else if (this.type == TubeType.TOP) {
            g.drawImage(this.tube, this.x, this.y, 72, this.height, (ImageObserver)null);
            g.drawImage(this.tubeBlock, this.x - 3, this.y + this.height - 36, (ImageObserver)null);
        }

    }
}
