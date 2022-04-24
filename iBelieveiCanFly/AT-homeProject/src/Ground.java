import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public class Ground {
    private BufferedImage image = GraphicsLoader.loadGraphics("ground.png");
    private int x1 = 0;
    private int x2 = 432;

    public Ground() {
    }

    public void tick() {
        float velY = 3.0F;
        this.x1 = (int)((float)this.x1 - velY);
        this.x2 = (int)((float)this.x2 - velY);
        if (this.x1 + 432 < 0) {
            this.x1 = 432;
        }

        if (this.x2 + 432 < 0) {
            this.x2 = 432;
        }

    }

    public void render(Graphics g) {
        g.drawImage(this.image, this.x1, 600, (ImageObserver)null);
        g.drawImage(this.image, this.x2, 600, (ImageObserver)null);
    }
}