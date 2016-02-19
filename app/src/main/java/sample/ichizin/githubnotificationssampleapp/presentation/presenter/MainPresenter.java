package sample.ichizin.githubnotificationssampleapp.presentation.presenter;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import retrofit.HttpException;
import rx.Subscriber;
import sample.ichizin.githubnotificationssampleapp.R;
import sample.ichizin.githubnotificationssampleapp.domain.interactor.GetIssueHttpUrl;
import sample.ichizin.githubnotificationssampleapp.domain.interactor.GetNotifiacetions;
import sample.ichizin.githubnotificationssampleapp.domain.interactor.GetPullRequestHttpUrl;
import sample.ichizin.githubnotificationssampleapp.domain.interactor.GetReleaseHttpUrl;
import sample.ichizin.githubnotificationssampleapp.domain.model.AccessToken;
import sample.ichizin.githubnotificationssampleapp.domain.model.Notification;
import sample.ichizin.githubnotificationssampleapp.presentation.view.MainView;
import sample.ichizin.githubnotificationssampleapp.util.LogUtil;
import sample.ichizin.githubnotificationssampleapp.util.enums.SubjectType;

/**
 * Created by ichizin on 16/02/16.
 *
 * @author ichizin
 */
public class MainPresenter implements Presenter<MainView> {

    private MainView mainView;
    private boolean isReload;

    private final GetNotifiacetions getNotifiacetions;
    private final GetPullRequestHttpUrl getPullRequestHttpUrl;
    private final GetIssueHttpUrl getIssueHttpUrl;
    private final GetReleaseHttpUrl getReleaseHttpUrl;


    @Inject
    public MainPresenter(GetNotifiacetions getNotifiacetions, GetIssueHttpUrl getIssueHttpUrl,
                         GetPullRequestHttpUrl getPullRequestHttpUrl, GetReleaseHttpUrl getReleaseHttpUrl) {
        this.getNotifiacetions = getNotifiacetions;
        this.getIssueHttpUrl = getIssueHttpUrl;
        this.getPullRequestHttpUrl = getPullRequestHttpUrl;
        this.getReleaseHttpUrl = getReleaseHttpUrl;
    }

    public void initialize() {

        if(TextUtils.isEmpty(AccessToken.getAccessToken(this.mainView.getContext()))) {
            this.mainView.displayLoginView();
            this.mainView.showLogoutOnToolbar(false);
        } else {
            this.mainView.showLoading();
            this.getData();
            this.mainView.showLogoutOnToolbar(true);
        }
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {
        this.getNotifiacetions.unsubscribe();
        this.getIssueHttpUrl.unsubscribe();
        this.getPullRequestHttpUrl.unsubscribe();

    }

    @Override
    public void destroy() {}

    public void getData() {
        this.getNotifiacetions.execute(AccessToken.getAccessToken(
                this.mainView.getContext()), new GetNotificationSuscriber());
    }

    public void reload() {
        this.isReload = true;
        this.mainView.showNoData(false);
        getData();
    }

    public void errorRefresh() {
        this.mainView.showErrorRefreshPage(false);
        this.mainView.showLoading();
        getData();
    }

    public void logout() {
        AccessToken.setAccessToken(this.mainView.getContext(), "");
        this.mainView.clearAdapter();
        this.mainView.displayLoginView();
    }

    public void getDetailUrl(String apiUrl, SubjectType subjectType) {

        if(TextUtils.isEmpty(apiUrl)) {
            this.mainView.showError(this.mainView.getContext().getResources().getString(R.string.error_invalid_url));
            return;
        }

        switch (subjectType) {
            case ISSUE:
                this.getIssueHttpUrl.execute(apiUrl, new GetHttpUrlSubscriber());
                break;
            case PULL_REQUEST:
                this.getPullRequestHttpUrl.execute(apiUrl, new GetHttpUrlSubscriber());
                break;
            case RELEASE:
                this.getReleaseHttpUrl.execute(apiUrl, new GetHttpUrlSubscriber());
                break;
            default:
                this.mainView.showError(this.mainView.getContext().getResources().getString(R.string.error_not_support));
                break;
        }
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

            HttpException exception = (HttpException) e;
            LogUtil.e(MainPresenter.class.getSimpleName(), exception.code());
            LogUtil.e(MainPresenter.class.getSimpleName(), exception.response().message());
            MainPresenter.this.mainView.showError(
                    MainPresenter.this.mainView.getContext().getResources().getString(R.string.error_authorize));
            MainPresenter.this.mainView.displayLoginView();
            MainPresenter.this.mainView.isRefreshing(false);
        }

        @Override
        public void onNext(List<Notification> notifications) {

            if(isReload) {
                MainPresenter.this.mainView.clearAdapter();
            }

            if(notifications.size() == 0) {
                MainPresenter.this.mainView.showNoData(true);
            }
            MainPresenter.this.mainView.addAdapter(notifications);
        }
    }

    private class GetHttpUrlSubscriber extends Subscriber<String> {

        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            try {
                HttpException exception = (HttpException) e;
                MainPresenter.this.mainView.showError(exception.response().errorBody().string());
            } catch (IOException e1) {
                MainPresenter.this.mainView.showError(e.getMessage());
            }
        }

        @Override
        public void onNext(String httpUrl) {
            LogUtil.d(MainPresenter.class.getSimpleName(), httpUrl);
            MainPresenter.this.mainView.transferDetail(httpUrl);
        }
    }
}
