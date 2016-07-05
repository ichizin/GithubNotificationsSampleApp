package sample.ichizin.githubnotificationssampleapp.ui.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import sample.ichizin.githubnotificationssampleapp.R;
import sample.ichizin.githubnotificationssampleapp.di.componets.DaggerAuthComponent;
import sample.ichizin.githubnotificationssampleapp.di.modules.AuthModule;
import sample.ichizin.githubnotificationssampleapp.presenter.LoginPresenter;
import sample.ichizin.githubnotificationssampleapp.ui.view.LoginView;

/**
 * GitHub Web Login
 *
 * @author ichizin
 */
public class LoginActivity extends BaseActivity implements LoginView {

    @Inject
    LoginPresenter loginPresenter;

    @Bind(R.id.login_webview)
    WebView loginWebView;

    @Bind(R.id.error_refresh)
    LinearLayout errorRefreshLayout;

    public static final String AUTH_URL = "github_oauth_url";
    public static final int LOGIN_REQUEST_CODE = 11;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        if(savedInstanceState == null) {
            this.initInjector();
            this.initUI();
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
    private void initUI() {

        String url = getIntent().getStringExtra(AUTH_URL);
        loginWebView.loadUrl(url);
        loginWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                view.setVisibility(View.INVISIBLE);
                showLoading();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                view.setVisibility(View.VISIBLE);
                hideLoading();
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                if (url.startsWith(getContext().getResources().getString(R.string.callback_url))) {
                    LoginActivity.this.loginPresenter.getAuthToken(url);
                }
                return super.shouldOverrideUrlLoading(view, url);
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                LoginActivity.this.errorRefreshLayout.setVisibility(View.VISIBLE);
                Toast.makeText(getContext(), R.string.error_webpage, Toast.LENGTH_SHORT).show();
            }
        });

        this.errorRefreshLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity.this.errorRefreshLayout.setVisibility(View.GONE);
                LoginActivity.this.loginWebView.reload();
            }
        });

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
