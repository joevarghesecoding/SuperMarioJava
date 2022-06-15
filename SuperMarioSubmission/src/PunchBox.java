import java.awt.*;

public class PunchBox extends Block{
    private Sprite powerUp;
    private boolean poppedUp = false;

    private int spriteY = getY();

    public PunchBox(int x, int y, int width, int height, boolean solid, Id id, GameEngine gameEngine, Sprite powerUp) {
        super(x, y, width, height, solid, id, gameEngine);
        this.powerUp = powerUp;
    }

    @Override
    public void render(Graphics g) {
        if(!poppedUp)
            g.drawImage(powerUp.getBufferedImage(), x, spriteY, width, height,null);
        if(!activated)
            g.drawImage(GameArea.powerUp.getBufferedImage(), x, y, width, height, null);
        else
            g.drawImage(GameArea.usedPowerUp.getBufferedImage(), x ,y , width, height, null);
    }

    @Override
    public void tick() {
        if(activated&&!poppedUp)
        {
            //spriteY--;
//            if(spriteY <= y - height){
            if(spriteY <= height){
                //point coin code here
                gameEngine.addBlock(new Coin(x,y-64, 64, 64, true, Id.coin, gameEngine));
            }
        }
    }
}
