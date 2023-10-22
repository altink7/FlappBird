package handler;

import objects.Button;
import play.Game;
import ranking.RankingTable;

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

        if(Button.checkCollision(e.getX(), e.getY(), Game.RANKING_BUTTON) && Game.gameover) {
            logger.info("Ranking Button pressed");

            new RankingTable();
        }

    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }
}
