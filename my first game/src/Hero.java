import java.awt.*;
import java.awt.image.BufferedImage;

public class Hero extends GameObject {
    Handl handl;
    Game game;
    private BufferedImage hero_image;

    public Hero(int x, int y, ID id,Handl handl,Game game,SpriteSheet ss) {
        super(x, y, id,ss);
        this.handl=handl;
        this.game=game;
        hero_image=ss.grabImage(1,1,32,50);

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

    private void collision(){
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
                   game.ammo+=30;
                   handl.removeObject(tempGameObject);
                }
            }
            if(tempGameObject.getId()==ID.Enemy){
                if(getBounds().intersects(tempGameObject.getBounds())){
                    game.hp-=2;
                    if(game.hp<=0){
                        System.exit(0);
                    }
                }
            }



        }
    }

    public void render(Graphics g) {
       g.drawImage(hero_image,x,y,null);
    }


    public Rectangle getBounds() {
        return new Rectangle(x,y,32,48);
    }
}
