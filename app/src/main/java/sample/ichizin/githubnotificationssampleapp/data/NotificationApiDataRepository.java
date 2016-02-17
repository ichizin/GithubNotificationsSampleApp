package sample.ichizin.githubnotificationssampleapp.data;


import java.util.List;

import retrofit.Retrofit;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Query;
import rx.Observable;
import sample.ichizin.githubnotificationssampleapp.domain.model.Notification;
import sample.ichizin.githubnotificationssampleapp.domain.repository.NotificationApiRepository;

/**
 * Created by ichizin on 16/02/16.
 *
 * @author ichizin
 */
public class NotificationApiDataRepository implements NotificationApiRepository {

    private NotificationApi notificationApi;

    public NotificationApiDataRepository(Retrofit retrofit) {
        this.notificationApi = retrofit.create(NotificationApi.class);
    }


    @Override
    public Observable<List<Notification>> getNotifications(String accessToken) {
        return this.notificationApi.getNotifications(accessToken, true, false, "");
    }

    interface NotificationApi {

        @GET("notifications")
        Observable<List<Notification>> getNotifications(@Header("Authorization") String accessToken,
                                                        @Query("all") boolean all,
                                                        @Query("participating") boolean participating,
                                                        @Query("before") String before);
    }
}
