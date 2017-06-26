import javax.swing.*;

class Main extends JFrame {
    Main(String tittle) {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(1200, 630);
        add(new Road());
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
    }

}
