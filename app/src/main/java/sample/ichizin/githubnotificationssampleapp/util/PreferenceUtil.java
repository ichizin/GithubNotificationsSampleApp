package sample.ichizin.githubnotificationssampleapp.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;

import sample.ichizin.githubnotificationssampleapp.util.enums.PreferenceKey;

/**
 * PreferenceUtil
 *
 * @author ichizin
 */
public class PreferenceUtil {

    private static final String TAG = PreferenceUtil.class.getSimpleName();
    private static final String PREFERENCE_DEFAULT = "app_preference";

    public static void write(Context context, PreferenceKey key, String value) {
        write(context, PREFERENCE_DEFAULT, key, value);
    }

    public static void write(Context context, String preferenceName,
                             PreferenceKey key, String value) {
        if (context != null) {
            LogUtil.d(TAG, "write/String : " + value);

            SharedPreferences pref = context
                    .getApplicationContext()
                    .getSharedPreferences(preferenceName, Activity.MODE_PRIVATE);
            SharedPreferences.Editor e = pref.edit();
            e.putString(key.getId(), value);
            e.commit();
        }
    }

    public static void write(Context context, PreferenceKey key, int value) {
        write(context, PREFERENCE_DEFAULT, key, value);
    }

    public static void write(Context context, String preferenceName,
                             PreferenceKey key, int value) {

        if(context != null) {

            LogUtil.d(TAG, "write/Int : " + value);

            SharedPreferences pref = context
                    .getApplicationContext()
                    .getSharedPreferences(preferenceName, Activity.MODE_PRIVATE);
            SharedPreferences.Editor e = pref.edit();
            e.putInt(key.getId(), value);
            e.commit();

        }
    }

    public static void write(Context context, PreferenceKey key, boolean value) {
        write(context, PREFERENCE_DEFAULT, key, value);
    }

    public static void write(Context context, String preferenceName,
                             PreferenceKey key, boolean value) {

        if(context != null) {

            LogUtil.d(TAG, "write/Int : " + value);

            SharedPreferences pref = context
                    .getApplicationContext()
                    .getSharedPreferences(preferenceName, Activity.MODE_PRIVATE);
            SharedPreferences.Editor e = pref.edit();
            e.putBoolean(key.getId(), value);
            e.commit();

        }
    }

    public static void write(Context context, PreferenceKey key, long value) {
        write(context, PREFERENCE_DEFAULT, key, value);
    }

    public static void write(Context context, String preferenceName,
                             PreferenceKey key, long value) {

        if(context != null) {

            LogUtil.d(TAG, "write/Int : " + value);

            SharedPreferences pref = context
                    .getApplicationContext()
                    .getSharedPreferences(preferenceName, Activity.MODE_PRIVATE);
            SharedPreferences.Editor e = pref.edit();
            e.putLong(key.getId(), value);
            e.commit();

        }
    }

    /*
     * プリファレンス読み出し
     */
    public static String read(Context context, PreferenceKey key) {
        return read(context, PREFERENCE_DEFAULT, key);
    }

    public static String read(Context context, String preferenceName, PreferenceKey key) {

        SharedPreferences pref = context.getApplicationContext()
                .getSharedPreferences(preferenceName, Activity.MODE_PRIVATE);

        String str = null;
        try {
            str = pref.getString(key.getId(), null);
            LogUtil.d(TAG, "read/String : " + str);
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
        return str;
    }

    public static int readInt(Context context, PreferenceKey key) {
        return readInt(context, PREFERENCE_DEFAULT, key, -1);
    }

    public static int readInt(Context context, PreferenceKey key, int defaultValue) {
        return readInt(context, PREFERENCE_DEFAULT, key, defaultValue);
    }


    public static int readInt(Context context, String preferenceName, PreferenceKey key) {
        return readInt(context, preferenceName, key, -1);
    }


    public static int readInt(Context context, String preferenceName, PreferenceKey key, int defaultValue) {

        SharedPreferences pref = context.getApplicationContext()
                .getSharedPreferences(preferenceName, Activity.MODE_PRIVATE);

        int num = defaultValue;
        try {
            num = pref.getInt(key.getId(), defaultValue);
            LogUtil.d(TAG, "read/int : " + num);
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
        return num;
    }

    public static long readLong(Context context, PreferenceKey key) {
        return readLong(context, PREFERENCE_DEFAULT, key);
    }

    public static long readLong(Context context, String preferenceName,
                                PreferenceKey key) {

        SharedPreferences pref = context.getApplicationContext()
                .getSharedPreferences(preferenceName, Activity.MODE_PRIVATE);

        long num = -1;
        try {
            num = pref.getLong(key.getId(), -1);
            LogUtil.d(TAG, "read/long : " + num);
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
        return num;
    }

    public static boolean readBoolean(Context context, PreferenceKey key) {
        return readBoolean(context, PREFERENCE_DEFAULT, key, false);
    }

    public static boolean readBoolean(Context context, PreferenceKey key, boolean initValue) {
        return readBoolean(context, PREFERENCE_DEFAULT, key, initValue);
    }

    public static boolean readBoolean(Context context, String preferenceName,
                                      PreferenceKey key, boolean initValue) {

        SharedPreferences pref = context.getApplicationContext()
                .getSharedPreferences(preferenceName, Activity.MODE_PRIVATE);

        boolean result = false;
        try {
            result = pref.getBoolean(key.getId(), initValue);
            LogUtil.d(TAG, "read/boolean : " + String.valueOf(result));
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
        return result;
    }


    /*
     * プリファレンスを全削除
     */
    public static void allDelete(Context context) {
        allDelete(context, PREFERENCE_DEFAULT);
    }

    public static void allDelete(Context context, String preferenceName) {
        SharedPreferences sharedPreferences = context.getApplicationContext()
                .getSharedPreferences(preferenceName, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Map<String, ?> keys = sharedPreferences.getAll();
        if (keys.size() > 0) {
            for (String key : keys.keySet()) {
                editor.remove(key);
            }
            editor.commit();
        }
    }

    public static void delete(Context context, PreferenceKey key) {
        delete(context, PREFERENCE_DEFAULT, key);
    }

    public static void delete(Context context, String preferenceName, PreferenceKey key) {
        SharedPreferences sharedPreferences = context.getApplicationContext()
                .getSharedPreferences(preferenceName, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Map<String, ?> keys = sharedPreferences.getAll();
        if (keys.size() > 0) {
            for (String k : keys.keySet()) {
                if (k.equals(key)) {
                    editor.remove(key.getId());
                }
            }
            editor.commit();
        }
    }

}
