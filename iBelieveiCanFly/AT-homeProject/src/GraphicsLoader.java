import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class GraphicsLoader {
    public GraphicsLoader() {
    }

    public static BufferedImage loadGraphics(String path) {
        BufferedImage image = null;

        try {
            image = ImageIO.read(ResourceLoader.load("/" + path));
        } catch (IOException var3) {
            var3.printStackTrace();
        }

        return image;
    }
}
