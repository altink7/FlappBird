package handler;

import play.Game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.Logger;

public class KeyHandler implements KeyListener {

    private static final Logger logger = Logger.getLogger(KeyHandler.class.getName());

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
