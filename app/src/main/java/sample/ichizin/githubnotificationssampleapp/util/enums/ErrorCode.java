package sample.ichizin.githubnotificationssampleapp.util.enums;

import java.util.EnumSet;

/**
 * Created by ichizin on 16/02/15.
 *
 * @author ichizin
 */
public enum ErrorCode {
    SYSTEM_ERROR(999);                  // Unknown error


    private int errorCode;

    private ErrorCode(int errorCode) {this.errorCode = errorCode;}

    public int getErrorCode() { return this.errorCode; }

    static EnumSet<ErrorCode> codes = EnumSet.allOf(ErrorCode.class);

    public static ErrorCode valueOfId(int errorCode) {
        for(ErrorCode code : codes) {
            if(code.getErrorCode() == errorCode) { return code; }
        }
        return SYSTEM_ERROR;
    }
}
