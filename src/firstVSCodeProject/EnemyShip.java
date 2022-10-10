package firstVSCodeProject;

import java.awt.Graphics2D;
import java.awt.Rectangle;
//import java.awt.event.KeyEvent;

/**
 * @author R4YL0
 */

public class EnemyShip {
    int[] xPoints = new int[4]; int[] yPoints = new int[4];
    int x = (int) Math.floor(Math.random()*370+15); int y;
    int dy = 1;
    int shootTimer = 150;
    int enCollInt;
    SpaceInvaders game;

    public EnemyShip(SpaceInvaders game) {
        this.game = game;
        xPoints[0] = x; xPoints[1] = x-14; xPoints[2] = x-28; xPoints[3] = x;
        yPoints[0] = y; yPoints[1] = y+28; yPoints[2] = y; yPoints[3] = y;
        checkIntersection();
    }

    public void checkIntersection() {
        for(int i = 0; i<game.ecnt; i++) {
            if(this == game.enemyship[i]) {
                if(collision3(i)) {
                    x = (int) Math.floor(Math.random()*370+15);
                    xPoints[0] = x; xPoints[1] = x-14; xPoints[2] = x-28; xPoints[3] = x;
                    yPoints[0] = y; yPoints[1] = y+28; yPoints[2] = y; yPoints[3] = y;
                    checkIntersection();
                    return;
                }
                return;
            }

        }
    }

    public void move() {
        shootTimer++;
        for(int k = 0;k<=3;k++)
            yPoints[k] = yPoints[k] + dy;
        if(shootTimer % 250 == 0) {
            for(int count = 0; count < game.acnt; count++) {
                if(game.enemyAmmo[count].y_now >= 600) {
                    game.enemyAmmo[count].shoot(xPoints[1],yPoints[1]);
                    return;
                }
            }
        }

        if(collision1()) {
            spawnEnemyShip();
        }
        if(collision2())
            game.gameOver();

        if(yPoints[1]>575) {
            spawnEnemyShip();
        }
    }

    public void paint(Graphics2D g2d)
    { g2d.fillPolygon(xPoints, yPoints, 3); }

    public Rectangle getBounds()
    { return new Rectangle(xPoints[2],yPoints[0],28,28); }

    public void spawnEnemyShip() {
        x = (int) Math.floor(Math.random()*370+15); y = -78;
        xPoints[0] = x; xPoints[1] = x-14; xPoints[2] = x-28; xPoints[3] = x;
        yPoints[0] = y; yPoints[1] = y+28; yPoints[2] = y; yPoints[3] = y;
    }

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

    public boolean collision3(int k) {
        for(int i = 0; i< k; i++) {
            if(game.enemyship[i] != this && game.enemyship[i].getBounds().intersects(getBounds())) {
                enCollInt = i;
                return true;
            }
        }
        return false;
    }
}
