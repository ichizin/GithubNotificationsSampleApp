package sample.ichizin.githubnotificationssampleapp.presentation.view.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import sample.ichizin.githubnotificationssampleapp.R;

/**
 *
 *
 * @author ichizin
 */
public class LoginAlertView extends LinearLayout {

    private LoginAlertViewListener loginAlertViewListener;

    @Bind(R.id.button_login)
    Button loginButton;

    public LoginAlertView(Context context) {
        super(context);
        init();
    }

    public LoginAlertView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LoginAlertView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public LoginAlertView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        this.setOrientation(VERTICAL);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.alert_login, this, true);
        ButterKnife.bind(this, view);
    }

    @OnClick(R.id.button_login)
    public void onClickLogin() {
        if(this.loginAlertViewListener != null) {
            this.loginAlertViewListener.onClickLogin();
        }
    }

    public void unBind() {
        ButterKnife.unbind(this);
    }

    public void setLoginAlertViewListener(LoginAlertViewListener loginAlertViewListener) {
        this.loginAlertViewListener = loginAlertViewListener;
    }

    public interface LoginAlertViewListener {
        void onClickLogin();
    }
}
