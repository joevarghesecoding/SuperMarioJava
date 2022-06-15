public class Camera {

    public int x, y;

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

    public void tick(Character mario)
    {
        setX(-mario.getX() + GameArea.getFrameWidth()/2);
        setY(-mario.getY() + GameArea.getFrameWidth()/3);
    }

}
