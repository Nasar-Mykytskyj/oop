package controller;

import game.Game;
import entity.Bullet;
import entity.GameObject;
import entity.ID;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import repository.Handl;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
@Component

@Scope(value = "singleton")
public class MouseInput extends MouseAdapter {
    ApplicationContext applicationContext;
    private Handl handl;

    private Camera camera;

    public MouseInput() {

    }

    private Game game;
    private SpriteSheet ss;
    public MouseInput(  Game game, SpriteSheet ss, ApplicationContext applicationContext){
        this.applicationContext=applicationContext;
        this.handl=(Handl)applicationContext.getBean("handl");
        this.camera=(Camera) applicationContext.getBean("camera");
        this.game=game;
        this.ss=ss;
    }
    public void mousePressed(MouseEvent e){
        //2d.drawImage( play,400,220,null);
        //g2d.drawImage( exit,400,350,null);
        if(game.getId()== ID.Menu) {
            if (e.getX() >= 450 - 100 && e.getX() <= 500 + 100) {
                if (e.getY() >= 320 - 100 && e.getY() <= 200 + 100) {
                    game.setId(ID.Game);
                }
            }
            if (e.getX() >= 500 - 100 && e.getX() <= 500 + 100) {
                if (e.getY() >= 400 - 60 && e.getY() <= 400 + 50) {
                    System.exit(0);
                }
            }
        }
        int mx=(int)(e.getX()+camera.getX());
        int my=(int)(e.getY()+camera.getY());
        for (int i = 0; i<handl.gameObject.size(); i++){
            GameObject tempGameObject =handl.gameObject.get(i);
            try {
                if (tempGameObject.getId() == ID.Player && game.getHero().getPokaznyky().getBulletNumber()> 0) {
                    handl.addObject((new Bullet(tempGameObject.getX() + 16, tempGameObject.getY() + 24, ID.Bullet, handl, mx, my, ss)));
                    game.getHero().getPokaznyky().setBulletNumber(game.getHero().getPokaznyky().getBulletNumber()-1);
                }
            }
            catch (NullPointerException k){

            }

        }
    }
}
