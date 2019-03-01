package stopwatch;

import javax.swing.*;
import java.awt.*;

public class MainClass {

    private final int WIDTH_FRM = 1100;
    private final int HEIGHT_FRM = 480;

    private final JFrame frm;

    public MainClass() {
        frm = new JFrame("Секундомер");
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frm.setSize(WIDTH_FRM, HEIGHT_FRM);
        //frm.setResizable(false);
        int xPos = Toolkit.getDefaultToolkit().getScreenSize().width / 2 - WIDTH_FRM / 2;
        int yPos = Toolkit.getDefaultToolkit().getScreenSize().height / 2 - HEIGHT_FRM / 2;
        frm.setLocation(xPos, yPos);

        frm.setContentPane(new StopWatchPane());

        frm.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainClass();
            }
        });
    }

}
