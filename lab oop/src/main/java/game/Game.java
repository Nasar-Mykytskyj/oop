package game;

import conteiner.Config;
import controller.*;
import controller.Menu;
import entity.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import repository.Handl;
import controller.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game extends Canvas implements  Runnable  {
    private  static final long serialVersionUID =1L;
    private boolean isRunning = false ;
    private Thread thread;
    private Handl handl;
    private Camera camera;
    private SpriteSheet ss;
    private BufferedImage level =null;
    private BufferedImage sprite_sheet=null;
    private BufferedImage floor=null;
    private controller.Menu menu1;



    private ID id ;

    Hero hero;
    ApplicationContext applicationContext;


    public  Game(){
        id = ID.Menu;
        applicationContext = new AnnotationConfigApplicationContext(Config.class);
        new window(1000,560 ,"first game",this);
        start();

        menu1=(Menu)applicationContext.getBean("menu") ;
        handl = (Handl)applicationContext.getBean("handl") ;
        camera=(Camera)applicationContext.getBean("camera");

        this.addKeyListener(new Keyinput(applicationContext));
        BufferedImageLoader loader = new BufferedImageLoader();
        level= loader.loadImage("/Безіменний.png");
        sprite_sheet=loader.loadImage("/sprite.png");
        ss =new SpriteSheet(sprite_sheet);

        floor=ss.grabImage(4,2,32,32);
        this.addMouseListener(new MouseInput(this,ss,applicationContext));

        loadLevel(level);



    }

    private void start(){
        isRunning =true;
        thread = new Thread(this);
        thread.start();
    }

    public void stop(){




        isRunning =false;
       try{
           thread.join();

        }
        catch(InterruptedException e){
            e.printStackTrace();
       }


    }

    public void run() {
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (isRunning) {
            long now = System.nanoTime();
            delta += (now - lastTime) /ns;
            lastTime = now;
            while(delta >= 1) {
                tick();
                delta--;
            }
            render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                frames = 0;

            }
        }
        stop();

    }

    public void tick(){
        if(id ==ID.Game) {
            for (int i = 0; i < handl.gameObject.size(); i++) {
                if (handl.gameObject.get(i).getId() == ID.Player) {
                    camera.tick(handl.gameObject.get(i));
                }
            }
            handl.tick();
        }
    }

    public void render(){
        BufferStrategy bs=this.getBufferStrategy();
        if (bs==null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        Graphics2D g2d=(Graphics2D) g;

        g2d.translate(-camera.getX(),-camera.getY());

        for(int xx=0;xx<30*72;xx+=32){
            for(int yy=0;yy<30*72;yy+=32){
                g.drawImage(floor,xx,yy,null);
            }
        }
        if(id==ID.Game) {
            try {

                handl.render(g);
                g2d.translate(camera.getX(), camera.getY());
                Font fon = new Font("arial", Font.ITALIC, 30);
                g.setFont(fon);
                g.setColor(Color.BLACK);
                g.drawString("HP: " + hero.getPokaznyky().getHp(), 5, 50);
                g.drawString("Bullet: " + hero.getPokaznyky().getBulletNumber(), 5, 100);
                g.drawString("Kills: " + hero.getPokaznyky().getKillsNumber(), 5, 150);
                if (hero.getPokaznyky().getKillsNumber() == 15) { g.setColor(Color.white);
                g.fillRect(400, 150, 200, 100);
                Font fon1 = new Font("arial", Font.ITALIC, 30);
                g.setFont(fon1);
                g.setColor(Color.BLACK);
                g.drawString("YOU WIN!!!", 420, 210);

                }


            }
            catch (NullPointerException e){

            }

        }
        else if(id==ID.Menu){


                menu1.render(g);


        }

        g.dispose();
        bs.show();
    }

    private void loadLevel(BufferedImage image){
        int w = image.getWidth();;
        int h = image.getHeight();

        for (int xx = 0; xx < w; xx++) {
            for (int yy = 0; yy < h; yy++) {
                int pixel = image.getRGB(xx, yy);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;
                if (red == 255) {
                    handl.addObject(new Block(xx * 32, yy * 32, ID.Block, ss));
                }
                if (blue == 255 && green == 0) {

                    handl.addObject(new Hero(xx * 32, yy * 32, ID.Player,  this, ss, applicationContext));
                    hero= (Hero) handl.gameObject.getLast();

                }
                if (green == 255 && blue == 0)
                    handl.addObject(new Enemy(xx * 32, yy * 32, ID.Enemy, ss,this,applicationContext));

                if (green == 255 && blue == 255)
                    handl.addObject(new Crate(xx * 32, yy * 32, ID.Crate, ss));
            }
        }

    }

    public void setId(ID id) {
        this.id = id;
    }

    public ID getId() {
        return id;
    }
















    public Hero getHero() {
        return hero;
    }

}
