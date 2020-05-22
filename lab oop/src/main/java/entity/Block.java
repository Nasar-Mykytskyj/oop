package entity;

import controller.Render;
import controller.SpriteSheet;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.awt.image.BufferedImage;
@Component

@Scope(value = "prototype")
public class Block extends GameObject  {

    private BufferedImage block_image;

    public Block() {
    }

    public Block(int x, int y, ID id , SpriteSheet ss) {
        super(x, y, id,ss);
        block_image=ss.grabImage(6,2,32,32);
    }


    public void tick() {

    }

    public void render(Graphics g) {
        g.drawImage(block_image,x,y,null);
    }


    public Rectangle getBounds() {
        return new Rectangle(x,y,32,32);
    }
}
