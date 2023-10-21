package objects;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Button {
    public int x;
    public int y;
    public int width;
    public int height;
    public boolean pressed;
    private final BufferedImage image;

    public Button(int x, int y, int width, int height, BufferedImage image) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.image = image;
    }

    public static boolean checkCollision(int mouseX, int mouseY, Button btn) {
        return mouseX >= btn.x && mouseX <= btn.x + btn.width && mouseY >= btn.y && mouseY <= btn.y + btn.height;
    }

    public void render(Graphics g) {
        if (this.pressed) {
            g.drawImage(this.image, this.x + 1, this.y + 1, this.width - 2, this.height - 2, null);
        } else {
            g.drawImage(this.image, this.x, this.y, null);
        }

    }
}