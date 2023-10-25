package play;

import constants.GameConstants;
import constants.objects.BirdConstants;
import constants.objects.ScoreConstants;
import constants.objects.TubeConstants;
import ranking.UsernameInputDialog;
import display.Window;
import handler.KeyHandler;
import handler.MouseHandler;
import handler.ObjectHandler;
import handler.TubeHandler;
import loader.GraphicsLoader;
import objects.Bird;
import objects.Button;
import objects.Ground;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.net.ServerSocket;

public class Game extends Canvas implements Runnable {
    public boolean running;
    public static boolean gameover;
    public  BufferedImage img_gameover;
    public BufferedImage background;
    public Ground ground;
    public static Bird BIRD;
    public static Button START_BUTTON;
    public static Button RANKING_BUTTON;
    public static int SCORE;
    public static int LEVEL;
    Thread thread;
    public ServerSocket serverSocket;

    public Game() {
    }

    public static void main(String[] args) {
        new Window(GameConstants.WIDTH, GameConstants.HEIGHT, "FlappyBird [Springen mit Leertaste]", new Game());
    }

    public synchronized void start() {
        running = true;
        this.thread = new Thread(this);
        this.thread.start();
        this.run();
    }

    public void init() {
        this.addKeyListener(new KeyHandler());
        this.addMouseListener(new MouseHandler());
        img_gameover = GraphicsLoader.loadGraphics("pictures/gameover.png");
        background = GraphicsLoader.loadGraphics("pictures/background.png");
        ground = new Ground();
        BIRD = new Bird(BirdConstants.BIRD_POS_X, BirdConstants.BIRD_POS_X, BirdConstants.BIRD_POS_X, BirdConstants.BIRD_STARTING_HEIGHT);
        START_BUTTON = new Button(Button.startGameButtonDesign().get("x"),
                Button.startGameButtonDesign().get("y"),
                Button.startGameButtonDesign().get("width"),
                Button.startGameButtonDesign().get("height"), GraphicsLoader.loadGraphics("pictures/playbutton.png"));

        RANKING_BUTTON = new Button(Button.rankingButtonDesign().get("x"),
                Button.rankingButtonDesign().get("y"),
                Button.rankingButtonDesign().get("width"),
                Button.rankingButtonDesign().get("height"), GraphicsLoader.loadGraphics("pictures/playbutton.png"));
    }

    public static void reset() {
        ObjectHandler.list.clear();
        ObjectHandler.addObject(BIRD);
        gameover = false;
        SCORE = 0;
    }

    public void tick() {
        if (!gameover) {
            ObjectHandler.tick();
            ground.tick();
        }
    }

    public void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(GameConstants.NUM_BUFFERS);
        } else {
            Graphics g = bs.getDrawGraphics();
            g.drawImage(background, 0, 0, null);
            ground.render(g);
            ObjectHandler.render(g);
            if (gameover && UsernameInputDialog.INPUT_FINISHED) {
                g.drawImage(img_gameover, TubeConstants.TUBE_WIDTH, GameConstants.IMAGE_Y, null);
                START_BUTTON.render(g);
                RANKING_BUTTON.render(g);
            }

            g.setFont(new Font("Arial", Font.BOLD, ScoreConstants.SCORE_FONT_SIZE));
            g.setColor(Color.WHITE);
            String s = Integer.toString(SCORE);
            String l = Integer.toString(LEVEL);
            int scoreWidth = g.getFontMetrics().stringWidth(s);
            int levelWidth = g.getFontMetrics().stringWidth(l);
            g.drawString(s, ScoreConstants.SCORE_X_POS - scoreWidth / 2, ScoreConstants.SCORE_Y_POS);
            g.drawString(l, ScoreConstants.SCORE_X_POS - levelWidth / 2, ScoreConstants.SCORE_Y_POS + 50);
            g.dispose();
            bs.show();
        }
    }

    public void run() {
        this.init();
        this.requestFocus();
        long pastTime = System.nanoTime();
        double delta = 0.0D;
        long timer = System.currentTimeMillis();
        int updates = 0;
        int frames = 0;

        while(running) {
            long now = System.nanoTime();
            delta += (double)(now - pastTime) / GameConstants.NANOSECONDS_TICK;

            for(pastTime = now; delta > 0.0D; --delta) {
                tick();
                ++updates;
                this.render();
                ++frames;
            }

            if (System.currentTimeMillis() - timer > 1000L) {
                timer += 1000L;
                System.out.println("FPS: " + frames + " | TICKS: " + updates);
                TubeHandler.tick();
                updates = 0;
                frames = 0;
            }
        }

    }
}