package sample.ichizin.githubnotificationssampleapp.presentation.presenter;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import sample.ichizin.githubnotificationssampleapp.domain.interactor.GetNotifiacetions;
import sample.ichizin.githubnotificationssampleapp.domain.model.AccessToken;
import sample.ichizin.githubnotificationssampleapp.domain.model.Notification;
import sample.ichizin.githubnotificationssampleapp.presentation.view.MainView;
import sample.ichizin.githubnotificationssampleapp.util.LogUtil;

/**
 * Created by ichizin on 16/02/16.
 *
 * @author ichizin
 */
public class MainPresenter implements Presenter<MainView> {

    private MainView mainView;
    private boolean isReload;

    private final GetNotifiacetions getNotifiacetions;

    @Inject
    public MainPresenter(GetNotifiacetions getNotifiacetions) {
        this.getNotifiacetions = getNotifiacetions;
    }

    public void initialize() {

        if(TextUtils.isEmpty(AccessToken.getAccessToken(this.mainView.getContext()))) {
            this.mainView.displayLoginView();
        } else {
            this.mainView.showLoading();
            this.getData();
        }
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        getNotifiacetions.unsubscribe();
    }

    public void getData() {
        this.getNotifiacetions.execute(AccessToken.getAccessToken(
                this.mainView.getContext()), new GetNotificationSuscriber());
    }

    public void reload() {
        this.isReload = true;
        getData();
    }

    public void errorRefresh() {
        MainPresenter.this.mainView.showErrorRefreshPage(false);
        MainPresenter.this.mainView.showLoading();
        getData();

    }

    @Override
    public void attachView(@NonNull MainView view) {
        this.mainView = view;
    }

    private class GetNotificationSuscriber extends Subscriber<List<Notification>> {
        @Override
        public void onCompleted() {
            isReload  = false;
            MainPresenter.this.mainView.isRefreshing(false);
            MainPresenter.this.mainView.hideLoading();
        }

        @Override
        public void onError(Throwable e) {

            LogUtil.e(MainPresenter.class.getSimpleName(), e);
            MainPresenter.this.mainView.showErrorRefreshPage(true);
        }

        @Override
        public void onNext(List<Notification> notifications) {

            if(isReload) {
                MainPresenter.this.mainView.clearAdapter();
            }
            MainPresenter.this.mainView.addAdapter(notifications);
        }
    }
}
