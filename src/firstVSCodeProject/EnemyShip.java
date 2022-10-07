package firstVSCodeProject;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

/**
 * @author R4YL0
 */

public class EnemyShip {
    int[] xPoints = new int[4]; int[] yPoints = new int[4];
    int x = (int) Math.floor(Math.random()*370+15); int y;
    int dy = 1;
    int eacnt = 2;
    int shootcount = 0;
    SpaceInvaders game;

    public EnemyShip(SpaceInvaders game) {
        this.game = game;
        xPoints[0] = x; xPoints[1] = x-14; xPoints[2] = x-28; xPoints[3] = x;
        yPoints[0] = y; yPoints[1] = y+28; yPoints[2] = y; yPoints[3] = y;
    }

    public void move() {
        for(int k = 0;k<=3;k++)
            yPoints[k] = yPoints[k] + dy;
        if(shootcount%10 == 0)
        {
            for(int count = 0; count < eacnt; count++) {
                if(game.enemyammo[count].y_now <= 0) {
                    game.enemyammo[count].shoot(enemyship[0].xPosition(),enemyship[0].yPosition());
                    return;
                }
            }
        }

        if(collision1()) {
            x = (int) Math.floor(Math.random()*370+15); y = -50;
            xPoints[0] = x; xPoints[1] = x-14; xPoints[2] = x-28; xPoints[3] = x;
            yPoints[0] = y; yPoints[1] = y+28; yPoints[2] = y; yPoints[3] = y;
        }
        if(collision2())
            game.gameOver();

        if(yPoints[1]>575) {
            x = (int) Math.floor(Math.random()*370+15); y = -50;
            xPoints[0] = x; xPoints[1] = x-14; xPoints[2] = x-28; xPoints[3] = x;
            yPoints[0] = y; yPoints[1] = y+28; yPoints[2] = y; yPoints[3] = y;
        }
    }
    public void paint(Graphics2D g2d)
    { g2d.fillPolygon(xPoints, yPoints, 3); }
    public Rectangle getBounds()
    { return new Rectangle(xPoints[2],yPoints[0],28,28); }

    public boolean collision1() {
        for(int count = 0; count < game.acnt ; count++) {
            if(game.ammo[count].getBounds().intersects(getBounds())) {
                game.ammo[count].x_now = 0; game.ammo[count].y_now = 0;
                game.score.score(200);
                return true;
            }
        }
        return false;
    }

    public boolean collision2()
    { return game.mainship.getBounds().intersects(getBounds()); }
    
}
