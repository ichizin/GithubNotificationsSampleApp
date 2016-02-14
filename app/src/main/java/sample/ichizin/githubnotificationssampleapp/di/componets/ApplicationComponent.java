package sample.ichizin.githubnotificationssampleapp.di.componets;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import sample.ichizin.githubnotificationssampleapp.di.modules.AppModule;
import sample.ichizin.githubnotificationssampleapp.presentation.activity.BaseActivity;

/**
 * Created by ichizin on 16/02/14.
 *
 * @author ichizin
 */
@Singleton // Constraints this component to one-per-application or unscoped bindings.
@Component(modules = AppModule.class)
public interface ApplicationComponent {

    void inject(BaseActivity baseActivity);

    Context context();
}
