import java.awt.*;

public class Bullet extends GameObject {

    public Handl handl;

    public Bullet(int x, int y, ID id, Handl handl,int mx,int my,SpriteSheet ss) {
        super(x, y, id,ss);
        this.handl = handl;



        velx=(mx-x)/10;
        vely=(my-y)/10;

    }


    public void tick() {
        x += velx;
        y += vely;
        for(int i = 0; i<handl.gameObject.size(); i++){
            GameObject tempGameObject =handl.gameObject.get(i);
            if(tempGameObject.getId()==ID.Block){
                if(getBounds().intersects(tempGameObject.getBounds())) {
                    handl.removeObject(this);
                }
            }
        }
    }


    public void render(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillOval(x, y,8,8 );
    }


    public Rectangle getBounds() {
        return new Rectangle(x,y,8,8);
    }
}
