package loader;

import java.io.InputStream;
import java.util.logging.Logger;

public class ResourceLoader {

    private static final Logger logger = Logger.getLogger(ResourceLoader.class.getName());

    public ResourceLoader() {
    }

    public static InputStream load(String path) {
        InputStream input = ResourceLoader.class.getResourceAsStream(path);

        if (input == null) {
            input = ResourceLoader.class.getResourceAsStream("/" + path);
        }

        return input;
    }
}
