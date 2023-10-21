package loader;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class GraphicsLoader {

    private static final Logger logger = Logger.getLogger(GraphicsLoader.class.getName());

    public GraphicsLoader() {
    }

    public static BufferedImage loadGraphics(String path) {
        BufferedImage image = null;

        try {
            image = ImageIO.read(ResourceLoader.load("/" + path));
        } catch (IOException var3) {
            logger.severe("Error while loading image: " + path);
        }

        return image;
    }
}
