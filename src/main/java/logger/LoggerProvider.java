package logger;

public class LoggerProvider {
    private static LoggerProvider instance;

    private final Logger logger;

    private LoggerProvider() {
        logger = new Logger();
        logger.setStrategy(new ConsoleLoggingStrategy());
        logger.log("Logger initialized");
    }

    public static LoggerProvider instance() {
        if (instance == null) {
            instance = new LoggerProvider();
        }
        return instance;
    }

    public Logger getLogger() {
        return logger;
    }

}
