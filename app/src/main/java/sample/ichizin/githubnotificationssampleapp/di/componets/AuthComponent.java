package sample.ichizin.githubnotificationssampleapp.di.componets;

import dagger.Component;
import sample.ichizin.githubnotificationssampleapp.di.PerActivity;
import sample.ichizin.githubnotificationssampleapp.di.modules.ActivityModule;
import sample.ichizin.githubnotificationssampleapp.di.modules.AuthModule;
import sample.ichizin.githubnotificationssampleapp.domain.usecase.GetAccessToken;
import sample.ichizin.githubnotificationssampleapp.ui.activity.LoginActivity;

/**
 * Created by ichizin on 16/02/14.
 *
 * @author ichizin
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, AuthModule.class})
public interface AuthComponent  extends ActivityComponent {

    void inject(LoginActivity loginActivity);

    GetAccessToken getAccessToken();
}
