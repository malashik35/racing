import javax.swing.*;
import java.awt.*;

class Opponent {
    int x;
    int y;
    private int v;
    Image img = new ImageIcon("resource/r.png").getImage();

    Rectangle getRect() {
        return new Rectangle(x, y, 140, 50);
    }

    Opponent(int x, int y, int v) {
        this.x = x;
        this.y = y;
        this.v = v;
    }
    //задаем скорость оппонента
    void move() {
        x -= v;
    }
}
