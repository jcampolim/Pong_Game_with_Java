import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable, KeyListener{
    private static final long serialVersionUID = 1L;
    public static int WIDTH = 160;
    public static int HEIGHT = 120;
    public static int SCALE = 3;

    public BufferedImage layer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

    public static Player2 player2;
    public static Player1 player1;
    public static Ball ball;
    public static Points points;

    public boolean start = false;
    public static boolean cont = false;

    public Game() {
        this.setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
        this.addKeyListener(this);
        player2 = new Player2(60, HEIGHT-5);
        player1 = new Player1(60, 0);
        ball = new Ball(WIDTH/2, HEIGHT/2 -1);
        points = new Points();
    }

    public static void main(String[]args) {
        Game game = new Game();
        JFrame frame = new JFrame("Pong");
        frame.setResizable(false); // Redimencionável ou não
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(game);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        new Thread(game).start();
    }

    public void tick() {
        player2.tick();
        player1.tick();
        ball.tick();
    }

    public void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = layer.getGraphics();
        g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        player2.render(g);
        player1.render(g);
        ball.render(g);
        points.render(g);

        g = bs.getDrawGraphics();
        g.drawImage(layer, 0, 0, WIDTH*SCALE, HEIGHT*SCALE, null);

        bs.show();
    }

    @Override
    public void run() {
        while(true) {
            render();
            while(start) {
                if(Ball.p2 == 5 || Ball.p1 == 5) {
                    render();
                    start = false;
                }
                render();
                tick();
                try {
                    Thread.sleep(1000/60);
                } catch(InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER) {
            start = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            player2.pright = true;
        } else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            player2.pleft = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_D) {
            player1.eright = true;
        } else if(e.getKeyCode() == KeyEvent.VK_A) {
            player1.eleft = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER) {
            start = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            player2.pright = false;
        } else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            player2.pleft = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_D) {
            player1.eright = false;
        } else if(e.getKeyCode() == KeyEvent.VK_A) {
            player1.eleft = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }
}
