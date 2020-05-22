package entity;

import game.Game;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import repository.Handl;
import controller.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;
@Component

@Scope(value = "prototype")
public class Enemy extends GameObject implements Collision{

    ApplicationContext applicationContext;
    private Handl handl;
    Game game;
    Random r=new Random();
    private int choose=0;
    private int hp=100;

    private BufferedImage enemy_image;
    public Enemy(int x, int y, ID id, SpriteSheet ss, Game game, ApplicationContext applicationContext) {
        super(x, y, id,ss);
        this.handl=(Handl)applicationContext.getBean("handl");
        enemy_image=ss.grabImage(4,1,32,32);
        this.game=game;
    }


    public void tick() {
                x+=velx;
                y+=vely;
                collision();
                choose=r.nextInt(10);

                for(int i = 0; i<handl.gameObject.size(); i++){
                    GameObject tempGameObject =handl.gameObject.get(i);
                    if(tempGameObject.getId()==ID.Block){
                        if(getBoundsBig().intersects(tempGameObject.getBounds())){
                            x+=(velx*2)*-1;
                            y+=(vely*2)*-1;
                            vely=0;
                            velx=0;
                        }
                        else if(choose==0) {
                            velx = (r.nextInt(3 - -3) + -3);
                            vely = (r.nextInt(3 - -3) + -3);

                        }
                    }




                }


    }


    public void render(Graphics g) {
       g.drawImage(enemy_image,x,y,null);


    }

    public void collision(){
        for(int i = 0; i<handl.gameObject.size(); i++){

            GameObject tempGameObject =handl.gameObject.get(i);

            if(tempGameObject.getId()==ID.Bullet){
                if(getBounds().intersects(tempGameObject.getBounds())){
                    hp-=25;
                    if(hp==0){
                        try {
                            handl.removeObject(this);
                            game.getHero().getPokaznyky().setKillsNumber(game.getHero().getPokaznyky().getKillsNumber()+1);
                        }
                         catch (NullPointerException e){

                        }
                    }
                }
            }
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x,y,32,32);
    }
    public Rectangle getBoundsBig() {
        return new Rectangle(x-16,y-16,64,64);
    }
}
