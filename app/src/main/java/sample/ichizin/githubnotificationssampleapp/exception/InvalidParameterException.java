package sample.ichizin.githubnotificationssampleapp.exception;


/**
 * Created by ichizin on 16/02/15.
 *
 * @author ichizin
 */
public class InvalidParameterException extends MyAppException {

    private String placeHolder;

    public InvalidParameterException(Throwable cause, String placeHolder) {
        super(cause);
        this.placeHolder = placeHolder;
    }

    public InvalidParameterException(String placeHolder) {
        this.placeHolder = placeHolder;
    }
}
