package objects;

import display.Animation;
import display.GameObject;
import handler.ObjectHandler;
import play.Game;
import loader.GraphicsLoader;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Bird extends GameObject {
    Animation animation;
    public float gravity = 0.3F;
    public float maxSpeed = 12.0F;

    public Bird(int x, int y, int width, int height) {
        super(x, y, width, height);
        BufferedImage[] images = new BufferedImage[3];

        for(int i = 0; i < images.length; ++i) {
            images[i] = GraphicsLoader.loadGraphics("pictures/bird" + i + ".png");
        }

        this.animation = new Animation(this, 100L, true, images);
        this.animation.start();
        ObjectHandler.addObject(this);
    }

    public void tick() {
        this.velY += this.gravity;
        this.y = (int)((float)this.y + this.velY);
        if (this.velY > this.maxSpeed) {
            this.velY = this.maxSpeed;
        }

        if (this.y + this.height > 602) {
            this.y = 602 - this.height;
            this.setVelY(0.0F);
        }

        if (this.y < 0) {
            this.y = 0;
            this.setVelY(0.0F);
        }

        GameObject temp;

        for(int i = 0; i < ObjectHandler.list.size(); ++i) {
            temp = ObjectHandler.list.get(i);
            if (temp instanceof Tube && this.getBounds().intersects(temp.getBounds())) {
                Game.gameover = true;
            }
        }

        this.animation.tick();
    }

    public void render(Graphics g) {
        this.animation.render(g);
    }
}
