package sample.ichizin.githubnotificationssampleapp.exception;

import android.content.Context;

import sample.ichizin.githubnotificationssampleapp.R;

/**
 * return Error Message class
 *
 * @author ichizin
 */
public class ErrorMessageFactory {

    private final static String TAG = ErrorMessageFactory.class.getSimpleName();

    private ErrorMessageFactory(){}

    public static String create(Context context, MyAppException exception) {

        String message = context.getResources().getString(R.string.error_unknown);

        if(exception instanceof InvalidParameterException) {

        }

        return message;
    }

}
