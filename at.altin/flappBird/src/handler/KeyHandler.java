package handler;

import constants.GameConstants;
import constants.objects.BirdConstants;
import objects.base.FloatingGameObject;
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
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            Game.BIRD.setVelY(-BirdConstants.MAX_SPEED);
        }

    }

    public void keyReleased(KeyEvent e) {
    }
}
