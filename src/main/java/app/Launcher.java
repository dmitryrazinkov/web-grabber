package app;

import Frame.mainGrabForm;

import javax.swing.*;

public class Launcher {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame jFrame = new mainGrabForm("Grub");
                jFrame.setDefaultCloseOperation(jFrame.EXIT_ON_CLOSE);
                jFrame.setLocation(450, 150);
                jFrame.setSize(500, 400);
                jFrame.setVisible(true);

            }
        });
    }
}
