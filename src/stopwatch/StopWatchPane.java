package stopwatch;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;

public class StopWatchPane extends JPanel {

    private final Color backgroundColor = new Color(200, 200, 200);
    private final Color colorNumbers = new Color(70, 70, 70);

    private int min = 0;
    private int sec = 0;

    private Timer timer;

    private ActionListener al = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            sec++;
            if (sec == 60) {
                min++;
                sec = 0;
            }
            if (min == 60) {
                min = 0;
            }
            repaint();
        }
    };

    private MouseAdapter ma = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getButton() == MouseEvent.BUTTON1) {
                if (timer.isRunning()) {
                    timer.stop();
                    return;
                }
                if (!timer.isRunning()) {
                    timer.start();
                    return;
                }
            }
            if (e.getButton() == MouseEvent.BUTTON3) {
                min = 0;
                sec = 0;
                timer.stop();
                repaint();
            }
        }
    };

    public StopWatchPane() {
        super(null);
        setBackground(backgroundColor);
        setToolTipText("<html>Левая кнопка мыши - старт/стоп<br>Правая кнопка мыши - обнулить значение");
        timer = new Timer(1000, al);
        addMouseListener(ma);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(colorNumbers);
        paintDots(g2d);
        paintTimeBoard(g2d);
    }

    private void paintDots(Graphics2D g2d) {
        double segmentWidth;
        double segmentHeight;
        int widthDot, heightDot;
        int sizeDot;
        int xStart, yStart;

        segmentWidth = (double) getWidth() / 5;
        segmentHeight = (double) getHeight();

        widthDot = (int) (segmentWidth / 2);
        heightDot = (int) (segmentHeight / 4);
        sizeDot = Math.min(widthDot, heightDot);

        xStart = (int) (segmentWidth * 2.5 - sizeDot / 2);
        yStart = (int) ((double) getHeight() / 8);
        g2d.fillOval(xStart, yStart, sizeDot, sizeDot);

        yStart *= 5;
        g2d.fillOval(xStart, yStart, sizeDot, sizeDot);
    }

    private void paintTimeBoard(Graphics2D g2d) {
        double segmentWidth;
        segmentWidth = (double) getWidth() / 5;
        int firstDigit, secondDigit;

        //Обрабатываем минуты
        if (min < 10) {
            firstDigit = 0;
            secondDigit = min;
            paintNumber(g2d, firstDigit, 0, 0, (int) segmentWidth, getHeight());
            paintNumber(g2d, secondDigit, (int) segmentWidth, 0, (int) segmentWidth, getHeight());
        }
        if (min >= 10) {
            firstDigit = min / 10;  //("" + min).charAt(0) - 48;
            secondDigit = min % 10; //("" + min).charAt(1) - 48;
            paintNumber(g2d, firstDigit, 0, 0, (int) segmentWidth, getHeight());
            paintNumber(g2d, secondDigit, (int) segmentWidth, 0, (int) segmentWidth, getHeight());
        }

        //Обрабатываем секунды
        if (sec < 10) {
            firstDigit = 0;
            secondDigit = sec;
            paintNumber(g2d, firstDigit, (int) (segmentWidth * 3), 0, (int) segmentWidth, getHeight());
            paintNumber(g2d, secondDigit, (int) (segmentWidth * 4), 0, (int) segmentWidth, getHeight());
        }
        if (sec >= 10) {
            firstDigit = sec / 10;  //("" + sec).charAt(0) - 48;
            secondDigit = sec % 10; //("" + sec).charAt(1) - 48;
            paintNumber(g2d, firstDigit, (int) (segmentWidth * 3), 0, (int) segmentWidth, getHeight());
            paintNumber(g2d, secondDigit, (int) (segmentWidth * 4), 0, (int) segmentWidth, getHeight());
        }
    }

    private void paintNumber(Graphics2D g2d, int val, int xStart, int yStart, int widthNumber, int heightNumber) {
        PolygonCoords[] segments;
        segments = NumberMaker.getNumberPolygons(val, xStart, yStart, widthNumber, heightNumber);
        for (PolygonCoords polygonCoords : segments) {
            g2d.fillPolygon(polygonCoords.x, polygonCoords.y, polygonCoords.getCountPolygonPoints());
        }
    }

}
