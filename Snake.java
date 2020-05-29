//TODO:
//1. Fix making multiple turns in one frame problem
//2. Fix problem of spawning apple inside the back of the snake
//3. Implement a score system and UI for player
//4. Make a better game over screen
//5. Create outlines for snake boxes to differentiate between them
//6. Add colors

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
//import java.awt.geom.*;
import java.util.ArrayList;
import java.awt.Rectangle;
//import java.util.ArrayList;
//import java.util.*;


public class Snake extends JPanel implements ActionListener, KeyListener {
    
    Timer t = new Timer(100, this);
    ArrayList<Integer> x = new ArrayList<>();
    ArrayList<Integer> y = new ArrayList<>();
    int velx = 0;
    int vely = 0;
    int size = 1;
    // boolean left = false;
    // boolean right = false;
    // boolean up = false;
    // boolean down = false;
    int ax = 500;
    int ay = 240;

    public Snake() {
        //new Timer(5, this).start());
        t.start();
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        x.add(100);
        y.add(240);
        // x.add(0);
        // y.add(0);
    }

    public void paint(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.fillOval(ax, ay, 20, 20);
        for (int i = 0; i < size; i++) {
            g2.fillRect(x.get(i), y.get(i), 20, 20);
        }
    }

    public void actionPerformed(ActionEvent e) {
        collision();
        for (int i = x.size() - 1; i > 0; i--) {
            x.set(i, x.get(i - 1));
            y.set(i, y.get(i - 1));
        }
        x.set(0, x.get(0) + velx);
        y.set(0, y.get(0) + vely);
        repaint();
    }

    public void collision() {
        Rectangle rect1 = new Rectangle(x.get(0), y.get(0), 20, 20);
        if (rect1.intersects(ax, ay, 20, 20)) {
            ax = ((int) (Math.random() * 38)) * 20;
            ay = ((int) (Math.random() * 26.5)) * 20;
            //System.out.println(ax + " " + ay);
            x.add(0);
            y.add(0);
            size++;
        }
        for (int i = 1; i < size; i++) {
            if (rect1.intersects(x.get(i), y.get(i), 20, 20)) {
                t.stop();
                System.out.println("Game Over");
            }
        }

        if (x.get(0) > 760 || x.get(0) < 0) {
            t.stop();
            System.out.println("Game Over");
        }

        if (y.get(0) > 530 || y.get(0) < 0) {
            t.stop();
            System.out.println("Game Over");
        }

        // else if (rect1.intersects(ax, ay, 20, 20) && vely == -20) {
        //     ax = (int) (Math.random() * 760);
        //     ay = (int) (Math.random() * 530);
        // }

        // else if (rect1.intersects(ax, ay, 20, 20) && velx == 20) {
        //     ax = (int) (Math.random() * 760);
        //     ay = (int) (Math.random() * 530);
        // }

        // else if (rect1.intersects(ax, ay, 20, 20) && vely == 20) {
        //     ax = (int) (Math.random() * 760);
        //     ay = (int) (Math.random() * 530);
        // }
    }

    public void up() {
        vely = -20;
        velx = 0;
    }

    public void down() {
        vely = 20;
        velx = 0;
    }

    public void left() {
        vely = 0;
        velx = -20;
    }

    public void right() {
        vely = 0;
        velx = 20;
    }

    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_UP && vely == 0) {
            up();
        }
        if (code == KeyEvent.VK_DOWN && vely == 0) {
            down();
        }
        if (code == KeyEvent.VK_LEFT && velx == 0) {
            left();
        }
        if (code == KeyEvent.VK_RIGHT && velx == 0) {
            right();
        }
    }

    public void keyTyped(KeyEvent e) {

    }

    public void keyReleased(KeyEvent ke) {

    }

    public static void main(String[] args) {
        Snake s = new Snake();
        s.setBackground(Color.WHITE);
    }
}