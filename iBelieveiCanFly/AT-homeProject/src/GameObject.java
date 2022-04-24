import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected float velX;
    protected float velY;

    public GameObject(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public abstract void tick();

    public abstract void render(Graphics var1);

    public Rectangle getBounds() {
        return new Rectangle(this.x, this.y, this.width, this.height);
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

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public float getVelX() {
        return this.velX;
    }

    public void setVelX(float velX) {
        this.velX = velX;
    }

    public float getVelY() {
        return this.velY;
    }

    public void setVelY(float velY) {
        this.velY = velY;
    }
}
