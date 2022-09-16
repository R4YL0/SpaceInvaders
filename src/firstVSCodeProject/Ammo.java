package firstVSCodeProject;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.Rectangle;

/**
 *
 * @author R4YL0
 */

public class Ammo {
    int x_now; int y_now;
    SpaceInvaders game;
    
    public Ammo(SpaceInvaders game, int x, int y) {
        this.game = game;
        x_now = x+14; y_now = y-28;
    }

    public void move()
    { y_now = y_now-3; }

    public void keyPressed(KeyEvent e, int a, int b) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE && y_now <= 0)
            { x_now = a-2; y_now = b; }
    }

    public void paint(Graphics2D g2d)
    { g2d.fillRect(x_now, y_now, 4, 15); } 

    public Rectangle getBounds()
    { return new Rectangle(x_now,y_now,4,15); }
}
