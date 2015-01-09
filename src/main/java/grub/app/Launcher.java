package grub.app;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Launcher {
    public static void main(final String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ApplicationContext context = SpringApplication.run(Config.class, args);
                //  ScriptsRepository scriptsRepository=context.getBean(ScriptsRepository.class);
                //  scriptsRepository.save(new Scripts("23"));
                //   JFrame jFrame = new mainGrabForm("Grub");
                //   jFrame.setDefaultCloseOperation(jFrame.EXIT_ON_CLOSE);
                //   jFrame.setLocation(450, 150);
                //   jFrame.setSize(500, 400);
                //   jFrame.setVisible(true);

                Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
                if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
                    try {
                        desktop.browse(new URL("http://localhost:4040").toURI());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        });
    }
}
