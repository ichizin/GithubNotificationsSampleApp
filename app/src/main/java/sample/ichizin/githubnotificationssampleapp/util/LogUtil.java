package sample.ichizin.githubnotificationssampleapp.util;

import android.support.annotation.NonNull;
import android.util.Log;

import sample.ichizin.githubnotificationssampleapp.BuildConfig;

/**
 * Created by ichizin on 16/02/14.
 *
 * @author ichizin
 */
public class LogUtil {
    private static long start = 0L;

    public static void i(@NonNull String tag, @NonNull Object val) {
        if(BuildConfig.DEBUG) {
            Log.i(tag, String.valueOf(val));
        }
    }

    public static void d(@NonNull String tag, @NonNull Object val) {
        if(BuildConfig.DEBUG) {
            Log.d(tag, String.valueOf(val));
        }
    }

    public static void w(@NonNull String tag, @NonNull Object val) {
        if(BuildConfig.DEBUG) {
            Log.w(tag, String.valueOf(val));
        }
    }

    public static void e(@NonNull String tag, @NonNull Object val) {
        if(BuildConfig.DEBUG) {
            Log.e(tag, String.valueOf(val));
        }
    }

    public static void error(@NonNull String tag, @NonNull Object val) {
        Log.e(tag, String.valueOf(val));
    }

    /**
     * 計測開始
     */
    public static void start() {
        start =  System.nanoTime();
    }

    /**
     * 計測開始から経過ミリ秒を取得します
     * @return
     */
    private  void performanceMilliTime(String key) {
        if(BuildConfig.DEBUG) {
            if(start == 0) { return; }
            Log.i(key, String.valueOf((System.nanoTime() - start) / 1000000));
        }
    }

    /**
     * 計測開始から経過秒を取得します
     * @return
     */
    private  void performanceSecondTime(String key) {
        if(BuildConfig.DEBUG) {
            if(start == 0) { return; }
            Log.i(key, String.valueOf((System.nanoTime() - start) / 1000000000));
        }
    }

}
