package sample.ichizin.githubnotificationssampleapp.di.modules;

import dagger.Module;
import dagger.Provides;
import sample.ichizin.githubnotificationssampleapp.di.PerActivity;
import sample.ichizin.githubnotificationssampleapp.domain.executor.PostExecutionThread;
import sample.ichizin.githubnotificationssampleapp.domain.executor.ThreadExecutor;
import sample.ichizin.githubnotificationssampleapp.domain.interactor.GetAccessToken;
import sample.ichizin.githubnotificationssampleapp.domain.repository.AuthRepository;

/**
 * Auth Module
 *
 * @author ichizin
 */
@Module
public class AuthModule {

    @Provides
    @PerActivity
    public GetAccessToken provideGetAccessToken(AuthRepository authRepository,
                          ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        return new GetAccessToken(authRepository, threadExecutor, postExecutionThread);
    }

}
