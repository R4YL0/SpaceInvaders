package firstVSCodeProject;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

/**
 *
 * @author R4YL0
 */

public class Masnip {
    int[] xPoints = new int[4]; int[] yPoints = new int[4];
    int dx; int dy;
    SpaceInvaders game;

    public Masnip(SpaceInvaders game, int x, int y) {
        this.game = game;
        xPoints[0] = x; xPoints[1] = x+14; xPoints[2] = x+28; xPoints[3] = x;
        yPoints[0] = y; yPoints[1] = y-28; yPoints[2] = y; yPoints[3] = y;
    }

    public void move() {
        if(dx != 0 || dy != 0) {
            for(int k = 0;k<=3;k++) {
                if((xPoints[k] > 0 + (14*k % 42) && dx<0) || (xPoints[k] < 400 - 42 + (14*k % 42) && dx>0))
                    { xPoints[k] = xPoints[k] + dx; }
                yPoints[k] = yPoints[k] + dy;
            }
        }
    }

    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_LEFT && xPoints[0]>0 && dx == -2)
            { dx = dx + 2; }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT && xPoints[2]<400 && dx == 2)
            { dx = dx - 2; }
        if(e.getKeyCode() == KeyEvent.VK_UP && dy == -2)
            { dy = dy + 2; }
        if(e.getKeyCode() == KeyEvent.VK_DOWN && dy == 2)
            { dy = dy - 2; }
    }
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_LEFT && xPoints[0]>0)
            { dx = -2; }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT && xPoints[2]<400)
            { dx = 2; }
        if(e.getKeyCode() == KeyEvent.VK_UP)
            { dy = -2; }
        if(e.getKeyCode() == KeyEvent.VK_DOWN)
            { dy = 2; }
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