import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

class Player {

    static final int maxV = 30;
    private static final int maxTOP = 30;
    private static final int maxBOTTOM = 500;

    private Image img_c = new ImageIcon("resource/Player.png").getImage();
    private Image img_UP = new ImageIcon("resource/Player_Left.png").getImage();
    private Image img_DOWN = new ImageIcon("resource/Player_Right.png").getImage();

    Image img = img_c;

    int v = 5; //speed
    int w = 0; //uskor
    int s = 0; //put
    //координаты
    int x = 30;
    int y = 100;
    private int dy = 0;
    //слои дороги
    int field1 = 0;
    int field2 = 1199;

    void move() {

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

    Rectangle getRect() {
        return new Rectangle(x, y, 140, 40);
    }

   void keyPressed(KeyEvent e) { //есть ускорение
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

    void keyReleased(KeyEvent e) { //нет ускорения
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
