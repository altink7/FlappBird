package handler;

import objects.Button;
import play.Game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseHandler implements MouseListener {
    public MouseHandler() {
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
        if (Button.checkCollision(e.getX(), e.getY(), Game.startButton) && Game.gameover) {
            Game.startButton.pressed = true;
            ObjectHandler.list.clear();
            ObjectHandler.addObject(Game.bird);
            Game.gameover = false;
            Game.score = 0;
            Game.startButton.pressed = false;
        }

    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }
}
