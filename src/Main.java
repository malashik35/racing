import javax.swing.*;

class Main extends JFrame {
    //запуск игры
    Main(String tittle) {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        int WIDTH = 1200;
        int HEIGHT = 630;
        setSize(WIDTH, HEIGHT);
        add(new Road());
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle(tittle);
    }
}
