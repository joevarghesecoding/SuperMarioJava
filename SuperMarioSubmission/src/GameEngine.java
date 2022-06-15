import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class GameEngine {

    public LinkedList<Character> character = new LinkedList<Character>();
    public LinkedList<Block> block = new LinkedList<Block>();

    public void render(Graphics g)
    {
        for(Character ch: character) {
            ch.render(g);
        }
        for(Block bl:block)
        {
            bl.render(g);
        }

    }

    public void tick()
    {


        for(int i = 0; i < character.size(); i++) {
            Character c = character.get(i);
            c.tick();
        }


        for(int i = 0; i < block.size(); i++) {
            Block bl = block.get(i);
            bl.tick();
        }

    }

    public void addCharacter(Character ch)
    {
        try {
            character.add(ch);
        } catch (Exception e)
        {
            e.getMessage();
        }
    }

    public void removeCharacter(Character ch)
    {
        try {
            character.remove(ch);
        } catch (Exception e)
        {
            e.getMessage();
        }
    }

    public void addBlock(Block bl)
    {
        try{
            block.add(bl);
        } catch (Exception e)
        {
            e.getMessage();
        }
    }

    public void removeBlock(Block bl)
    {
        try{
            block.remove(bl);
        } catch (Exception e)
        {
            e.getMessage();
        }
    }

    public void clearLevel()
    {
        character.clear();
        block.clear();
    }

    public void createLevel(BufferedImage level)
    //public void createLevel()
    {
//        for(int i = 0; i < Game.WIDTH*Game.SCALE/64 + 1; i++)
//        {
//            addTile(new Wall(i * 64, Game.HEIGHT*Game.SCALE-64, 64,64, true, Id.wall, this));
//            if(i != 0 && i != 1 && i != 15 && 1 != 17) addTile(new Wall(i * 64, 300, 64,64, true, Id.wall, this));
//        }

        int width = level.getWidth();
        int height = level.getHeight();
//        System.out.println("level.getWidth() " + level.getWidth());
//        System.out.println("level.getHeight() " + level.getHeight());

        for(int y = 0; y < height; y++)
        {
            for(int x = 0; x < width; x++)
            {
                int pixel = level.getRGB(x,y);
                //System.out.println("pixcel found " + pixel);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;
                //WALL
                if(red == 0 && green == 0 && blue == 0)
                {
                    addBlock(new Wall(x*64, y * 64, 64, 64, true, Id.wall, this));
                    //System.out.println("WALL DETECTED");
                }
                //MARIO
                if(red == 0 && green == 0 && blue == 255){
                    addCharacter(new Mario(x*64, y * 64, 64, 64,  Id.player, this));
                    //System.out.println("MARIO DETECTED");
                }
                //GOOMBA
                if(red==255 && green == 0 && blue == 0){
                    addCharacter(new Goomba(x*64, y * 64, 64, 64,  Id.goomba, this));
                }
                //POWERUP
                if(red==255 && green == 255 &&blue ==0)
                {
                    addBlock(new PunchBox(x*64, y*64, 64, 64, true, Id.powerUp, this, GameArea.powerUp));
                    //System.out.println("power up detected");
                }
                //COIN
                if(red==0&&(green == 255) &&blue==0)
                {
                    addBlock(new Coin(x*64,y*64, 64, 64, true, Id.coin, this));
                    //System.out.println("coin detected");
                }
                //WIN FLAG
                if(red==255 && green == 0 && blue == 255)
                {
                    addBlock(new Flag(x*64, y * 64, 64, 64, true, Id.flag, this));
                    // System.out.println("flag detected");
                }

            }
        }

    }


}
