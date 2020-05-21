import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Keyinput extends KeyAdapter {

    Handl handl;

    public Keyinput(Handl handl) {
        this.handl=handl;
    }

    public void keyPressed(KeyEvent e){
    int key =e.getKeyCode();

    for(int i = 0; i<handl.gameObject.size(); i++){
        GameObject tempGameObject =handl.gameObject.get(i);

       if(tempGameObject.getId()==ID.Player){
            if(key==KeyEvent.VK_W) handl.setUp(true);
            if(key==KeyEvent.VK_S) handl.setDown(true);
            if(key==KeyEvent.VK_A) handl.setLeft(true);
            if(key==KeyEvent.VK_D) handl.setRight(true);
        }
    }
    }

    public void keyReleased(KeyEvent e){
        int key =e.getKeyCode();

        for(int i = 0; i<handl.gameObject.size(); i++){
            GameObject tempGameObject =handl.gameObject.get(i);

            if(tempGameObject.getId()==ID.Player){
                if(key==KeyEvent.VK_W) handl.setUp(false);
                if(key==KeyEvent.VK_S) handl.setDown(false);
                if(key==KeyEvent.VK_A) handl.setLeft(false);
                if(key==KeyEvent.VK_D) handl.setRight(false);
            }
        }
    }


}
