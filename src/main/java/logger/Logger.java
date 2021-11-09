package logger;

public class Logger {
    private LoggingStrategy loggingStrategy;

    public void setStrategy(LoggingStrategy strategy) {
        loggingStrategy = strategy;
    }

    public void log(String message) {
        loggingStrategy.log(message);
    }
}
