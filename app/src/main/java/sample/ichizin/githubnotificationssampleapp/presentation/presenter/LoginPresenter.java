package sample.ichizin.githubnotificationssampleapp.presentation.presenter;

import android.support.annotation.NonNull;

import javax.inject.Inject;

import sample.ichizin.githubnotificationssampleapp.presentation.view.LoginView;

public class LoginPresenter implements Presenter<LoginView> {

    private LoginView loginView;

    @Inject
    public LoginPresenter() {

    }

    public void getAuthToken(String redirectUrl) {


        
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void attachView(@NonNull LoginView view) {
        this.loginView = view;
    }
}
