import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter {
    private Handl handl;
    private Camera camera;
    private Game game;
    private SpriteSheet ss;
    public MouseInput(Handl handl,Camera camera,Game game,SpriteSheet ss){
        this.handl=handl;
        this.camera=camera;
        this.game=game;
        this.ss=ss;
    }
    public void mousePressed(MouseEvent e){
        //2d.drawImage( play,400,220,null);
        //g2d.drawImage( exit,400,350,null);
        if(Game.id==ID.Menu) {
            if (e.getX() >= 450 - 100 && e.getX() <= 500 + 100) {
                if (e.getY() >= 320 - 100 && e.getY() <= 200 + 100) {
                    Game.id = ID.Game;
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

            if (tempGameObject.getId()==ID.Player && game.ammo>0){
                handl.addObject((new Bullet(tempGameObject.getX()+16, tempGameObject.getY()+24,ID.Bullet,handl,mx,my,ss)));
                game.ammo--;
            }
        }
    }
}
