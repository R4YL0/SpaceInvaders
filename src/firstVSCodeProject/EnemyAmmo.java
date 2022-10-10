package firstVSCodeProject;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public class EnemyAmmo {
    int x_now; double y_now;
    SpaceInvaders game;

    public EnemyAmmo(SpaceInvaders game, int x, int y) {
        this.game = game;
        x_now = x-14; y_now = y+28;
    }

    public void move() {
        y_now = y_now+1.3;
        if(collision1()) {
            x_now = 600;
            y_now = 400;
        }
        if(collision2()) {
            game.gameOver();
        }
    }



    public void shoot(int x, int y) {
        x_now = x-3; y_now = y;
    }

    public void paint(Graphics2D g2d)
    { g2d.fillRect(x_now, (int) y_now, 7, 7); }

    public Rectangle getBounds()
    { return new Rectangle(x_now,(int) y_now,7,7); }
    public Rectangle getBoundsAmmo()
    { return new Rectangle(x_now,(int) y_now,16,16); }

    public boolean collision1() {
        for(int count = 0; count < game.acnt ; count++) {
            if(game.ammo[count].getBoundsAmmo().intersects(getBoundsAmmo())) {
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
