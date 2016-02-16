package sample.ichizin.githubnotificationssampleapp.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Base64;

import com.facebook.crypto.Crypto;
import com.facebook.crypto.Entity;
import com.facebook.crypto.exception.CryptoInitializationException;
import com.facebook.crypto.exception.KeyChainException;
import com.facebook.crypto.keychain.SharedPrefsBackedKeyChain;
import com.facebook.crypto.util.SystemNativeCryptoLibrary;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.UUID;

import sample.ichizin.githubnotificationssampleapp.exception.StoreTokenException;
import sample.ichizin.githubnotificationssampleapp.util.enums.PreferenceKey;

/**
 * PreferenceUtil
 *
 * @author ichizin
 */
public class PreferenceUtil {

    private static final String TAG = PreferenceUtil.class.getSimpleName();
    private static final String PREFERENCE_DEFAULT = "app_preference";

    public static synchronized void write(Context context, PreferenceKey key, String value) {
        write(context, PREFERENCE_DEFAULT, key, value);
    }

    public static synchronized void write(Context context, String preferenceName,
                             PreferenceKey key, String value) {
        if (context != null) {
            LogUtil.d(TAG, "write/String  " + key.getId() + " :" + value);

            SharedPreferences pref = context
                    .getApplicationContext()
                    .getSharedPreferences(preferenceName, Activity.MODE_PRIVATE);
            SharedPreferences.Editor e = pref.edit();
            e.putString(key.getId(), value);
            e.apply();
        }
    }

    public static void write(Context context, PreferenceKey key, int value) {
        write(context, PREFERENCE_DEFAULT, key, value);
    }

    public static synchronized void write(Context context, String preferenceName,
                             PreferenceKey key, int value) {

        if(context != null) {

            LogUtil.d(TAG, "write/Int : " + value);

            SharedPreferences pref = context
                    .getApplicationContext()
                    .getSharedPreferences(preferenceName, Activity.MODE_PRIVATE);
            SharedPreferences.Editor e = pref.edit();
            e.putInt(key.getId(), value);
            e.apply();

        }
    }

    public static synchronized void write(Context context, PreferenceKey key, boolean value) {
        write(context, PREFERENCE_DEFAULT, key, value);
    }

    public static synchronized void write(Context context, String preferenceName,
                             PreferenceKey key, boolean value) {

        if(context != null) {

            LogUtil.d(TAG, "write/Int : " + value);

            SharedPreferences pref = context
                    .getApplicationContext()
                    .getSharedPreferences(preferenceName, Activity.MODE_PRIVATE);
            SharedPreferences.Editor e = pref.edit();
            e.putBoolean(key.getId(), value);
            e.apply();

        }
    }

    public static void write(Context context, PreferenceKey key, long value) {
        write(context, PREFERENCE_DEFAULT, key, value);
    }

    public static synchronized void write(Context context, String preferenceName,
                             PreferenceKey key, long value) {

        if(context != null) {

            LogUtil.d(TAG, "write/Int : " + value);
            SharedPreferences pref = context
                    .getApplicationContext()
                    .getSharedPreferences(preferenceName, Activity.MODE_PRIVATE);
            SharedPreferences.Editor e = pref.edit();
            e.putLong(key.getId(), value);
            e.apply();
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
            LogUtil.d(TAG, "read/String " + key.getId() + " :" + str);
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

    /**
     * アクセストークンを暗号化して保存
     * @param context
     * @param token
     */
    public static void setAccessToken(Context context, String token) throws StoreTokenException {

        String accessKey = UUID.randomUUID().toString();

        Crypto crypto = new Crypto(
                new SharedPrefsBackedKeyChain(context),
                new SystemNativeCryptoLibrary());

        if(!crypto.isAvailable()) {
            throw new StoreTokenException();
        }

        try {
            byte[] encryptKey = crypto.encrypt(token.getBytes(), new Entity(accessKey));
            write(context, PreferenceKey.TOKEN_ACCESS_KEY, accessKey);
            write(context, PreferenceKey.ENCRYPTED_KEY, Base64.encodeToString(encryptKey, Base64.DEFAULT));
        } catch (UnsupportedEncodingException e) {
            LogUtil.error(TAG, e);
        } catch (CryptoInitializationException e) {
            LogUtil.error(TAG, e);
        } catch (KeyChainException e) {
            LogUtil.error(TAG, e);
        } catch (IOException e) {
            LogUtil.error(TAG, e);
        }
    }

    /**
     * アクセストークンを復号して読み出す
     * @param context
     * @return
     */
    public static String readAccessToken(Context context) throws StoreTokenException {

        String accessKey = read(context, PreferenceKey.TOKEN_ACCESS_KEY);
        String encryptedToken = read(context, PreferenceKey.ENCRYPTED_KEY);
        byte[] rawEncryptedToken = null;

        if(!TextUtils.isEmpty(accessKey) && !TextUtils.isEmpty(encryptedToken)) {

            rawEncryptedToken = Base64.decode(encryptedToken, Base64.DEFAULT);

            Crypto crypto = new Crypto(
                    new SharedPrefsBackedKeyChain(context),
                    new SystemNativeCryptoLibrary());

            if(!crypto.isAvailable()) {
                throw new StoreTokenException();
            }
            try {
                byte[] decryptedToken = crypto.decrypt(rawEncryptedToken, new Entity(accessKey));
                String accessToken = new String(decryptedToken);
                return accessToken;
            } catch (UnsupportedEncodingException e) {
                LogUtil.error(TAG, e);
            } catch (CryptoInitializationException e) {
                LogUtil.error(TAG, e);
            } catch (KeyChainException e) {
                LogUtil.error(TAG, e);
            } catch (IOException e) {
                LogUtil.error(TAG, e);
            }
        }
        return null;
    }

}
