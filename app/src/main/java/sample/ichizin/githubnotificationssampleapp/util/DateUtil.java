package sample.ichizin.githubnotificationssampleapp.util;

import android.support.annotation.NonNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ichizin on 16/02/18.
 *
 * @author ichizin
 */
public class DateUtil {

    private static final long MIN = 6000L;         // 1分 1000 * 60
    private static final long HOUR = 3600000L;     // 1時間 1000 * 60 * 60
    private static final long DAY = 86400000L;     // 1日 1000 * 60 * 60 * 24

    /**
     *
     * @param isoTime
     * @return
     * @throws ParseException
     */
    public static String parseIso8601(@NonNull String isoTime) throws ParseException{
        return parseIso8601(isoTime, "yyyy/MM/dd HH:mm:ss");
    }
    /**
     *
     * @param isoTime
     * @param dateFormat
     * @return
     * @throws ParseException
     */
    public static String parseIso8601(@NonNull String isoTime, @NonNull String dateFormat) throws ParseException{
        String s = isoTime.replace("Z", "+00:00");
        Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").parse(s);
        SimpleDateFormat sd = new SimpleDateFormat(dateFormat);
        return sd.format(date);
    }




}
