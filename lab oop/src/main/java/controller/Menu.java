package controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.awt.image.BufferedImage;
@Component

@Scope(value = "singleton")
public class Menu implements Render {
    BufferedImageLoader loader = new BufferedImageLoader();
    private BufferedImage play ;
    private BufferedImage exit;

    public Menu() {
    }


    public void render(Graphics g){

        Graphics2D g2d=(Graphics2D) g;

        play =loader.loadImage("/button.png");
        exit=loader.loadImage("/exitb.png");


        Font fon=new Font("arial",Font.ITALIC,70);
        g.setFont(fon);
        g.setColor(Color.WHITE);
        g.drawString("My game",700/2,120);


        g2d.drawImage( play,400,220,null);
        g2d.drawImage( exit,400,350,null);
    }
}
