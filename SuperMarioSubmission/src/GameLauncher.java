import java.awt.*;

public class GameLauncher {
    public Buttons[] buttons;

    public GameLauncher()
    {
        buttons = new Buttons[2];

        buttons[0] = new Buttons(100, 150, 200, 100, "Press Enter to start" );
        buttons[1] = new Buttons(100, 250, 200, 100, "Press Exit to close");
    }

    public void render(Graphics g)
    {
        g.setColor(Color.BLACK);
        g.fillRect(0,0, GameArea.getFrameWidth(), GameArea.getFrameHeights());
        g.setColor(Color.WHITE);
        g.setFont(new Font("Dialog", Font.BOLD, 50));
        g.drawString("Welcome to Joe's", 500, 150);
        g.drawImage(GameArea.img, 500, 250, 400,350, null);

        for(int i = 0; i < buttons.length; i++)
        {
            buttons[i].render(g);
        }
    }
}
