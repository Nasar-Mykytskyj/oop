package entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component

@Scope(value = "singleton")
public class Pokaznyky {
    private int hp;
    private int bulletNumber;
    private int killsNumber;


    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getBulletNumber() {
        return bulletNumber;
    }

    public void setBulletNumber(int bulletNumber) {
        this.bulletNumber = bulletNumber;
    }

    public int getKillsNumber() {
        return killsNumber;
    }

    public void setKillsNumber(int killsNumber) {
        this.killsNumber = killsNumber;
    }

    public Pokaznyky() {
        this.hp = 100;
        this.bulletNumber = 100;
        this.killsNumber = 0;
    }
}
