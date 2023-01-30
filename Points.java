import java.awt.Graphics;
import java.awt.Color;

public class Points {
    public static String p11;
    public static String p22;

    public Points() {
        p11 = ""+Ball.p1;
        p22 = ""+Ball.p2;
    }

    public void render(Graphics g) {
        g.drawString(p11, 10, (Game.HEIGHT/2)-5);
        g.drawString(p22, 10, (Game.HEIGHT/2)+15);

        
        if(Ball.p1 == 5) {
            g.setColor(Color.red);
            g.drawString("Red won", 55, 40);
        }
        if(Ball.p2 == 5) {
            g.setColor(Color.blue);
            g.drawString("Blue won", 53, 90);
        }
        
    }
}
