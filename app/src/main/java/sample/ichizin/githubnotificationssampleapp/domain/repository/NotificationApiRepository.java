package sample.ichizin.githubnotificationssampleapp.domain.repository;

import java.util.List;

import rx.Observable;
import sample.ichizin.githubnotificationssampleapp.domain.model.Notification;

/**
 * Created by ichizin on 16/02/16.
 *
 * @author ichizin
 */
public interface NotificationApiRepository {

    Observable<List<Notification>> getNotifications(String accessToken);
}
