package sample.ichizin.githubnotificationssampleapp.ui.activity;

import android.app.Activity;
import android.os.Bundle;

import javax.inject.Inject;

import butterknife.ButterKnife;
import sample.ichizin.githubnotificationssampleapp.MyApplication;
import sample.ichizin.githubnotificationssampleapp.di.componets.ApplicationComponent;
import sample.ichizin.githubnotificationssampleapp.di.modules.ActivityModule;
import sample.ichizin.githubnotificationssampleapp.util.Navigator;

/**
 * BaseActivity
 *
 * @author ichizin
 */
public class BaseActivity extends Activity {

    @Inject
    Navigator navigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getApplicationComponent().inject(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    /**
     * Get the Main Application component for dependency injection.
     *
     */
    protected ApplicationComponent getApplicationComponent() {
        return ((MyApplication)getApplication()).getApplicationComponent();
    }


    /**
     * Get an Activity module for dependency injection.
     *
     */
    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }
}
