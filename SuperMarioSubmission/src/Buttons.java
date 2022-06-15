import java.awt.*;

public class Buttons {

    public int x, y;
    public int width, height;

    public String label;

    public Buttons(int x, int y, int width, int height, String label) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.label = label;
    }

    public void render(Graphics g)
    {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Dialog", Font.BOLD, 25));
        // g.drawRect(getX(), getY(), getWidth(), getHeight());

        FontMetrics fm = g.getFontMetrics();
        int stringX = (getWidth() - fm.stringWidth(getLabel()))/2;
        int stringY = (fm.getAscent() + (getHeight() - (fm.getAscent() + fm.getDescent()))/2);
        g.drawString(getLabel(), getX() + stringX, getY() + stringY);
    }

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

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getLabel() {
        return label;
    }

}
