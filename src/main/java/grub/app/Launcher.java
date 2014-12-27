package grub.app;

import org.springframework.boot.SpringApplication;

import javax.swing.*;

public class Launcher {
    public static void main(final String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                SpringApplication.run(Config.class, args);
             //   JFrame jFrame = new mainGrabForm("Grub");
             //   jFrame.setDefaultCloseOperation(jFrame.EXIT_ON_CLOSE);
             //   jFrame.setLocation(450, 150);
             //   jFrame.setSize(500, 400);
             //   jFrame.setVisible(true);

            }
        });
    }
}
