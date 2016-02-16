package sample.ichizin.githubnotificationssampleapp.di.modules;

import dagger.Module;
import dagger.Provides;
import sample.ichizin.githubnotificationssampleapp.di.PerActivity;
import sample.ichizin.githubnotificationssampleapp.domain.executor.PostExecutionThread;
import sample.ichizin.githubnotificationssampleapp.domain.executor.ThreadExecutor;
import sample.ichizin.githubnotificationssampleapp.domain.interactor.GetNotifiacetions;
import sample.ichizin.githubnotificationssampleapp.domain.repository.NotificationApiRepository;

/**
 * Notification Module
 *
 * @author ichizin
 */
@Module
public class NotificationModule {

    @Provides
    @PerActivity
    public GetNotifiacetions provideGetNotifications(NotificationApiRepository notificationApiRepository,
                                   ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread){
        return new GetNotifiacetions(notificationApiRepository, threadExecutor, postExecutionThread);
    }
}
