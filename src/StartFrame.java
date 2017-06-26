import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class StartFrame extends JFrame {
    static JFrame f;
    Image menu;
    File file;
    StartFrame(String title) {
        file = new File("resource/record.txt");
        menu = new ImageIcon("resource/0.jpg").getImage();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setSize(480, 320);
        JButton button1 = new JButton("Выход");
        JButton button = new JButton( "Играть");
        button.setBounds(300,20, 150,50);
        button1.setBounds(300,80,150,50);
        button.setBackground(Color.decode("#3cff00"));
        button1.setBackground(Color.decode("#3cff00"));
        button.setForeground(Color.white);
        button1.setForeground(Color.white);

        ActionListener actionListener = e -> {
            f = new Main(title);
            dispose();
        };
        ActionListener actionListener2 = e -> {
            System.exit(0);
        };

        button.addActionListener(actionListener);
        button1.addActionListener(actionListener2);
        add(button);
        add(button1);

        setLayout(null);
        JLabel label = new JLabel("Best score: " + readScore());
        label.setForeground(Color.white);
        label.setFont(new Font("Colibry", Font.BOLD, 50));
        label.setVisible(true);
        label.setSize(200,200);
        label.setVerticalAlignment(JLabel.TOP);
        label.setHorizontalAlignment(JLabel.LEFT);
        add(label);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
    }



    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(menu, 0,0,null);
    }

    public static void main(String[] args) {SwingUtilities.invokeLater(() -> new StartFrame("Езда по встречной"));}

    private Integer readScore(){
        try {
            try(BufferedReader in = new BufferedReader(new FileReader(file.getAbsoluteFile()))){
//                if (in.readLine() == null) return 0;
                return Integer.valueOf(in.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
