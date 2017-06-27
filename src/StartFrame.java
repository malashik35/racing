import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class StartFrame extends JFrame {
    static JFrame f;
    private File file;

    StartFrame(String title) {
        file = new File("resource/record.txt");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JLabel menu = new JLabel(new ImageIcon("resource/menu.jpg"));
        getLayeredPane().add(menu, 0).setBounds(0,0,1200,630);
        int HEIGHT = 630;
        int WIDTH = 1200;
        setSize(WIDTH, HEIGHT);

        ActionListener actionListener = e -> {
            f = new Main(title);
            dispose();
        };
        ActionListener actionListener2 = e -> {
            System.exit(0);
        };
//задаем кнопки старт и выход
        JButton startButton = new JButton("START");
        startButton.setBounds(600, 450, 250, 100);
        startButton.setBackground(Color.BLACK);
        startButton.setFont(new Font("Trebuchet MS", Font.BOLD, 50));
        startButton.setForeground(Color.white);
        startButton.setFocusable(false);
        startButton.addActionListener(actionListener);
        getLayeredPane().add(startButton, 0);

        JButton exitButton = new JButton("EXIT");
        exitButton.setBounds(900, 450, 250, 100);
        exitButton.setBackground(Color.BLACK);
        exitButton.setFont(new Font("Trebuchet MS", Font.BOLD, 50));
        exitButton.setForeground(Color.white);
        exitButton.setFocusable(false);
        exitButton.addActionListener(actionListener2);
        getLayeredPane().add(exitButton, 1);
//записываем значение скорости
        JLabel label = new JLabel("Your score: " + readScore());
        label.setFont(new Font("Trebuchet MS", Font.BOLD, 50));
        label.setForeground(Color.white);
        label.setVisible(true);
        getLayeredPane().add(label, 1).setBounds(30, 0, 1000, 100);

        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Езда по встречной");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StartFrame("Езда по встречной"));
    }
    //считывам достижение
    private Integer readScore() {
        try {
            try (BufferedReader in = new BufferedReader(new FileReader(file.getAbsoluteFile()))) {
                return Integer.valueOf(in.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
