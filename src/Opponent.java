import javax.swing.*;
import java.awt.*;

class Opponent {
    int x;
    int y;
    int v;
    Image img = new ImageIcon("resource/r.png").getImage();
    Road road;

    Rectangle getRect() {
        return new Rectangle(x, y, 140, 50);
    }

    Opponent(int x, int y, int v, Road road) {
        this.x = x;
        this.y = y;
        this.v = v;
        this.road = road;
    }

    void move() {
        x -= v;
    }
}
