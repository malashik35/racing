import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Player {

    public static final int maxV = 30;
    public static final int maxTOP = 30;
    public static final int maxBOTTOM = 500;

    Image img_c = new ImageIcon("Player.png").getImage();
    Image img_UP = new ImageIcon("Player_Left.png").getImage();
    Image img_DOWN = new ImageIcon("Player_Right.png").getImage();

    Image img = img_c;

    int v = 5; //speed
    int w = 0; //uskor
    int s = 0; //put
    //координаты
    int x = 30;
    int y = 100;
    int dy = 0;
    //слои дороги
    int field1 = 0;
    int field2 = 1200;

    public void move() {
        s += v;
        v += w;

        if (v <= 0) v = 0;
        if (v >= maxV) v = maxV;

        y -= dy;

        if (y <= maxTOP) y = maxTOP;
        if (y >= maxBOTTOM) y = maxBOTTOM;

        if (field2 - v <= 0) {
            field1 = 0;
            field2 = 1200;

        } else {
            field1 -= v;
            field2 -= v;

        }
    }

    public Rectangle getRect() {
        return new Rectangle(x, y, 140, 40);
    }

   public void keyPressed(KeyEvent e) { //есть ускорение
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_RIGHT) {
            w = 1;
        }
        if (key == KeyEvent.VK_LEFT) {
            w = -1;
        }
        if (key == KeyEvent.VK_UP) {
            dy = 5;
            img = img_UP;
        }
        if (key == KeyEvent.VK_DOWN) {
            dy = -5;
            img = img_DOWN;
        }
    }

    public void keyReleased(KeyEvent e) { //нет ускорения
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_RIGHT) {
            w = 0;
        }
        if (key == KeyEvent.VK_LEFT) {
            w = 0;
        }
        if (key == KeyEvent.VK_UP) {
            dy = 0;
            img = img_c;
        }
        if (key == KeyEvent.VK_DOWN) {
            dy = 0;
            img = img_c;
        }

    }
}
