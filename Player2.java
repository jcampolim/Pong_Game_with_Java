import java.awt.Color;
import java.awt.Graphics;

public class Player2 {
    public boolean pleft, pright;

    public int x, y;
    public int width, height;

    public Player2(int x, int y) {
        this.x = x;
        this.y = y;
        this.width = 40;
        this.height = 5;
    }

    public void tick() {
        if(pright) {
            x++;
        } else if(pleft) {
            x--;
        }

        if(x + width > Game.WIDTH){
            x = Game.WIDTH - width;
        } else if(x < 0) {
            x = 0;
        }
    }

    public void render(Graphics g) {
        g.setColor(Color.blue);
        g.fillRect(x, y, width, height);
        g.setColor(Color.white);
        g.fillRect(0, Game.HEIGHT/2, Game.WIDTH, 1);
    }
}
