package display;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public class Animation {
    private int x;
    private int y;
    private int currentImage;
    private long delay;
    private long startTime;
    private boolean loop;
    private boolean running;
    private GameObject target;
    private BufferedImage[] images;

    public Animation(GameObject target, long delay, boolean loop, BufferedImage[] images) {
        this.x = target.getX();
        this.y = target.getY();
        this.currentImage = 0;
        this.delay = delay;
        this.startTime = 0L;
        this.loop = loop;
        this.setTarget(target);
        this.images = images;
    }

    public Animation(GameObject target, int x, int y, long delay, boolean loop, BufferedImage[] images) {
        this.x = target.getX() + x;
        this.y = target.getY() + y;
        this.currentImage = 0;
        this.delay = delay;
        this.startTime = 0L;
        this.loop = loop;
        this.setTarget(target);
        this.images = images;
    }

    public Animation(int x, int y, long delay, boolean loop, BufferedImage[] images) {
        this.x = x;
        this.y = y;
        this.currentImage = 0;
        this.delay = delay;
        this.startTime = 0L;
        this.loop = loop;
        this.setTarget((GameObject)null);
        this.images = images;
    }

    public void render(Graphics g) {
        if (this.target == null) {
            g.drawImage(this.images[this.currentImage], this.x, this.y, (ImageObserver)null);
        } else {
            g.drawImage(this.images[this.currentImage], this.target.x, this.target.y, (ImageObserver)null);
        }

    }

    public void tick() {
        long pastTime = (System.nanoTime() - this.startTime) / 1000000L;
        if (pastTime >= this.delay && this.running) {
            ++this.currentImage;
            this.startTime = System.nanoTime();
        }

        if (this.currentImage == this.images.length) {
            this.currentImage = 0;
            if (!this.loop) {
                this.stop();
            }
        }

    }

    public void start() {
        this.running = true;
        this.currentImage = 0;
        this.startTime = 0L;
    }

    public void stop() {
        this.running = false;
        this.currentImage = 0;
        this.startTime = 0L;
    }

    public GameObject getTarget() {
        return this.target;
    }
    public void setTarget(GameObject target) {
        this.target = target;
    }
    public int getX() {
        return this.x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return this.y;
    }
    public void setY(int y) {
        this.y = y;
    }

    public int getCurrentImage() {
        return this.currentImage;
    }

    public void setCurrentImage(int currentImage) {
        this.currentImage = currentImage;
    }

    public long getDelay() {
        return this.delay;
    }

    public void setDelay(long delay) {
        this.delay = delay;
    }

    public boolean isLoop() {
        return this.loop;
    }

    public void setLoop(boolean loop) {
        this.loop = loop;
    }

    public boolean isRunning() {
        return this.running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public BufferedImage[] getImages() {
        return this.images;
    }

    public void setImages(BufferedImage[] images) {
        this.images = images;
    }
}
