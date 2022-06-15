import java.awt.*;

public class Mario extends Character {
    private int frame = 0;
    private int frameDelay = 0;

    private int pixelTraveled = 0;

    //private boolean animate = false;


    public Mario(int x, int y, int width, int height, Id id, GameEngine gameEngine) {
        super(x, y, width, height, id, gameEngine);
    }

    @Override
    public void render(Graphics g) {
        if(facing == 1)
        {
            g.drawImage(GameArea.mario[frame].getBufferedImage(), x, y, width, height, null);
        }
        else if(facing == 0)
        {
            g.drawImage(GameArea.playerStand.getBufferedImage(), x, y, width, height, null);
        }

    }

    @Override
    public void tick() {
        x+= dx;
        y+= dy;

        if(goingDownPipe)
        {
            pixelTraveled +=dy;
        }

//        if(x <= 0) x = 0;
        //if(y <= 0) y = 0;

//        if(x + width >= 1080) x = 1080 - width;
//        if(y + height >= 771) y = 771 - height;

        //animate = dx != 0;

        try {
            for (Block b : gameEngine.block) {
                if (!b.solid)
                    break;
                if (b.isSolid()) {
                    if (getBoundsTop().intersects(b.getBounds()) && b.getId() != Id.coin) {
                        setDy(0);
                        if (jumping) {
                            jumping = false;
                            //System.out.println(jumping);
                            gravity = 0.8;
                            falling = true;
                        }
                        //y = t.getY() + t.height;

                        if (b.getId() == Id.powerUp) {
                            if (getBoundsTop().intersects(b.getBounds())) b.activated = true;
                            System.out.println("hit power up");
                        }
                    }
                    if (getBoundsBottom().intersects(b.getBounds()) && b.getId() != Id.coin) {
                        setDy(0);
                        //y = t.getY() - t.height;
                        if (falling)
                            falling = false;
                        else {
                            if (!falling && !jumping) {
                                gravity = 0.0;
                                //System.out.println(jumping);
                                falling = true;
                            }
                        }
                    }
                    if (getBoundsLeft().intersects(b.getBounds()) && b.getId() != Id.coin) {
                        setDx(0);
                        x = b.getX() + b.width;
                        //System.out.println("x :" + x);
                    }
                    if (getBoundsRight().intersects(b.getBounds()) && b.getId() != Id.coin) {
                        setDx(0);
                        x = b.getX() - b.width;
                        //System.out.println("x :" + x);
                    }
                    if (getBounds().intersects(b.getBounds()) && b.getId() == Id.coin) {
                        GameArea.coins++;
                        b.die();
                    }
                    if (getBounds().intersects(b.getBounds()) && b.getId() == Id.flag) {
                        GameArea.winGame = true;
                    }
                }
            }
        } catch (Exception e)
        {
            e.getMessage();
        }

        for(int i =0; i < gameEngine.character.size(); i++)
        {
            Character ch = gameEngine.character.get(i);

            if(ch.getId() == Id.goomba)
            {
                if(getBoundsBottom().intersects(ch.getBoundsTop()))
                {
                    ch.die();
                }
                else if(getBounds().intersects(ch.getBounds())){
                    die();
                }
            }
        }


        if(jumping)
        {
            gravity -= 0.1;
            setDy((int)-gravity);
            if(gravity <= 0.0)
            {
                jumping = false;
                falling = true;
            }
        }
        if(falling)
        {
            gravity += 0.1;
            setDy((int)gravity);
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

//        if(goingDownPipe) {
//            for(int i = 0; i< GameArea.handler.tile.size(); i++)
//            {
//                Block b = GameArea.gameEngine.block.get(i);
//                if(b.getId() == Id.pipe)
//                {
//                    if(getBoundsBottom().intersects(b.getBounds()))
//                    {
//                        switch(t.facing) {
//                            case 0:
//                                setDy(-5);
//                                setDx(0);
//                                break;
//                            case 2:
//                                setDy(5);
//                                setDx(0);
//                                break;
//                        }
//                        if(pixelTraveled > t.height + height) goingDownPipe = false;
//                    }
//
//                }
//            }
//        }

    }
}
