import java.awt.*;
import java.util.Random;

public class Goomba extends Character {

    private int frame = 0;
    private int frameDelay = 0;

    public Goomba(int x, int y, int width, int height, Id id, GameEngine gameEngine) {
        super(x, y, width, height, id, gameEngine);

        setDx(-1);
        facing = 0;

    }

    @Override
    public void render(Graphics g) {
        if(facing == 1)
        {
            g.drawImage(GameArea.goomba[frame].getBufferedImage(), x, y, width, height, null);
        }
        else if(facing == 0)
        {
            g.drawImage(GameArea.goomba[frame].getBufferedImage(), x, y, width, height, null);
        }
    }

    @Override
    public void tick() {
        x += dx;
        y += dy;

        for(int i = 0; i < gameEngine.block.size(); i++){
            Block t = gameEngine.block.get(i);
            if(t.isSolid())
            {
                if(getBoundsBottom().intersects(t.getBounds())) {
                    setDy(0);
                    if(falling) falling = false;
                } else if (!falling) {
                    falling = true;
                    gravity = 0.8;
                }
                if(getBoundsLeft().intersects(t.getBounds())){
                    setDx(1);
                    facing = 1;
                }
                if(getBoundsRight().intersects(t.getBounds()))
                {
                    setDx(-1);
                    facing = 0;
                }
            }
        }

        if(falling)
        {
            gravity += 0.1;
            setDy((int) gravity);
        }

        if(dx != 0)
        {
            frameDelay++;
            if(frameDelay>=10)
            {
                frame++;
                if(frame > 3)
                {
                    frame = 0;
                }
                frameDelay = 0;
            }
        }
    }
}
