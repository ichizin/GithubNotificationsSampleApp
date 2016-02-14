package sample.ichizin.githubnotificationssampleapp.di.modules;

import android.app.Application;
import android.content.Context;

import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import sample.ichizin.githubnotificationssampleapp.BuildConfig;
import sample.ichizin.githubnotificationssampleapp.domain.executor.JobExecutor;
import sample.ichizin.githubnotificationssampleapp.domain.executor.PostExecutionThread;
import sample.ichizin.githubnotificationssampleapp.domain.executor.ThreadExecutor;
import sample.ichizin.githubnotificationssampleapp.domain.executor.UIThread;
import sample.ichizin.githubnotificationssampleapp.presentation.Navigator;

/**
 * Application Module
 *
 * @author ichizin
 */
@Module
public class AppModule {

    private Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return this.application;
    }

    @Provides @Singleton
    Navigator provideNavigator() {
        return new Navigator();
    }

    @Provides @Singleton
    ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
        return jobExecutor;
    }

    @Provides @Singleton
    PostExecutionThread providePostExecutionThread(UIThread uiThread) {
        return uiThread;
    }

    @Provides
    @Singleton
    Cache provideOkhttpCache(Context context) {
        int cacheSize = 10 * 1024 * 1024;   // 10MB
        Cache cache = new Cache(context.getCacheDir(), cacheSize);
        return cache;
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(Cache cache) {

        OkHttpClient client = new OkHttpClient();
        client.setCache(cache);
        client.setReadTimeout(10000, TimeUnit.MILLISECONDS);
        client.setConnectTimeout(15000, TimeUnit.MILLISECONDS);

        if(BuildConfig.DEBUG) {
//            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//            client.interceptors().add(interceptor);

        }
        return client;
    }



}
