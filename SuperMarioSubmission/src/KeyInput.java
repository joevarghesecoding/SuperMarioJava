import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInput implements KeyListener {
    @Override
    public void keyTyped(KeyEvent e) {
        //Not used
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        for(Character ch: GameArea.gameEngine.character)
        {
            if(ch.getId() == Id.player)
            {
                switch(key)
                {
                    case KeyEvent.VK_W:
                        if(!ch.jumping)
                        {
                            ch.jumping = true;
                            //System.out.println("ch:Jumping");
                            ch.gravity = 10.0;
                            //System.out.println("gravity " + ch.gravity);
                        } break;
                    case KeyEvent.VK_S:
                        for(int i = 0; i < GameArea.gameEngine.block.size(); i++)
                        {
                            Block t = GameArea.gameEngine.block.get(i);
//                            if(t.getId() == Id.pipe)
//                            {
//                                if(ch.getBoundsBottom().intersects(t.getBounds())) {
//                                    if(!ch.goingDownPipe)
//                                        ch.goingDownPipe = true;
//                                }
//                            }
                        }
                        break;
                    case KeyEvent.VK_A:
                        ch.setDx(-2);
                        ch.facing = 1;
                        break;
                    case KeyEvent.VK_D:
                        ch.setDx(2);
                        ch.facing= 1;
                        break;
                }
            }

        }
        if(key == KeyEvent.VK_ENTER)
        {
            GameArea.playing = true;
        }
        if(key == KeyEvent.VK_ESCAPE)
        {
            System.exit(0);
        }


    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        for(Character ch: GameArea.gameEngine.character)
        {
            if(ch.getId() == Id.player)
            {
                switch(key)
                {
                    case KeyEvent.VK_W:
                        ch.setDy(0);
                        break;
                    case KeyEvent.VK_S:
                        ch.setDy(0);
                        break;
                    case KeyEvent.VK_A:
                        ch.setDx(0);
                        break;
                    case KeyEvent.VK_D:
                        ch.setDx(0);
                        break;
                }
            }

        }
    }
}
