package sample.ichizin.githubnotificationssampleapp.presentation.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import sample.ichizin.githubnotificationssampleapp.MyApplication;
import sample.ichizin.githubnotificationssampleapp.di.componets.ApplicationComponent;
import sample.ichizin.githubnotificationssampleapp.di.modules.ActivityModule;
import sample.ichizin.githubnotificationssampleapp.presentation.Navigator;

/**
 * BaseActivity
 *
 * @author ichizin
 */
public class BaseActivity extends AppCompatActivity {

    @Inject
    Navigator navigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getApplicationComponent().inject(this);
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
