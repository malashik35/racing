import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.*;


public class Road extends JPanel implements ActionListener, Runnable {

    boolean state;

    Timer timer = new Timer(20, (ActionListener) this);

    int s = 0;

    Image img = new ImageIcon("doroga1.png").getImage();

    Player player = new Player();

    Thread opponentCreat = new Thread(this);

    java.util.List<Opponent> opponents = new ArrayList<Opponent>();

    public Road() {
        timer.start();
        opponentCreat.start();
        addKeyListener(new MyKeyAdapter());
        setFocusable(true);
    }


    private class MyKeyAdapter extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            player.keyPressed(e);
        }

        public void keyReleased(KeyEvent e) {
            player.keyReleased(e);
        }

    }

    public void paint(Graphics g) {
        g = (Graphics2D) g;
        g.drawImage(img, player.field1, 0, null);
        g.drawImage(img, player.field2, 0, null);
        g.drawImage(player.img, player.x, player.y, null);

        double v = 200/Player.maxV * player.v;
        g.setColor(Color.white);
        Font font = new Font("Arial", Font.ITALIC, 20);
        g.setFont(font);
        g.drawString("Your Speed: " + v + " км/ч", 100, 20);



        if (player.field2 == 1200) s += 1200;
        g.setColor(Color.white);
        Font font1 = new Font("Arial", Font.ITALIC, 20);
        g.setFont(font1);
        g.drawString("Your distance: " + s + " м", 100, 590);

        if (player.s < 20000) {
            g.setColor(Color.white);
            g.drawString("L E V E L   1 : EASY", 500, 20);
        }

        if (player.s >= 20000 && player.s < 50000) {
            g.setColor(Color.white);
            g.drawString("L E V E L   2 : MEDIUM", 500, 20);
        }

        if (player.s >= 50000 && player.s < 70000) {
            g.setColor(Color.white);
            g.drawString("L E V E L   3 : HARD", 500, 20);
        }

        if (player.s >= 70000) {
            g.setColor(Color.white);
            g.drawString("L E V E L   4 : FATALITY", 500, 20);
        }


        Iterator<Opponent> i = opponents.iterator();
        while (i.hasNext()) {
            Opponent opponent = i.next();
            if (opponent.x > 1200 || opponent.x < -200) i.remove();
            else {
                opponent.move();
                g.drawImage(opponent.img, opponent.x, opponent.y, null);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        player.move();
        repaint();
        CrashWithOpponent();
        WhenYouWin();
    }

    private void WhenYouWin() {
        if (player.s > 99600) {
            JOptionPane.showMessageDialog(null, "Поздравляю! Ты победил!!");
            System.exit(0);
        }
    }

    private void CrashWithOpponent() { // проверка столкновений
        Iterator<Opponent> i = opponents.iterator();
        while (i.hasNext()) {
            Opponent opponent = i.next();
            if (player.getRect().intersects(opponent.getRect())) {
                JOptionPane.showMessageDialog(null, "Ууууууупс, Ты проиграл...");
              /*  state = true;

                if (state) {
                    SwingUtilities.invokeLater(() -> {
                        opponentCreat = new Thread();
                        opponentCreat.start();
                    });
                    return;
                }*/
                System.exit(1);
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
                int y1=rand.nextInt(500);

                if (Math.abs(y1-previous) < 70 && previous>y1 && y1-70 > 150)  y1 -=70; else y1 +=140;
                if (Math.abs(y1-previous) < 70 && previous<y1 && y1+70 < 500)  y1 +=70; else y1 -= 140;
                previous=y1;

                if (player.s < 20000) opponents.add(new Opponent(1200, y1, 10, this));
                if (player.s >= 20000 && player.s < 50000) { opponents.add(new Opponent(1200, y1, 20, this)); }
                if (player.s >= 50000 && player.s < 70000) opponents.add(new Opponent(1200, y1, 30, this));
                if (player.s >= 70000) opponents.add(new Opponent(1200, y1, 40, this));

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}