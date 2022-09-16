package firstVSCodeProject;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author R4YL0
 */

public class Comet {
    SpaceInvaders game;
    double x = Math.floor(Math.random()*370+15); double y = Math.floor(Math.random()*50 + 100);
    int durchmesser = 30;
    double dx;  double dy;
    boolean split = true;
    boolean wait = false;

    public Comet(SpaceInvaders game)
    { this.game = game;
        dx = Math.random()*4 - 2;
        dy = Math.random()*2/Math.sqrt(game.ccnt) + 1;
    }
    public void move() {
        if(collision1()) {
            x = Math.floor(Math.random()*370+15); y = -50;
            dx = 0; dy = 0;
        }
        else
            { x = x+dx; y = y+dy; }
        for(int count = 0; count < game.ccnt; count++) {
            if(this == game.comet[count] && game.comets[count]) {
                dx = Math.random()*4 - 2; dy = Math.random()*2/Math.sqrt(game.ccnt) + 1;
                wait = false;
            }
        }
        if(collision2())
        { game.gameOver(); }

        if(x<-50)
        { x = 400; }
        if(x>400)
        { x = -50; }
        if(y>575) {
            if(!split) {
                durchmesser = durchmesser *2;
                split = true;
                wait = true;
            }
            else {
                x = Math.floor(Math.random()*370+15); y = -50;
                dx = (Math.random()*4 - 2)/Math.sqrt(2*game.ccnt);
            }
        }
    }

    public void split() {
        if(split) {
            durchmesser = durchmesser/2;
            if(dx==0)
                { dx = Math.random(); }
            else 
                { dx = dx/2; }
            dy = dy/2;
            split = false;
            for(int count = 0; count < game.ccnt; count++) {
                if(this == game.comet[count])
                    { setup(game.split[count]); }
            }
            game.score.score(50);
            return;
        }
            durchmesser = durchmesser * 2;
            split = true;
            wait = true;
            game.score.score(20);
            for(int count = 0; count < game.ccnt; count++) {
                if(this == game.comet[count] && game.split[count].split) {
                    game.score.score(100);
                    return;
                }
            }
    }
    
    public void setup(CometSplit cometSplit) {
        cometSplit.x = x+(15*(-dx/Math.abs(dx)));
        cometSplit.y = y;
        cometSplit.dx = -dx; cometSplit.dy = dy;
        cometSplit.durchmesser = durchmesser; cometSplit.split = split;
        cometSplit.wait = false;
    }

    public void paint(Graphics2D g2d)
    { g2d.fillOval((int)x, (int)y, durchmesser, durchmesser); }
    
    public Rectangle getBounds()
    { return new Rectangle((int)x,(int)y,durchmesser, durchmesser); }

    public boolean collision1() {
       for(int count = 0; count < game.acnt ; count++) {
            if(game.ammo[count].getBounds().intersects(getBounds())) {
                split();
                game.ammo[count].x_now = 0; game.ammo[count].y_now = 0;
                return split;
            }
        }
        return false;
    }
    public boolean collision2()
    { return game.mainship.getBounds().intersects(getBounds()); }
}
