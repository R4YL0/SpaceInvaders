package firstVSCodeProject;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
//import java.awt.geom.Area;

/**
 * @author R4YL0
 */

public class SpaceInvaders extends JPanel  {
    int acnt = 5;
    int ccnt = 3;
    int ecnt = 1;
    MainShip mainship;
    EnemyShip enemyship[] = new EnemyShip[ecnt];
    Comet[] comet = new Comet[ccnt];
    CometSplit[] split = new CometSplit[ccnt];
    Ammo[] ammo = new Ammo[acnt];
    EnemyAmmo[] enemyAmmo = new EnemyAmmo[acnt];
    UI score; int points;
    int height = 600; int width = 400;
    int x = (width/2) - 28; int y = height - (height/4);
    boolean[] comets = new boolean[ccnt];

    public SpaceInvaders() {
        setSize(width,height);
        mainship = new MainShip(this,x,y);
        score = new UI(this);
        for(int count = 0; count < ccnt; count++) {
            comet[count] = new Comet(this);
            split[count] = new CometSplit(this);
            comets[count] = false;
        }
        for(int count = 0; count < acnt; count++) {
            ammo[count] = new Ammo(this, 0, 0);
            enemyAmmo[count] = new EnemyAmmo(this, 600, -20);
        }
        for(int count = 0; count < ecnt; count++)
            enemyship[count] = new EnemyShip(this);
        setFocusable(true);
        addKeyListener(new KeyListener() {
            //gedrückte Taste loslassen
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_LEFT)
                    mainship.keyReleased(e);
                if(e.getKeyCode() == KeyEvent.VK_RIGHT)
                    mainship.keyReleased(e);
                if(e.getKeyCode() == KeyEvent.VK_UP)
                    mainship.keyReleased(e);
                if(e.getKeyCode() == KeyEvent.VK_DOWN)
                    mainship.keyReleased(e);
                if(e.getKeyCode() == KeyEvent.VK_SHIFT || e.getKeyCode() == KeyEvent.VK_CONTROL)
                    mainship.keyReleased(e);
            }
            //Taste gedrückt lassen
            public void keyPressed(KeyEvent e) {
                mainship.keyPressed(e);
                for(int count = 0; count < acnt && e.getKeyCode() == KeyEvent.VK_SPACE; count++) {
                    if(ammo[count].y_now <= 0) {
                        ammo[count].keyPressed(e,mainship.xPosition(),mainship.yPosition());
                        return;
                    }
                }
                if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
                    System.exit(ABORT);
            }
            
            public void keyTyped(KeyEvent e) {}
        });
    }
    public static void main(String[] args) throws InterruptedException
    {
        JFrame rahmen = new JFrame("Space Invaders");
        SpaceInvaders myinvaders = new SpaceInvaders();
        rahmen.setSize(myinvaders.width,myinvaders.height);
        rahmen.add(myinvaders);
        rahmen.setVisible(true);
        rahmen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        while(true) {
            myinvaders.move(); myinvaders.repaint();
            Thread.sleep(9);
        }
    }

    public void move() {
        for(int count = 0; count < ccnt; count++) {
            if(comet[count].wait && split[count].wait)
                comets[count] = true;
            else
                comets[count] = false;
            comet[count].move(); split[count].move();
        }
        mainship.move();
        for(int count = 0; count < acnt; count++) {
            ammo[count].move();
            enemyAmmo[count].move();
        }
        for(int count = 0; count < ecnt; count++)
            enemyship[count].move();
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        mainship.paint(g2d);
        for(int count = 0; count < ccnt; count++)
        { comet[count].paint(g2d); split[count].paint(g2d); }
        for(int count = 0; count < acnt; count++) {
            ammo[count].paint(g2d);
            enemyAmmo[count].paint(g2d);
        }
        for(int count = 0; count < ecnt; count++)
            enemyship[count].paint(g2d);
    }
    
    public void gameOver() {
        JOptionPane.showMessageDialog(this, "Points: " + points,"Game Over",JOptionPane.YES_NO_OPTION);
        System.out.print("Total: " + points + " Points.");
        System.exit(ABORT);
    }
}