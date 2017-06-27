import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.List;


public class Road extends JPanel implements Runnable {

    private int s = 0;
    private File file;
    private Image img = new ImageIcon("resource/doroga1.png").getImage();
    private Player player = new Player();
    private List<Opponent> opponents = new ArrayList<Opponent>();


    Road() {
        Thread opponentCreat = new Thread(this);
        opponentCreat.start();
        file = new File("resource/record.txt");
        setFocusable(true);
        KeyListener keyListener = new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                player.keyPressed(e);
            }

            public void keyReleased(KeyEvent e) {
                player.keyReleased(e);
            }
        };
        addKeyListener(keyListener);

        ActionListener timerListener = e -> {
            {
                player.move();
                CrashWithOpponent();
                WhenYouWin();
            }
            repaint();
        };
        Timer timer = new Timer(20, timerListener);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.ITALIC, 20));

        g.drawImage(img, player.field1, 0, null);
        g.drawImage(img, player.field2, 0, null);
        g.drawImage(player.img, player.x, player.y, null);

        double v = 200 / Player.maxV * player.v;
        g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.ITALIC, 20));
        g.drawString("Your Speed: " + v + " км/ч", 100, 20);

        //определяем и выводим пройденную дистанцию
        if (player.field2 == 1200) s += 1200;
        g.drawString("Your distance: " + s + " м", 100, 590);

        //определяем и выводим уровень сложности
        if (player.s < 20000) {
            g.drawString("L E V E L   1 : EASY", 500, 20);
        }
        if (player.s >= 20000 && player.s < 50000) {
            g.drawString("L E V E L   2 : MEDIUM", 500, 20);
        }
        if (player.s >= 50000 && player.s < 70000) {
            g.drawString("L E V E L   3 : HARD", 500, 20);
        }
        if (player.s >= 70000) {
            g.drawString("L E V E L   4 : FATALITY", 500, 20);
        }
//задаем движение и рисуем оппонентов
        try {
            Iterator<Opponent> i = opponents.iterator();
            while (i.hasNext()) {
                Opponent opponent = i.next();
                if (opponent.x > 1200 || opponent.x < -200) i.remove();
                else {
                    opponent.move();
                    g.drawImage(opponent.img, opponent.x, opponent.y, null);
                }
            }
        }catch (Exception ignored){}

    }

    //при победе
    private void WhenYouWin() {
        if (player.s > 99600) {
            JOptionPane.showMessageDialog(null, "Поздравляю! Ты победил!!");
            new StartFrame("Езда по встречной");
            writeScore(s);
            StartFrame.f.dispose();

        }
    }
    //при столкновении
    private void CrashWithOpponent() {
        for (Opponent opponent : opponents) {
            if (player.getRect().intersects(opponent.getRect())) {
                JOptionPane.showMessageDialog(null, "Ууууууупс, Ты проиграл...");
                writeScore(s);
                new StartFrame("Езда по встречной");
                StartFrame.f.dispose();
            }
        }
    }

    //точка входа врагов
    @Override
    public void run() {
        int previous = 0;
        while (true) {
            Random rand = new Random();
            try {

                Thread.sleep(rand.nextInt(2000)); //засыпаем от 0 до 2000
                int y1 = rand.nextInt(500);

                if (Math.abs(y1 - previous) < 70 && previous > y1 && y1 - 70 > 150) y1 -= 70;
                else y1 += 140;
                if (Math.abs(y1 - previous) < 70 && previous < y1 && y1 + 70 < 500) y1 += 70;
                else y1 -= 140;
                previous = y1;

                if (player.s < 20000) opponents.add(new Opponent(1200, y1, 10));
                if (player.s >= 20000 && player.s < 50000) {
                    opponents.add(new Opponent(1200, y1, 20));
                }
                if (player.s >= 50000 && player.s < 70000) opponents.add(new Opponent(1200, y1, 30));
                if (player.s >= 70000) opponents.add(new Opponent(1200, y1, 40));

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    //записываем достижение
    private void writeScore(int maxScore){
        try {
            try (PrintWriter out = new PrintWriter(file.getAbsoluteFile())) {
                System.out.println("MaxScore = "+maxScore);
                out.print(maxScore);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}