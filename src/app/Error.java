package app;

public class Error {
    public static RuntimeException die(String message)
    {
        System.err.println(message);
        return die();
    }

    public static RuntimeException die()
    {
        System.exit(1);
        return new RuntimeException();
    }
}
