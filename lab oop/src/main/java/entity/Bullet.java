package entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import repository.Handl;
import controller.SpriteSheet;

import java.awt.*;
@Component

@Scope(value = "prototype")
public class Bullet extends GameObject implements Collision {

    public Handl handl;

    public Bullet(int x, int y, ID id, Handl handl, int mx, int my, SpriteSheet ss) {
        super(x, y, id,ss);
        this.handl = handl;



        velx=(mx-x)/10;
        vely=(my-y)/10;

    }


    public void tick() {
        x += velx;
        y += vely;
        collision();
    }


    public void render(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillOval(x, y,8,8 );
    }


    public Rectangle getBounds() {
        return new Rectangle(x,y,8,8);
    }

    @Override
    public void collision() {
        for(int i = 0; i<handl.gameObject.size(); i++){
            GameObject tempGameObject =handl.gameObject.get(i);
            if(tempGameObject.getId()==ID.Block){
                if(getBounds().intersects(tempGameObject.getBounds())) {
                    handl.removeObject(this);
                }
            }
        }
    }
}
