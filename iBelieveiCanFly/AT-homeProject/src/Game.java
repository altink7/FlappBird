import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.net.ServerSocket;

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
    ServerSocket serverSocket;

    public Game() {
    }

    public static void main(String[] args) {
        new Window(432, 768, "FlappyBird [Springen mit Leertaste]", new Game());
    }

    public synchronized void start() {
        this.running = true;
        this.thread = new Thread();
        this.thread.start();
        this.run();
    }

    public void init() {
        this.addKeyListener(new KeyHandler());
        this.addMouseListener(new MouseHandler());
        img_gameover = GraphicsLoader.loadGraphics("gameover.png");
        background = GraphicsLoader.loadGraphics("background.png");
        ground = new Ground();
        bird = new Bird(50, 50, 51, 36);
        startButton = new Button(138, 200, 156, 87, GraphicsLoader.loadGraphics("playbutton.png"));
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
            this.createBufferStrategy(3);
        } else {
            Graphics g = bs.getDrawGraphics();
            g.drawImage(background, 0, 0, (ImageObserver)null);
            ground.render(g);
            ObjectHandler.render(g);
            if (gameover) {
                g.drawImage(img_gameover, 72, 130, (ImageObserver)null);
                startButton.render(g);
            }

            g.setFont(new Font("Arial", 1, 48));
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
                this.tick();
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