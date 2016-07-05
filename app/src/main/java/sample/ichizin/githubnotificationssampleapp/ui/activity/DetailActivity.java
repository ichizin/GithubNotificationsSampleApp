package sample.ichizin.githubnotificationssampleapp.ui.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import butterknife.Bind;
import butterknife.ButterKnife;
import sample.ichizin.githubnotificationssampleapp.R;

/**
 * Created by ichizin on 16/02/19.
 *
 * @author ichizin
 */
public class DetailActivity extends BaseActivity {

    public static String HTTP_URL = "http_url";

    @Bind(R.id.detail_webview)
    WebView webView;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        this.progressDialog = new  ProgressDialog(this);
            progressDialog.setMessage(
                    this.getResources().getString(R.string.progress_loading));
        this.progressDialog.show();

        String httpUrl = getIntent().getStringExtra(HTTP_URL);
        this.webView.loadUrl(httpUrl);

        this.webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                DetailActivity.this.progressDialog.dismiss();
            }
        });

        this.toolbar.setNavigationIcon(R.drawable.button_redo);
        this.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
