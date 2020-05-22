package repository;

import controller.Render;
import entity.GameObject;
import org.springframework.stereotype.Repository;

import java.awt.*;
import java.util.LinkedList;
@Repository
public class Handl implements Render {

   public LinkedList<GameObject> gameObject = new LinkedList<GameObject>();
    private  boolean up=false, down=false, right=false, left =false;
    public void tick(){
        for(int i = 0; i< gameObject.size(); i++){
            GameObject tempGameObject = gameObject.get(i);
            tempGameObject.tick();
        }
    }

    public void render(Graphics g){

        for(int i = 0; i< gameObject.size(); i++){
            GameObject tempGameObject = gameObject.get(i);

            tempGameObject.render(g);
        }
    }
    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isUp() {
        return up;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }



    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void addObject(GameObject tempGameObject){
        gameObject.add(tempGameObject);
    }

    public void removeObject(GameObject tempGameObject){
        gameObject.remove(tempGameObject);
    }



}
