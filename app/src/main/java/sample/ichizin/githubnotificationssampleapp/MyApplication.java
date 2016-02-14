package sample.ichizin.githubnotificationssampleapp;

import android.app.Application;

import sample.ichizin.githubnotificationssampleapp.di.componets.ApplicationComponent;
import sample.ichizin.githubnotificationssampleapp.di.componets.DaggerApplicationComponent;
import sample.ichizin.githubnotificationssampleapp.di.modules.AppModule;

/**
 *
 *
 * @author ichizin
 */
public class MyApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeInjector();
    }

    private void initializeInjector() {
       this.applicationComponent = DaggerApplicationComponent.builder()
               .appModule(new AppModule(this))
               .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return this.applicationComponent;
    }
}
