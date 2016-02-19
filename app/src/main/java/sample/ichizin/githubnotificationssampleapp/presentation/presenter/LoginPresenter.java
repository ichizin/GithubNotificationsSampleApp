package sample.ichizin.githubnotificationssampleapp.presentation.presenter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.text.TextUtils;


import javax.inject.Inject;

import sample.ichizin.githubnotificationssampleapp.R;
import sample.ichizin.githubnotificationssampleapp.domain.interactor.GetAccessToken;
import sample.ichizin.githubnotificationssampleapp.domain.model.AccessToken;
import sample.ichizin.githubnotificationssampleapp.exception.MyAppException;
import sample.ichizin.githubnotificationssampleapp.presentation.view.LoginView;
import sample.ichizin.githubnotificationssampleapp.util.LogUtil;

public class LoginPresenter implements Presenter<LoginView> {

    private final GetAccessToken getAccessToken;
    private LoginView loginView;

    @Inject
    public LoginPresenter(GetAccessToken getAccessToken) {
        this.getAccessToken = getAccessToken;
    }

    public void getAuthToken(String redirectUrl) {

        if(TextUtils.isEmpty(redirectUrl)) {
            // TODO: エラー表示 -> 元に戻す
            return;
        }
        Uri uri = Uri.parse(redirectUrl);
        String code = uri.getQueryParameter("code");
        Context context = this.loginView.getContext();
        this.getAccessToken.execute(context.getResources().getString(R.string.client_id),
                context.getResources().getString(R.string.client_secret),
                code,
                new GetAccessTokenSubscriber());
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        this.getAccessToken.unsubscribe();
    }

    @Override
    public void attachView(@NonNull LoginView view) {
        this.loginView = view;
    }

    private void showErrorMessage(MyAppException e) {
        String message = ErrorMessageFactory.create(this.loginView.getContext(), e);
        this.loginView.showError(message);
    }

    private class GetAccessTokenSubscriber extends rx.Subscriber<AccessToken> {

        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

//            showErrorMessage((MyAppException)e);
        }

        @Override
        public void onNext(AccessToken accessToken) {

            LogUtil.d(LoginPresenter.class.getSimpleName(), accessToken.getAccess_token());
            AccessToken.setAccessToken(LoginPresenter.this.loginView.getContext(), accessToken.getAccess_token());
            LoginPresenter.this.loginView.loginSuccess();
        }
    }


}
