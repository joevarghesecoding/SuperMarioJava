import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class GameArea extends Canvas implements Runnable{

    //Dimensions
    public static final int WIDTH = 1080;
    public static final int HEIGHT = 772;
    public static final String TITLE = "Super Mario Game";


    //Necessary static objects
    public static GameEngine gameEngine;
    public static GameLauncher launcher;

    public static int coins = 0;
    public static int lives = 3;
    public static int deathScreenTime = 0;

    public static boolean showDeathScreen = true;
    public static boolean winGame = false;

    private Thread runner;
    private boolean running = false;
    public static SpriteSheet playerSheet;
    public static SpriteSheet floorSheet;
    private BufferedImage bufferedImage;
    public static Sprite powerUp;
    public static Sprite usedPowerUp;
    public static Sprite[] pipe = new Sprite[4];
    public static boolean playing = false;

    public static ImageIcon ii = new ImageIcon("/Users/jyothishvarghese/Desktop/SuperMarioFinal/src/Sprites/openingImg.png");
    public static Image img = ii.getImage();

    public static Sprite[] goomba = new Sprite[6];
    public static Sprite floor;
    public static Sprite mario[] = new Sprite[6];
    public static Sprite playerStand;
    public static Sprite coin;
    public static Sprite flag;

    public static Camera cam;

    //change here
    public void start(){
        if(running)
            return;
        running = true;
        runner = new Thread(this, "Thread");
        runner.start();
    }

    //change here
    public  void stop() throws InterruptedException {
        if(!running)
            return;
        running = false;
        try{
            runner.join();
        }catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    public GameArea(){
        Dimension size = new Dimension(WIDTH, HEIGHT);
        setPreferredSize(size);
        setMaximumSize(size);
        setMinimumSize(size);
    }

    private void initGame() throws IOException {
        gameEngine = new GameEngine();
        cam = new Camera();
        playerSheet = new SpriteSheet("/PlumberFellasSpritesheet.png");
        floorSheet = new SpriteSheet("/PlumberFellasTilesheet.png");
        launcher = new GameLauncher();

        //levelSheet = new SpriteSheet("/Level1.png");

        addKeyListener(new KeyInput());

        floor = new Sprite(floorSheet, 2,6);
        playerStand = new Sprite(playerSheet, 1, 4);
        powerUp = new Sprite(floorSheet, 2, 7);
        usedPowerUp = new Sprite(floorSheet, 1, 7);
        coin = new Sprite(floorSheet, 3, 6);
        flag = new Sprite(floorSheet, 1,2);
        for(int i = 0; i < pipe.length; i++)
        {
            if(i < 2)
            {
                //Pipe top
                pipe[i] = new Sprite(floorSheet, i+4, 8);
            }
            else
            {
                pipe[i] = new Sprite(floorSheet, i+4, 9 );
            }
        }

        for(int i = 0; i < mario.length; i++)
        {
            mario[i] = new Sprite(playerSheet, i+1, 5);
        }

        for(int i = 0; i < goomba.length; i++)
        {
            goomba[i] = new Sprite(playerSheet, i+1, 17);
        }

        bufferedImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Level1.png")));

        //gameEngine.createLevel(bufferedImage);
    }

    public static void main(String[] args) {
        GameArea ga = new GameArea();
        JFrame frame = new JFrame(TITLE);
        frame.add(ga);
        frame.pack();
        frame.setSize(new Dimension(WIDTH , HEIGHT));
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        ga.start();
    }




    @Override
    public void run() {
        try {
            initGame();
        } catch (IOException e) {
            e.printStackTrace();
        }

        while(running)
        {
            render();

            tick();
        }
        try {
            stop();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void render()
    {
        BufferStrategy bs = getBufferStrategy();
        if(bs == null)
        {
            createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.setColor(new Color(92,148,252));
        g.fillRect(0,0, getWidth(), getHeight());
        if(!showDeathScreen)
            g.drawImage(coin.getBufferedImage(), 1000, 20, 25, 25, null);
        if(showDeathScreen)
        {
            gameEngine.clearLevel();
            g.setColor(Color.BLACK);
            g.fillRect(0,0, getFrameWidth(), getFrameHeights());
            g.setColor(Color.WHITE);
            g.setFont(new Font("Dialog", Font.BOLD, 50));
            g.drawString("YOU DIED", 500, 500);

            gameEngine.createLevel(bufferedImage);
        }

        if(winGame)
        {
            gameEngine.clearLevel();
            g.setColor(Color.BLACK);
            g.fillRect(0,0, getFrameWidth(), getFrameHeights());
            g.setColor(Color.WHITE);
            g.setFont(new Font("Dialog", Font.BOLD, 50));
            g.drawString("CONGRATULATIONS!", 250, 250);
            g.drawString("YOU WIN!", 250, 300);
        }

        g.setColor(Color.WHITE);
        g.setFont(new Font("Dialog", Font.BOLD, 20));
        g.drawString("x" + coins, 1035, 40);

        if(playing) g.translate(cam.getX(), cam.getY());
        if(!showDeathScreen&&playing) gameEngine.render(g);
        else if(!playing) launcher.render(g);
        g.dispose();
        bs.show();
    }

    public void tick()
    {
        if(playing) gameEngine.tick();

        for(Character e : gameEngine.character)
        {
            if(e.getId() == Id.player)
            {
               // if(!e.goingDownPipe)
                    cam.tick(e);
            }
        }

        if(showDeathScreen) deathScreenTime++;
        if(deathScreenTime >= 180)
        {
            showDeathScreen = false;
            deathScreenTime = 0;
            gameEngine.clearLevel();
            gameEngine.createLevel(bufferedImage);
        }

//        for(int i = 0; i < gameEngine.entity.size(); i++)
//        {
//            Entity e = gameEngine.entity.get(i);
//            if(e.getId() == Id.player)
//            {
//                cam.tick(e);
//            }
//        }
    }

    public static int getFrameWidth()
    {
        return WIDTH ;
    }

    public static int getFrameHeights()
    {
        return HEIGHT;
    }

}
