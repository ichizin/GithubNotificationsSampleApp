package sample.ichizin.githubnotificationssampleapp.di.modules;

import android.app.Application;
import android.content.Context;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import sample.ichizin.githubnotificationssampleapp.BuildConfig;
import sample.ichizin.githubnotificationssampleapp.data.AuthDataRepository;
import sample.ichizin.githubnotificationssampleapp.data.HttpLoggingInterceptor;
import sample.ichizin.githubnotificationssampleapp.data.NotificationApiDataRepository;
import sample.ichizin.githubnotificationssampleapp.domain.executor.JobExecutor;
import sample.ichizin.githubnotificationssampleapp.domain.executor.PostExecutionThread;
import sample.ichizin.githubnotificationssampleapp.domain.executor.ThreadExecutor;
import sample.ichizin.githubnotificationssampleapp.domain.executor.UIThread;
import sample.ichizin.githubnotificationssampleapp.domain.repository.AuthRepository;
import sample.ichizin.githubnotificationssampleapp.domain.repository.NotificationApiRepository;
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
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return gsonBuilder.create();
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

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        if(BuildConfig.DEBUG) {
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        }
        client.interceptors().add(interceptor);

        return client;
    }

    @Provides
    @Singleton
    @Named("auth")
    Retrofit provideAuthRetrofit(Gson gson, OkHttpClient okHttpClient) {

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl("https://github.com/")
                .client(okHttpClient)
                .build();

        return retrofit;
    }

    @Provides
    @Singleton
    @Named("api")
    Retrofit provideApiRetrofit(Gson gson, OkHttpClient okHttpClient) {

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl("https://api.github.com/")
                .client(okHttpClient)
                .build();

        return retrofit;
    }

    @Provides
    @Singleton
    AuthRepository provideAuthRepository(@Named("auth")Retrofit retrofit) {
        return new AuthDataRepository(retrofit);
    }

    @Provides
    @Singleton
    NotificationApiRepository provideNotificationApiRepository(@Named("api") Retrofit retrofit) {
        return new NotificationApiDataRepository(retrofit);
    }

}
