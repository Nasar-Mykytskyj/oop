package entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component

@Scope(value = "prototype")
public  enum ID {
    Player(),
    Block(),
    Crate(),
    Bullet(),
    Enemy(),
    Menu(),
    Game();
}
