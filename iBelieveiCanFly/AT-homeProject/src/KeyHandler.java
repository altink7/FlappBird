import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    public KeyHandler() {
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 32) {
            Game.bird.setVelY(-5.0F);
        }

    }

    public void keyReleased(KeyEvent e) {
    }
}
