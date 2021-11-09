package logger;

public class ConsoleLoggingStrategy implements LoggingStrategy{
    @Override
    public void log(String message)
    {
        System.out.println("[Log: Console] " +message );
    }
}
