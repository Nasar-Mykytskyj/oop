package controller;

import entity.GameObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component

@Scope(value = "singleton")
public class Camera {
    private float x,y;

    public Camera(float x, float y){
        this.x=x;
        this.y=y;
    }

    public Camera() {
        this.x=0;
        this.y=0;
    }

    public void tick(GameObject gameObject){
        x+=((gameObject.getX()-x)-1000/2)*0.05f;
        y+=((gameObject.getY()-y)-563/2)*0.05f;

        if(x<=0)x=0;
        if(x>=1032)x=1032;
        if(y<=0)y=0;
        if(y>=365)y=365;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
