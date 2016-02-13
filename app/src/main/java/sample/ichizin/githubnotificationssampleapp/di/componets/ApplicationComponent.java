package sample.ichizin.githubnotificationssampleapp.di.componets;

import javax.inject.Singleton;

import dagger.Component;
import sample.ichizin.githubnotificationssampleapp.di.modules.ApplicationModule;

/**
 * Created by ichizin on 16/02/14.
 *
 * @author ichizin
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {


}
