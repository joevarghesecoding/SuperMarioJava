import java.awt.*;

public abstract class Character {
    public int x, y;
    public int width, height;
    public int facing = 0; //0 - left, 1 - right

    public int dx, dy;

    public Id id;

    public GameEngine gameEngine;
    public boolean jumping = false;
    public boolean falling = true;
    public boolean goingDownPipe = false;

    public double gravity = 0.0;

    public Character(int x, int y, int width, int height, Id id, GameEngine gameEngine)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.id = id;
        this.gameEngine = gameEngine;
    }

    public abstract void render(Graphics g);
    public abstract void tick();

    //GETTERS AND SETTERS
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

    public Id getId() {
        return id;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    public void die()
    {
        gameEngine.removeCharacter(this);
        GameArea.lives--;
        GameArea.showDeathScreen = true;
    }

    public Rectangle getBounds()
    {
        return new Rectangle(getX(), getY(), width, height);
    }

    public Rectangle getBoundsTop()
    {
        return new Rectangle(getX() + 10,getY() + 10, width - 20, 15);
    }

    public Rectangle getBoundsBottom()
    {
        return new Rectangle(getX()+10,getY() + height -5, width - 20, 5);
    }

    public Rectangle getBoundsLeft()
    {
        return new Rectangle(getX(),getY() + 10, 5, height - 20);
    }

    public Rectangle getBoundsRight()
    {
        return new Rectangle(getX() + width - 5,getY() + 10, 5, height - 20);
    }
}
