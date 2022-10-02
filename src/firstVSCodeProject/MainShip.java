package firstVSCodeProject;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

/**
 * @author R4YL0
 */

public class MainShip {
    int[] xPoints = new int[4]; int[] yPoints = new int[4];
    int dx; int dy;
    int speed = 2;
    SpaceInvaders game;

    public MainShip(SpaceInvaders game, int x, int y) {
        this.game = game;
        xPoints[0] = x; xPoints[1] = x+14; xPoints[2] = x+28; xPoints[3] = x;
        yPoints[0] = y; yPoints[1] = y-28; yPoints[2] = y; yPoints[3] = y;
    }

    public void move() {
        if(dx != 0 || dy != 0) {
            for(int k = 0;k<=3;k++) {
                if((xPoints[k] > (14*k % 42)-10 && dx<0) || (xPoints[k] < 410 - 42 + (14*k % 42) && dx>0))
                    xPoints[k] = xPoints[k] + dx*speed;
                yPoints[k] = yPoints[k] + dy*speed;
            }
        }
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SHIFT || e.getKeyCode() == KeyEvent.VK_CONTROL)
            speed = 2;
        if(e.getKeyCode() == KeyEvent.VK_LEFT && xPoints[0]>-10 && dx == -2)
            dx = 0;
        if(e.getKeyCode() == KeyEvent.VK_RIGHT && xPoints[2]<410 && dx == 2)
            dx = 0;
        if(e.getKeyCode() == KeyEvent.VK_UP && dy == -2)
            dy = 0;
        if(e.getKeyCode() == KeyEvent.VK_DOWN && dy == 2)
            dy = 0;
    }
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SHIFT)
            speed = 4;
        if(e.getKeyCode() == KeyEvent.VK_CONTROL)
            speed = 1;
        if(e.getKeyCode() == KeyEvent.VK_LEFT && xPoints[0]>-10)
            dx = -1;
        if(e.getKeyCode() == KeyEvent.VK_RIGHT && xPoints[2]<410)
            dx = 1;
        if(e.getKeyCode() == KeyEvent.VK_UP)
            dy = -1;
        if(e.getKeyCode() == KeyEvent.VK_DOWN)
            dy = 1;
    }

    public int xPosition()
    { return xPoints[1]; }
    public int yPosition()
    { return yPoints[1]; }

    public void paint(Graphics2D g2d)
    { g2d.fillPolygon(xPoints, yPoints, 3); }
    public Rectangle getBounds()
    { return new Rectangle(xPoints[0],yPoints[1],28,28); }
}