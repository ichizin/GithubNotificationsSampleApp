package sample.ichizin.githubnotificationssampleapp.domain.repository;


import java.util.List;

import rx.Observable;
import sample.ichizin.githubnotificationssampleapp.domain.model.Notification;
import sample.ichizin.githubnotificationssampleapp.domain.model.request.NotificationRequest;

/**
 * Created by ichizin on 16/02/16.
 *
 * @author ichizin
 */
public interface NotificationApiRepository {

    Observable<List<Notification>> getNotifications(String accessToken);

    Observable getNotifications(String accessToken, NotificationRequest entity);

    Observable getIssueHttpUrl(String apiUrl);

    Observable getPullRequestUrl(String apiUrl);

    Observable getReleaseUrl(String apiUrl);
}
