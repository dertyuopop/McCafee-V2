package McCafee;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.*;

public class Log {
    private static final Logger logger = Logger.getLogger("McCafeeLogger");

    static {
        try {
            FileHandler fileHandler = new FileHandler("app.log", true);
            fileHandler.setEncoding("UTF-8");
            fileHandler.setFormatter(new SimpleFormatter() {
                private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                @Override
                public synchronized String format(LogRecord record) {
                    return String.format("%s [%s] %s%n",
                            sdf.format(new Date(record.getMillis())),
                            record.getLevel(),
                            record.getMessage());
                }
            });

            logger.addHandler(fileHandler);
            logger.setUseParentHandlers(false); // Verhindert Ausgabe auf Konsole

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Hilfsmethoden für einfaches Logging
    public static void info(String message) {
        logger.info(message);
    }

    public static void warning(String message) {
        logger.warning(message);
    }

    public static void error(String message) {
        logger.severe(message);
    }
}
