package McCafee;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.*;

public class Log {
    private static final Logger logger = Logger.getLogger(Log.class.getName());

    static {
        try {
            FileHandler fileHandler = new FileHandler("app.log", true);

            // Eigenes Format mit Zeitstempel, aber nicht für [NOLOG]
            fileHandler.setFormatter(new Formatter() {
                @Override
                public String format(LogRecord record) {
                    String msg = record.getMessage();

                    // Wenn Nachricht mit "[NOLOG]" beginnt, nicht ins Log schreiben
                    if (msg.startsWith("[NOLOG]")) {
                        return "";
                    }

                    // Zeitstempel hinzufügen
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String timestamp = sdf.format(new Date(record.getMillis()));

                    return timestamp + " [" + record.getLevel() + "] " + msg + "\n";
                }
            });

            fileHandler.setEncoding("UTF-8"); // Setzt korrekte Zeichenkodierung
            logger.addHandler(fileHandler);
            logger.setUseParentHandlers(false); // Verhindert Standard-Konsolen-Logging

            // System.out doppelt umleiten (aber bestimmte Nachrichten ignorieren)
            PrintStream dualStream = new DualPrintStream(System.out, new LogOutputStream(logger, Level.INFO));
            System.setOut(dualStream);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Methode zum Abfangen und Loggen von Benutzereingaben
    public static String getUserInput(Scanner scanner, String prompt) {
        System.out.print(prompt); // In Konsole anzeigen
        String input = scanner.nextLine();
        logger.info("[UserInput] " + prompt + input);
        return input;
    }

    // Direkte Logging-Methoden
    public static void log(Level level, String className, String message) {
        logger.log(level, "[" + className + "] " + message);
    }

    public static void info(String className, String message) {
        log(Level.INFO, className, message);
    }

    public static void warning(String className, String message) {
        log(Level.WARNING, className, message);
    }

    public static void error(String className, String message) {
        log(Level.SEVERE, className, message);
    }

    // Klasse für doppelte Ausgabe (Konsole + Log)
    private static class DualPrintStream extends PrintStream {
        private final OutputStream second;

        public DualPrintStream(OutputStream main, OutputStream second) throws UnsupportedEncodingException {
            super(main, true, StandardCharsets.UTF_8); // Setzt UTF-8
            this.second = second;
        }

        @Override
        public void println(String message) {
            if (message.startsWith("[NOLOG]")) {
                // Entfernt "[NOLOG]" vor der Konsolenausgabe
                super.println(message.substring(7));
            } else {
                super.println(message);
                try {
                    second.write((message + "\n").getBytes(StandardCharsets.UTF_8));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // LogOutputStream für System.out-Logging
    private static class LogOutputStream extends OutputStream {
        private final Logger logger;
        private final Level level;
        private final StringBuilder buffer = new StringBuilder();

        public LogOutputStream(Logger logger, Level level) {
            this.logger = logger;
            this.level = level;
        }

        @Override
        public void write(int b) {
            if (b == '\n') {
                String message = buffer.toString();
                if (!message.startsWith("[NOLOG]")) {
                    logger.log(level, message);
                }
                buffer.setLength(0);
            } else {
                buffer.append((char) b);
            }
        }
    }
}
