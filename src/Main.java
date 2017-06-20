import javax.swing.*;
import java.util.jar.JarFile;

/**
 * Created by user on 19.06.2017.
 */
public class Main {
    public static void main(String[] args) {

        JFrame f = new JFrame("Езда по встречной ");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(1200, 640);
        f.add(new Road());
        f.setVisible(true);
    }
}
