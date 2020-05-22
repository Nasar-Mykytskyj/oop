package controller;

import entity.GameObject;
import entity.ID;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import repository.Handl;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
@Component

@Scope(value = "singleton")
public class Keyinput extends KeyAdapter {

    Handl handl;
    ApplicationContext applicationContext;
    public Keyinput( ApplicationContext applicationContext) {
        this.applicationContext=applicationContext;
        handl=(Handl)applicationContext.getBean("handl") ;

    }

    public Keyinput() {
    }

    public void keyPressed(KeyEvent e){
    int key =e.getKeyCode();

    for(int i = 0; i<handl.gameObject.size(); i++){
        GameObject tempGameObject =handl.gameObject.get(i);

       if(tempGameObject.getId()== ID.Player){
            if(key== KeyEvent.VK_W) handl.setUp(true);
            if(key== KeyEvent.VK_S) handl.setDown(true);
            if(key== KeyEvent.VK_A) handl.setLeft(true);
            if(key== KeyEvent.VK_D) handl.setRight(true);
        }
    }
    }

    public void keyReleased(KeyEvent e){
        int key =e.getKeyCode();

        for(int i = 0; i<handl.gameObject.size(); i++){
            GameObject tempGameObject =handl.gameObject.get(i);

            if(tempGameObject.getId()==ID.Player){
                if(key== KeyEvent.VK_W) handl.setUp(false);
                if(key== KeyEvent.VK_S) handl.setDown(false);
                if(key== KeyEvent.VK_A) handl.setLeft(false);
                if(key== KeyEvent.VK_D) handl.setRight(false);
            }
        }
    }


}
