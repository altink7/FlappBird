package handler;

import dialog.UsernameInputDialog;
import objects.Button;
import play.Game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.logging.Logger;

public class MouseHandler implements MouseListener {

    private static final Logger logger = Logger.getLogger(MouseHandler.class.getName());

    public MouseHandler() {
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
        if (Button.checkCollision(e.getX(), e.getY(), Game.START_BUTTON) && Game.gameover) {
            logger.info("Start Button pressed");

            Game.reset();
        }

    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }
}
