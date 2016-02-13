package sample.ichizin.githubnotificationssampleapp.di.componets;

import android.app.Activity;

import dagger.Component;
import sample.ichizin.githubnotificationssampleapp.di.PerActivity;
import sample.ichizin.githubnotificationssampleapp.di.modules.ActivityModule;
import sample.ichizin.githubnotificationssampleapp.di.modules.ApplicationModule;

/**
 * A base component upon which fragment's components may depend.
 * Activity-level components should extend this component.
 *
 * Subtypes of ActivityComponent should be decorated with annotation:
 */
@PerActivity
@Component(dependencies = ApplicationModule.class, modules = ActivityModule.class)
public interface ActivityComponent {
    //Exposed to sub-graphs.
    Activity activity();
}
