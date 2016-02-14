package sample.ichizin.githubnotificationssampleapp.exception;

import android.support.annotation.StringRes;

import sample.ichizin.githubnotificationssampleapp.util.enums.ErrorCode;

/**
 *
 *
 * @author ichizin
 */
public class MyAppException extends RuntimeException {

    private Throwable cause;
    private @StringRes
    int messageRes;
    private ErrorCode errorCode;

    public MyAppException(Throwable cause, @StringRes int messageRes) {
        super(cause);
        this.cause = cause;
        this.messageRes = messageRes;
    }

    public MyAppException(Throwable cause, @StringRes int messageRes, ErrorCode errorCode) {
        super(cause);
        this.cause = cause;
        this.messageRes = messageRes;
        this.errorCode = errorCode;
    }

    @Override
    public Throwable getCause() {
        return cause;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public @StringRes int getErrorMessageRes() {
        return messageRes;
    }

}
