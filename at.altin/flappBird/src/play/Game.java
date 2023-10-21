package play;

import display.GameObject;
import display.Window;
import handler.KeyHandler;
import handler.MouseHandler;
import handler.ObjectHandler;
import handler.TubeHandler;
import objects.Bird;
import objects.Button;
import objects.Ground;
import service.GraphicsLoader;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.net.ServerSocket;
import java.util.Iterator;

public class Game extends Canvas implements Runnable {
    public static final int WIDTH = 432;
    public static final int HEIGHT = 768;
    public boolean running;
    public static boolean gameover;
    public static BufferedImage img_gameover;
    public static BufferedImage background;
    public static Ground ground;
    public static Bird bird;
    public static Button startButton;
    public static int score;
    Thread thread;
    public ServerSocket serverSocket;

    public Game() {
    }

    public static void main(String[] args) {
        new Window(432, 768, "FlappyBird [Springen mit Leertaste]", new Game());
    }

    public synchronized void start() {
        this.running = true;
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
        bird = new Bird(50, 50, 51, 36);
        startButton = new Button(138, 200, 156, 87, GraphicsLoader.loadGraphics("pictures/playbutton.png"));
    }

    public synchronized static void tick() {
        Iterator<GameObject> iterator = ObjectHandler.list.iterator();
        while (iterator.hasNext()) {
            GameObject obj = iterator.next();
            obj.tick();
            if (obj.getY() > 768 || obj.getY() < -obj.getHeight()) {
                iterator.remove();
            }
        }
    }

    public void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
        } else {
            Graphics g = bs.getDrawGraphics();
            g.drawImage(background, 0, 0, null);
            ground.render(g);
            ObjectHandler.render(g);
            if (gameover) {
                g.drawImage(img_gameover, 72, 130, null);
                startButton.render(g);
            }

            g.setFont(new Font("Arial", Font.BOLD, 48));
            g.setColor(Color.WHITE);
            String s = Integer.toString(score);
            int textWidth = g.getFontMetrics().stringWidth(s);
            g.drawString(s, 216 - textWidth / 2, 40);
            g.dispose();
            bs.show();
        }
    }

    public void run() {
        this.init();
        this.requestFocus();
        long pastTime = System.nanoTime();
        double amountOfTicks = 60.0D;
        double ns = 1.0E9D / amountOfTicks;
        double delta = 0.0D;
        long timer = System.currentTimeMillis();
        int updates = 0;
        int frames = 0;

        while(this.running) {
            long now = System.nanoTime();
            delta += (double)(now - pastTime) / ns;

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