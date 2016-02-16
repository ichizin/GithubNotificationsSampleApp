package sample.ichizin.githubnotificationssampleapp.di.componets;

import dagger.Component;
import sample.ichizin.githubnotificationssampleapp.di.PerActivity;
import sample.ichizin.githubnotificationssampleapp.di.modules.ActivityModule;
import sample.ichizin.githubnotificationssampleapp.di.modules.NotificationModule;
import sample.ichizin.githubnotificationssampleapp.domain.interactor.GetNotifiacetions;
import sample.ichizin.githubnotificationssampleapp.presentation.activity.MainActivity;

/**
 * Created by ichizin on 16/02/16.
 *
 * @author ichizin
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, NotificationModule.class})
public interface ApiCommponent {

    void inject(MainActivity mainActivity);

    GetNotifiacetions getNotifications();
}
