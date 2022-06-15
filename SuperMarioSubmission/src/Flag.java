import java.awt.*;

public class Flag extends Block{
    public Flag(int x, int y, int width, int height, boolean solid, Id id, GameEngine gameEngine) {
        super(x, y, width, height, solid, id, gameEngine);
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(GameArea.flag.getBufferedImage(), x, y, width, height, null);
    }

    @Override
    public void tick() {

    }
}
