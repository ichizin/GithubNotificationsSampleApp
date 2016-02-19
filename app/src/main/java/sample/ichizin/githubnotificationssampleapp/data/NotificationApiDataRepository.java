package sample.ichizin.githubnotificationssampleapp.data;


import android.support.annotation.NonNull;

import java.util.List;

import retrofit.Response;
import retrofit.Retrofit;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Headers;
import retrofit.http.QueryMap;
import retrofit.http.Url;
import rx.Observable;
import rx.functions.Func1;
import sample.ichizin.githubnotificationssampleapp.domain.model.Issue;
import sample.ichizin.githubnotificationssampleapp.domain.model.Notification;
import sample.ichizin.githubnotificationssampleapp.domain.model.PullRequest;
import sample.ichizin.githubnotificationssampleapp.domain.model.Releases;
import sample.ichizin.githubnotificationssampleapp.domain.model.request.NotificationRequest;
import sample.ichizin.githubnotificationssampleapp.domain.repository.NotificationApiRepository;
import sample.ichizin.githubnotificationssampleapp.util.LogUtil;

/**
 * Created by ichizin on 16/02/16.
 *
 * @author ichizin
 */
public class NotificationApiDataRepository implements NotificationApiRepository {

    private final String TAG = NotificationApiDataRepository.class.getSimpleName();
    private NotificationApi notificationApi;

    public NotificationApiDataRepository(Retrofit retrofit) {
        this.notificationApi = retrofit.create(NotificationApi.class);
    }

    @Override
    public Observable getNotifications(@NonNull String accessToken) {
        return getNotifications(accessToken, new NotificationRequest.Builder().build());
    }

    @Override
    public Observable getNotifications(@NonNull String accessToken,
                                                           @NonNull NotificationRequest entity) {
        return this.notificationApi.getNotifications(accessToken, entity);
    }


    @Override
    public Observable<String> getIssueHttpUrl(String apiUrl) {
        return this.notificationApi.getIssue(apiUrl)
               .map(new Func1<Issue, String>() {
                   @Override
                   public String call(Issue issue) {
                       return issue.getHtml_url();
                   }
               });
    }

    @Override
    public Observable<String> getPullRequestUrl(String apiUrl) {
        return this.notificationApi.getPullRequest(apiUrl)
                .map(new Func1<PullRequest, String>() {
                    @Override
                    public String call(PullRequest pullRequest) {
                        return pullRequest.getHtml_url();
                    }
                });
    }

    @Override
    public Observable<String> getReleaseUrl(String apiUrl) {
        return this.notificationApi.getRelease(apiUrl)
                .map(new Func1<Releases, String>() {
                    @Override
                    public String call(Releases releases) {
                        return releases.getHtml_url();
                    }
                });
    }

    interface NotificationApi {

        @Headers("Cache-Control: no-cache")
        @GET("notifications")
        Observable<List<Notification>> getNotifications(@Header("Authorization") String accessToken,
                                                        @QueryMap NotificationRequest notification);

        @GET
        Observable<Issue> getIssue(@Url String apiUrl);

        @GET
        Observable<PullRequest> getPullRequest(@Url String apiUrl);

        @GET
        Observable<Releases> getRelease(@Url String apiUrl);
    }
}
