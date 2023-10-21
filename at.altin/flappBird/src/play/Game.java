package play;

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
    public static final int WIDTH = 432;
    public static final int HEIGHT = 768;
    public static final int BIRD_POS_X = 50;
    public static final int SCORE_X_POS = 216;
    public static final int SCORE_Y_POS = 40;
    public static final int SCORE_FONT_SIZE = 48;
    public static final int IMAGE_Y = 130;
    public static final int NUM_BUFFERS = 3;
    public static final double AMOUNT_OF_TICKS = 60.0D;
    public static final double NANOSECONDS_TICK = 1.0E9D / AMOUNT_OF_TICKS;
    public static final int BIRD_STARTING_HEIGHT = 36;

    public boolean running;
    public static boolean gameover;
    public  BufferedImage img_gameover;
    public BufferedImage background;
    public Ground ground;
    public static Bird bird;
    public static Button startButton;
    public static int score;
    Thread thread;
    public ServerSocket serverSocket;

    public Game() {
    }

    public static void main(String[] args) {
        new Window(WIDTH, HEIGHT, "FlappyBird [Springen mit Leertaste]", new Game());
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
        bird = new Bird(BIRD_POS_X, BIRD_POS_X, BIRD_POS_X, BIRD_STARTING_HEIGHT);
        startButton = new Button(Button.startGameButtonDesign().get("x"),
                Button.startGameButtonDesign().get("y"),
                Button.startGameButtonDesign().get("width"),
                Button.startGameButtonDesign().get("height"), GraphicsLoader.loadGraphics("pictures/playbutton.png"));
    }

    public static void reset() {
        ObjectHandler.list.clear();
        ObjectHandler.addObject(bird);
        gameover = false;
        score = 0;
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
            this.createBufferStrategy(NUM_BUFFERS);
        } else {
            Graphics g = bs.getDrawGraphics();
            g.drawImage(background, 0, 0, null);
            ground.render(g);
            ObjectHandler.render(g);
            if (gameover) {
                g.drawImage(img_gameover, TubeHandler.TUBE_WIDTH, IMAGE_Y, null);
                startButton.render(g);
            }

            g.setFont(new Font("Arial", Font.BOLD, SCORE_FONT_SIZE));
            g.setColor(Color.WHITE);
            String s = Integer.toString(score);
            int textWidth = g.getFontMetrics().stringWidth(s);
            g.drawString(s, SCORE_X_POS - textWidth / 2, SCORE_Y_POS);
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
            delta += (double)(now - pastTime) / NANOSECONDS_TICK;

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