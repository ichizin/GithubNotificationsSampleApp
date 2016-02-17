package sample.ichizin.githubnotificationssampleapp.domain.model;

import android.content.Context;
import android.text.TextUtils;

import java.io.Serializable;

import sample.ichizin.githubnotificationssampleapp.exception.StoreTokenException;
import sample.ichizin.githubnotificationssampleapp.util.PreferenceUtil;

/**
 * Created by ichizin on 16/02/15.
 *
 * @author ichizin
 */
public class AccessToken implements Serializable {

    private static final long serialVersionUID = -2161944679465879989L;

    private static final String TOKEN_PREFIX = "token ";

    private String access_token;

    private String scope;

    private String token_type;

    private static String token;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public static String getAccessToken(Context context) throws StoreTokenException {
        if(TextUtils.isEmpty(token)) {
            token = TOKEN_PREFIX + PreferenceUtil.readAccessToken(context);
        }
        return token;
    }

    public static void setAccessToken(Context context, String accessToken) throws StoreTokenException {

        PreferenceUtil.setAccessToken(context, accessToken);
        token = TOKEN_PREFIX + accessToken;
    }


}
