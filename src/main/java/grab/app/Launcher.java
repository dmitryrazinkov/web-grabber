package grab.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;

public class Launcher {
    private static final Logger log = LoggerFactory.getLogger(Launcher.class);

    public static void main(final String[] args) {
        if (args.length != 1 || (!args[0].equals("create") && !args[0].equals("update"))) {
            log.error("Start app with argument \"create\" or \"update\"");
            System.exit(1);
        }

        SwingUtilities.invokeLater(new ProjectInit(args));
    }

}
