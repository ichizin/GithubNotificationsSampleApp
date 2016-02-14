package sample.ichizin.githubnotificationssampleapp.presentation.activity;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import sample.ichizin.githubnotificationssampleapp.util.LogUtil;

/**
 * GitHub Web Login
 *
 * @author ichizin
 */
public class LoginActivity extends BaseActivity {

    public static final String AUTH_URL = "github_oauth_url";
    public static final int LOGIN_REQUEST_CODE = 11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WebView webView = new WebView(this);
        String url = getIntent().getStringExtra(AUTH_URL);
        LogUtil.d(LoginActivity.class.getSimpleName(), url);
        webView.loadUrl(url);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                LogUtil.d(LoginActivity.class.getSimpleName(), url);

                Uri uri = Uri.parse(url);


                return super.shouldOverrideUrlLoading(view, url);
            }
        });
        setContentView(webView);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}
