package sample.ichizin.githubnotificationssampleapp.presentation.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import sample.ichizin.githubnotificationssampleapp.R;
import sample.ichizin.githubnotificationssampleapp.di.componets.ApiCommponent;
import sample.ichizin.githubnotificationssampleapp.di.componets.AuthComponent;
import sample.ichizin.githubnotificationssampleapp.di.componets.DaggerApiCommponent;
import sample.ichizin.githubnotificationssampleapp.di.componets.DaggerAuthComponent;
import sample.ichizin.githubnotificationssampleapp.di.modules.AuthModule;
import sample.ichizin.githubnotificationssampleapp.di.modules.NotificationModule;
import sample.ichizin.githubnotificationssampleapp.domain.model.AccessToken;
import sample.ichizin.githubnotificationssampleapp.domain.model.Notification;
import sample.ichizin.githubnotificationssampleapp.presentation.adapter.NotificationAdapter;
import sample.ichizin.githubnotificationssampleapp.presentation.presenter.MainPresenter;
import sample.ichizin.githubnotificationssampleapp.presentation.view.MainView;
import sample.ichizin.githubnotificationssampleapp.presentation.view.widget.LoginAlertView;
import sample.ichizin.githubnotificationssampleapp.util.LogUtil;

public class MainActivity extends BaseActivity implements MainView, LoginAlertView.LoginAlertViewListener {

    @Inject
    MainPresenter mainPresenter;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;

    @Bind(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    private ApiCommponent apiComponent;
    private AlertDialog loginDialog;
    private LoginAlertView loginAlertView;

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

    private void setToolbar() {
        this.toolbar.setTitle(R.string.app_name);
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
        recyclerView.setAdapter(notificationAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == LoginActivity.LOGIN_REQUEST_CODE && resultCode == RESULT_OK) {

            LogUtil.d(MainActivity.class.getSimpleName(),
                    "onActivityResult: " + AccessToken.getAccessToken(this));

            if(this.loginDialog != null) {
                this.loginDialog.dismiss();
            }
            this.mainPresenter.getData();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(this.loginAlertView != null)  {
            this.loginAlertView.unBind();
        }
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void displayLoginView() {
        LoginAlertView view = new LoginAlertView(getContext());
        view.setLoginAlertViewListener(this);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(view);
        this.loginDialog = builder.create();
        this.loginDialog.setCanceledOnTouchOutside(true);
        this.loginDialog.show();
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
}
