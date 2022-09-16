package firstVSCodeProject;

import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author R4YL0
 */

public class CometSplit {
    double x = 0; double y = 0;
    int durchmesser;
    double dx = 0; double dy = 0;
    boolean split = false;
    boolean wait = false;
    SpaceInvaders game;

    public CometSplit(SpaceInvaders game)
    { this.game = game; }
    
    public void move() {
        if(collision1()) {
            x = -20; y = -20;
            dx = 0; dy = 0;
        }
        else {
            x = x+dx; y = y+dy;
        }
        if(collision2())
            { game.gameOver(); }
        for(int count = 0; count < game.ccnt; count++) {
            if(game.comets[count] && this == game.split[count]) {
                wait = false;
                return;
            }
        }
        if(x<-50)
            { x = 400; }
        if(x>400)
            { x = -50; }
        if(y>575) {
            x = -20; y = -20;
            dx = 0; dy = 0;
            wait = true;
        }
    }

    public void paint(Graphics2D g2d)
    { g2d.fillOval((int)x, (int)y, durchmesser, durchmesser); }
    
    public Rectangle getBounds()
    { return new Rectangle((int)x,(int)y,durchmesser, durchmesser); }

    public boolean collision1() {
        for(int count = 0; count < game.acnt ; count++) {
            if(game.ammo[count].getBounds().intersects(getBounds())) {
                split = true;
                game.ammo[count].x_now = 0; game.ammo[count].y_now = 0;
                wait = true;
                break;
            }
        }
        return split;
    }

    public boolean collision2()
    { return game.mainship.getBounds().intersects(getBounds()); }
}
