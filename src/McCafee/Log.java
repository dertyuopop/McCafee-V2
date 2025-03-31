package McCafee;
import java.io.IOException;
import java.util.logging.*;

public class Log {
    private static final Logger logger = Logger.getLogger(Log.class.getName());

    static {
        try {
            FileHandler fileHandler = new FileHandler("app.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
            logger.setLevel(Level.ALL);

            // entfernen der Konsolenausgabe
            logger.setUseParentHandlers(false);
        }
        catch (IOException e) {
            System.err.println("Fehler beim Laden des Logs" + e.getMessage());
        }
    }

    //Loggen von Nachrichten
    public static void log(Level level, String message) {
        logger.log(level, message);
    }

    //Higher Level info Log
    public static void info(String message) {
        logger.log(Level.INFO, message);
    }

    //Warning Log
    public static void warning(String message) {
        logger.log(Level.WARNING, message);
    }

    // Error Log
    public static void error(String message) {
        logger.log(Level.SEVERE, message);
    }
}
