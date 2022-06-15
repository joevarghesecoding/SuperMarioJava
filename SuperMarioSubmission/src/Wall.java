import java.awt.*;

public class Wall extends Block{
    public Wall(int x, int y, int width, int height, boolean solid, Id id, GameEngine gameEngine) {
        super(x, y, width, height, solid, id, gameEngine);
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(GameArea.floor.getBufferedImage(), x, y, width, height, null);
    }

    @Override
    public void tick() {

    }
}
