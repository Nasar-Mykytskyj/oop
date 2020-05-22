package controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
@Component

@Scope(value = "singleton")
public class BufferedImageLoader {
    private BufferedImage image;

    public BufferedImageLoader() {
    }

    public BufferedImage loadImage(String path){

        try {
            image = ImageIO.read(getClass().getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;

    }
}
