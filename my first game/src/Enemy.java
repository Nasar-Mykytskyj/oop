import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Enemy extends GameObject {

    private Handl handl;
    Game game;
    Random r=new Random();
    int choose=0;
    int hp=100;
    private BufferedImage enemy_image;
    public Enemy(int x, int y, ID id,Handl handl,SpriteSheet ss) {
        super(x, y, id,ss);
        this.handl=handl;
        enemy_image=ss.grabImage(4,1,32,32);
    }


    public void tick() {
                x+=velx;
                y+=vely;
                Damage();
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

    private void Damage(){
        for(int i = 0; i<handl.gameObject.size(); i++){

            GameObject tempGameObject =handl.gameObject.get(i);

            if(tempGameObject.getId()==ID.Bullet){
                if(getBounds().intersects(tempGameObject.getBounds())){
                    hp-=25;
                    if(hp==0){
                        handl.removeObject(this);
                        game.kills+=1;

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
