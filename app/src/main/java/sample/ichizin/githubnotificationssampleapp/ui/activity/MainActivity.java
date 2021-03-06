package sample.ichizin.githubnotificationssampleapp.ui.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import sample.ichizin.githubnotificationssampleapp.R;
import sample.ichizin.githubnotificationssampleapp.di.componets.ApiCommponent;
import sample.ichizin.githubnotificationssampleapp.di.componets.DaggerApiCommponent;
import sample.ichizin.githubnotificationssampleapp.di.modules.NotificationModule;
import sample.ichizin.githubnotificationssampleapp.domain.AccessToken;
import sample.ichizin.githubnotificationssampleapp.domain.Notification;
import sample.ichizin.githubnotificationssampleapp.ui.adapter.NotificationAdapter;
import sample.ichizin.githubnotificationssampleapp.presenter.MainPresenter;
import sample.ichizin.githubnotificationssampleapp.ui.view.MainView;
import sample.ichizin.githubnotificationssampleapp.ui.view.widget.LoginAlertView;
import sample.ichizin.githubnotificationssampleapp.util.LogUtil;
import sample.ichizin.githubnotificationssampleapp.util.enums.SubjectType;

public class MainActivity extends BaseActivity implements MainView,
        LoginAlertView.LoginAlertViewListener, NotificationAdapter.NotificationAdapterLisnter{

    @Inject
    MainPresenter mainPresenter;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;

    @Bind(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    @Bind(R.id.login_view)
    LoginAlertView loginView;

    @Bind(R.id.progress_content)
    FrameLayout progressArea;

    @Bind(R.id.error_refresh)
    LinearLayout errorRefreshLayout;

    @Bind(R.id.no_data_content)
    LinearLayout noDataLayout;

    private ApiCommponent apiComponent;

    private NotificationAdapter notificationAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        if(savedInstanceState == null) {
            initializeInjector();
            setToolbar();
            initializePresentr();
            initializeRecyclerView();
        }

        this.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                MainActivity.this.mainPresenter.reload();
            }
        });

        this.errorRefreshLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.mainPresenter.errorRefresh();
            }
        });
    }

    /**
     * Inject DI
     */
    private void initializeInjector() {
        this.apiComponent = DaggerApiCommponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .notificationModule(new NotificationModule()).build();
        this.apiComponent.inject(this);
    }

    /**
     *
     */
    private void setToolbar() {
        this.toolbar.setTitle(R.string.app_name);
        this.toolbar.setTitleTextColor(getResources().getColor(R.color.font_white));
        this.toolbar.inflateMenu(R.menu.menu_main);
        this.toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId() == R.id.logout) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setTitle(R.string.confirm)
                            .setMessage(R.string.confirm_logout)
                            .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    MainActivity.this.mainPresenter.logout();
                                }
                            }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    }).show();
                }
                return false;
            }
        });
    }

    private void initializePresentr() {
        this.mainPresenter.attachView(this);
        this.mainPresenter.initialize();
    }

    private void initializeRecyclerView() {

        LinearLayoutManager layoutManager =
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.setLayoutManager(layoutManager);
        this.notificationAdapter = new NotificationAdapter(getContext());
        this.notificationAdapter.setNotificationAdapterLisnter(this);
        recyclerView.setAdapter(notificationAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == LoginActivity.LOGIN_REQUEST_CODE && resultCode == RESULT_OK) {

            LogUtil.d(MainActivity.class.getSimpleName(),
                    "onActivityResult: " + AccessToken.getAccessToken(this));

            this.loginView.setVisibility(View.GONE);
            this.showLoading();
            this.mainPresenter.getData();
            this.showLogoutOnToolbar(true);
            this.swipeRefreshLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.mainPresenter.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.mainPresenter.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.mainPresenter.destroy();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showLoading() {
        this.progressArea.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        this.progressArea.setVisibility(View.GONE);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void displayLoginView() {
        this.loginView.setVisibility(View.VISIBLE);
        this.loginView.setLoginAlertViewListener(this);
        this.showLogoutOnToolbar(false);
        this.swipeRefreshLayout.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onClickLogin() {
        navigator.login(this, getContext());
    }

    @Override
    public void addAdapter(List<Notification> notifications) {
        this.notificationAdapter.add(notifications);
    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(R.string.confirm)
                .setMessage(R.string.confirm_backkey_message)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.this.finish();
                    }
                }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).show();
    }

    @Override
    public void isRefreshing(boolean flag) {
        this.swipeRefreshLayout.setRefreshing(flag);
    }

    @Override
    public void clearAdapter() {
        this.notificationAdapter.clear();
    }

    @Override
    public void showErrorRefreshPage(boolean isShow) {
        if(isShow) {
            this.errorRefreshLayout.setVisibility(View.VISIBLE);
        } else {
            this.errorRefreshLayout.setVisibility(View.GONE);
        }
    }

    @Override
    public void showNoData(boolean isShow) {
        if(isShow) {
            this.noDataLayout.setVisibility(View.VISIBLE);
        } else {
            this.noDataLayout.setVisibility(View.GONE);
        }
    }

    @Override
    public void showLogoutOnToolbar(boolean isShow) {
        Menu menu = this.toolbar.getMenu();
        if(isShow) {
            menu.getItem(0).setVisible(true);
        } else {
            menu.getItem(0).setVisible(false);
        }
    }

    @Override
    public void onClickItem(String detailApiUrl, SubjectType type) {
        this.mainPresenter.getDetailUrl(detailApiUrl, type);
    }

    @Override
    public void transferDetail(String httpUrl) {
        this.navigator.detail(getContext(), httpUrl);
    }
}
