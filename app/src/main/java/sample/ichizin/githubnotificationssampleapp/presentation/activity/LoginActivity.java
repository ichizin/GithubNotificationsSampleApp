package sample.ichizin.githubnotificationssampleapp.presentation.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import javax.inject.Inject;

import sample.ichizin.githubnotificationssampleapp.R;
import sample.ichizin.githubnotificationssampleapp.di.componets.DaggerAuthComponent;
import sample.ichizin.githubnotificationssampleapp.di.modules.AuthModule;
import sample.ichizin.githubnotificationssampleapp.presentation.presenter.LoginPresenter;
import sample.ichizin.githubnotificationssampleapp.presentation.view.LoginView;
import sample.ichizin.githubnotificationssampleapp.util.LogUtil;

/**
 * GitHub Web Login
 *
 * @author ichizin
 */
public class LoginActivity extends BaseActivity implements LoginView {

    @Inject
    LoginPresenter loginPresenter;

    public static final String AUTH_URL = "github_oauth_url";
    public static final int LOGIN_REQUEST_CODE = 11;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState == null) {
            this.initInjector();
            this.setWebView();
            this.initPresenter();
        }
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

    /**
     * Set webview
     */
    private void setWebView() {
        WebView webView = new WebView(this);
        String url = getIntent().getStringExtra(AUTH_URL);
        webView.loadUrl(url);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                showLoading();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                hideLoading();
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                if(url.startsWith(getContext().getResources().getString(R.string.callback_url))) {
                    LoginActivity.this.loginPresenter.getAuthToken(url);
                }
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
        setContentView(webView);
    }

    /**
     *
     */
    private void initPresenter() {
        this.loginPresenter.attachView(this);
    }

    /**
     * Inject component
     */
    private void initInjector() {
         DaggerAuthComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .authModule(new AuthModule())
                .build().inject(this);
    }


    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showLoading() {

        if(this.progressDialog == null) {
            this.progressDialog = new  ProgressDialog(getContext());
            progressDialog.setMessage(
                    getContext().getResources().getString(R.string.progress_dispose));
        }
        this.progressDialog.show();
    }

    @Override
    public void hideLoading() {
        if(this.progressDialog != null) {
            this.progressDialog.dismiss();
        }
    }

    @Override
    public void showError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loginSuccess() {
        setResult(RESULT_OK);
        this.finish();
    }

    @Override
    public void loginFail() {

    }
}
