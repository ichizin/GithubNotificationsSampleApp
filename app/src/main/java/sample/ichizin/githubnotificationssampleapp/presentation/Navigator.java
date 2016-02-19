package sample.ichizin.githubnotificationssampleapp.presentation;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.squareup.okhttp.HttpUrl;

import sample.ichizin.githubnotificationssampleapp.R;
import sample.ichizin.githubnotificationssampleapp.presentation.activity.BaseActivity;
import sample.ichizin.githubnotificationssampleapp.presentation.activity.DetailActivity;
import sample.ichizin.githubnotificationssampleapp.presentation.activity.LoginActivity;

/**
 *
 *
 * @author ichizin
 */
public class Navigator {

    public void login(@NonNull BaseActivity activity, @NonNull Context context) {

        HttpUrl.Builder url = new HttpUrl.Builder()
                .scheme("https")
                .host(context.getResources().getString(R.string.oauth_host))
                .addPathSegment("login")
                .addPathSegment("oauth")
                .addPathSegment("authorize")
                .addQueryParameter("client_id", context.getResources().getString(R.string.client_id))
                .addQueryParameter("scope", context.getResources().getString(R.string.scopes));

        Intent intent = new Intent(context, LoginActivity.class);
        intent.putExtra(LoginActivity.AUTH_URL, url.toString());
        activity.startActivityForResult(intent, LoginActivity.LOGIN_REQUEST_CODE);
    }

    public void detail(@NonNull Context context, @NonNull String httpUrl) {

        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(DetailActivity.HTTP_URL, httpUrl);
        context.startActivity(intent);
    }

}
