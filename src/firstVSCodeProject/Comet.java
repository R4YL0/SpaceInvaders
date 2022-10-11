package firstVSCodeProject;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 * @author R4YL0
 */

public class Comet {
    SpaceInvaders game;
    double x = Math.floor(Math.random()*370+15); double y = Math.floor(Math.random()*50 + 100);
    int durchmesser = 30;
    double dx;  double dy;
    boolean split = true;
    boolean wait = false;
    boolean cometCollision = false;
    boolean cometIntersect = false;
    int comCollInt;
    int comSpCollInt;

    public Comet(SpaceInvaders game)
    { this.game = game;
        dx = Math.random() * 4 - 2;
        dy = Math.random() * 2 / Math.sqrt(game.ccnt) + 1;
        checkIntersection(true);
    }
    public void checkIntersection(boolean yChange) {
        for(int i = 0; i<game.ccnt; i++) {
            if(this == game.comet[i]) {
                if(collision3(i)) {
                    x = Math.floor(Math.random()*370+15);
                    if(yChange)
                        y = Math.floor(Math.random()*50 + 100);
                    checkIntersection(yChange);
                    return;
                }
                return;
            }

        }
    }
    public void move() {
        //Collision with Ammo, Reset
        if(collision1()) {
            x = Math.floor(Math.random()*370+15);
            y = -50;
            dx = 0; dy = 0;
            checkIntersection(false);
        } else
            x = x+dx;
        y = y+dy;

        //Collision with MainShip
        if(collision2()) {
            game.gameOver();
        }

        //Comet Collision with Comets
        if(collision3(game.ccnt)) {
            if(!game.comet[comCollInt].cometCollision && !cometCollision) {
                dx = -dx;
                x += dx;
                if(game.comet[comCollInt].getBounds().intersects(getBounds())) {
                    if (y > game.comet[comCollInt].y) {
                        x += dy;
                        y += dy;
                        x += dy;
                    } else {
                        x += dy;
                        y -= dy;
                        x += dx;
                    }
                }
                if(dx * game.comet[comCollInt].dx > 0)
                    game.comet[comCollInt].dx = -game.comet[comCollInt].dx;
                cometCollision = true;
            } else if(cometCollision) {
                if (y > game.comet[comCollInt].y)
                    y += dy;
                else
                    y -= dy;
            } else
                game.comet[comCollInt].cometCollision = false;
        }


        // Comet Leaving Bounds
        if(x<-50) {
            x = 400;
        }
        if(x>400) {
            x = -50;
        }
        if(y>575) {
            for(int i = 0; i<game.ccnt; i++) {
                if (!split && !game.split[i].wait) {
                    durchmesser = durchmesser * 2;
                    split = true;
                    wait = true;
                } else {
                    x = Math.floor(Math.random() * 370 + 15);
                    y = -50;
                    checkIntersection(false);
                    dx = (Math.random() * 4 - 2) / Math.sqrt(2 * game.ccnt);
                }
            }
        }
        //Game checking if cometSplit and Itself reached Bounds
        for(int count = 0; count < game.ccnt; count++) {
            if(this == game.comet[count] && game.comets[count]) {
                dx = Math.random()*4 - 2; dy = Math.random()*2/Math.sqrt(game.ccnt) + 1;
                wait = false;
            }
        }
    }

    //Cometsplit and Setup
    public void split() {
        if(split) {
            durchmesser = durchmesser/2;
            dy = dy/3;
            if(dx==0)
                dx = Math.random();
            else 
                dx = dx/2;
            split = false;
            for(int count = 0; count < game.ccnt; count++) {
                if(this == game.comet[count])
                    setup(game.split[count]);
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

    //JFrame & Hitbox
    public void paint(Graphics2D g2d)
    { g2d.fillOval((int)x, (int)y, durchmesser, durchmesser); }
    public Rectangle getBounds()
    { return new Rectangle((int)x,(int)y,durchmesser, durchmesser); }

    //Collision with Ammo
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

    //Collision with Mainship
    public boolean collision2()
    { return game.mainship.getBounds().intersects(getBounds()); }

    //Collision with Comets
    public boolean collision3(int k) {
        for(int i = 0; i< k; i++) {
            if(game.comet[i] != this && game.comet[i].getBounds().intersects(getBounds())) {
                comCollInt = i;
                return true;
            }
        }
        return false;
    }
}
