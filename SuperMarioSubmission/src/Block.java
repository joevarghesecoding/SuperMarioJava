import java.awt.*;

public abstract class Block {
    public int x, y;
    public int width, height;

    public int dx, dy;

    public Id id;

    public GameEngine gameEngine;
    public boolean activated = false;

    public boolean solid = false;


    public Id getId()
    {
        return id;
    }

    public Block(int x, int y, int width, int height, boolean solid, Id id, GameEngine gameEngine)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.solid = solid;
        this.id = id;
        this.gameEngine = gameEngine;
    }

    public abstract void render(Graphics g);
    public abstract void tick();

    public void die()
    {
        gameEngine.removeBlock(this);
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isSolid() {
        return solid;
    }

    public Rectangle getBounds()
    {
        return new Rectangle(getX(), getY(), width, height);
    }
}
