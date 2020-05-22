package entity;

import game.Game;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import repository.Handl;
import controller.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferedImage;
@Component

@Scope(value = "singleton")
public class Hero extends GameObject implements Collision{
    Handl handl;
    Game game;

    Pokaznyky pokaznyky;
    ApplicationContext applicationContext ;
    private BufferedImage hero_image;
    public Hero() {
    }

    public Hero(int x, int y, ID id,  Game game, SpriteSheet ss,ApplicationContext applicationContext) {
        super(x, y, id,ss);

        this.game=game;
        hero_image=ss.grabImage(1,1,32,50);
        this.applicationContext= applicationContext;
        pokaznyky=(Pokaznyky)applicationContext.getBean("pokaznyky");
        this.handl=(Handl)applicationContext.getBean("handl");
    }


    public void tick() {
        x+=velx;
        y+=vely;
        collision();
        if(handl.isUp()) vely=-5;
        else if(!handl.isDown())vely=0;

        if(handl.isDown()) vely=5;
        else if(!handl.isUp())vely=0;

        if(handl.isLeft())velx=-5;
        else if(!handl.isRight())velx=0;

        if(handl.isRight())velx=5;
        else if(!handl.isLeft())velx=0;
    }

    public void collision(){
        for(int i = 0; i<handl.gameObject.size(); i++){

            GameObject tempGameObject =handl.gameObject.get(i);

            if(tempGameObject.getId()==ID.Block){
                if(getBounds().intersects(tempGameObject.getBounds())){
                    x+=velx*-1;
                    y+=vely*-1;
                }
            }

            if(tempGameObject.getId()==ID.Crate){
                if(getBounds().intersects(tempGameObject.getBounds())){

                   pokaznyky.setBulletNumber(pokaznyky.getBulletNumber()+30);
                   handl.removeObject(tempGameObject);
                }
            }
            if(tempGameObject.getId()==ID.Enemy){
                if(getBounds().intersects(tempGameObject.getBounds())){

                    pokaznyky.setHp(pokaznyky.getHp()-2);
                    if(pokaznyky.getHp()<=0){

                        //game.stop();
                        System.exit(0);
                    }
                }
            }



        }
    }
    public Pokaznyky getPokaznyky() {
        return pokaznyky;
    }

    public void setPokaznyky(Pokaznyky pokaznyky) {
        this.pokaznyky = pokaznyky;
    }
    public void render(Graphics g) {
       g.drawImage(hero_image,x,y,null);
    }


    public Rectangle getBounds() {
        return new Rectangle(x,y,32,48);
    }
}
