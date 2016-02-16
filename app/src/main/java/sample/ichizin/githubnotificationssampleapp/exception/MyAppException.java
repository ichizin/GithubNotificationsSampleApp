package sample.ichizin.githubnotificationssampleapp.exception;

/**
 *
 *
 * @author ichizin
 */
public class MyAppException extends RuntimeException {

    private Throwable cause;

    public MyAppException(){}

    public MyAppException(Throwable cause) {
        super(cause);
    }

}
