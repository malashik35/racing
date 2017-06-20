import javax.swing.*;
import java.awt.*;

public class Opponent {
    int x;
    int y;
    int v;
    Image img = new ImageIcon("r.png").getImage();
    Road road;

    public Rectangle getRect() {
        return new Rectangle(x,y,140, 50);
    }

    public Opponent(int x, int y, int v, Road road) {
        this.x = x;
        this.y = y;
        this.v = v;
        this.road = road;
    }

    public void move() {
        x = x - v;
    }
}
