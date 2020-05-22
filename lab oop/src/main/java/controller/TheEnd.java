package controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.awt.image.BufferedImage;
@Component

@Scope(value = "singleton")
public class TheEnd implements Render{
    int x;
    public TheEnd() {

    }
    public void render(Graphics g){

        Graphics2D g2d=(Graphics2D) g;


        Font fon=new Font("arial",Font.ITALIC,70);
        g.setFont(fon);
        g.setColor(Color.WHITE);
        g.drawString("TheEnd",700/2,120);



    }
}
