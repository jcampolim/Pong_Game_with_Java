import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Ball {

    public double x, y;
    public int width, height;

    public double dx, dy;
    public double speed = 1.7;

    public static int p1 = 0;
    public static int p2 = 0;

    public Ball(int x, int y) {
        this.x = x;
        this.y = y;
        this.width = 4;
        this.height = 4;

        int angle = new Random().nextInt(120 - 45) + 45 + 1;
        dx = Math.cos(Math.toRadians(angle));
        dy = Math.sin(Math.toRadians(angle));
    } 

    public void tick() {
        if (x + (dx * speed) + width >= Game.WIDTH) {
            dx *= -1;
        } else if (x + (dx * speed) < 0) {
            dx *= -1;
        }

        if (y >= Game.HEIGHT) {
            p1 ++;
            new Game();
            return;
        } else if (y < 0) {
            p2 ++;
            new Game();
            return;
        }

        Rectangle bounds = new Rectangle((int)(x + (dx * speed)), (int)(y + (dy * speed)), width, height);
        Rectangle boundsPlayer = new Rectangle(Game.player2.x, Game.player2.y, Game.player2.width, Game.player2.height);
        Rectangle boundsEnemy = new Rectangle(Game.player1.x, Game.player1.y, Game.player1.width, Game.player1.height);

        if (bounds.intersects(boundsPlayer)) {
            int angle = new Random().nextInt(120 - 45) + 45 + 1;
            dx = Math.cos(Math.toRadians(angle));
            dy = Math.sin(Math.toRadians(angle));
            if (dy > 0) {
                dy *= -1;
            }
        } else if (bounds.intersects(boundsEnemy)) {
            int angle = new Random().nextInt(120 - 45) + 45 + 1;
            dx = Math.cos(Math.toRadians(angle));
            dy = Math.sin(Math.toRadians(angle));
            if (dy < 0) {
                dy *= -1;
            }
        }

        x += dx * speed;
        y += dy * speed;
    }

    public void render(Graphics g) {
        g.setColor(Color.yellow);
        g.fillRect((int)x, (int)y, width, height);
    }
}