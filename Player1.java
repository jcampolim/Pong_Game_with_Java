import java.awt.Color;
import java.awt.Graphics;

public class Player1 {
    public boolean eleft, eright;

    public int x, y;
    public int width, height;

    public Player1(int x, int y) {
        this.x = x;
        this.y = y;
        this.width = 40;
        this.height = 5;
    }

    public void tick() {
        if(eright) {
            x++;
        } else if(eleft) {
            x--;
        }

        if(x + width > Game.WIDTH){
            x = Game.WIDTH - width;
        } else if(x < 0) {
            x = 0;
        }
    }

    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(x, y, width, height);
    }
}
