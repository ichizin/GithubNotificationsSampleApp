package sample.ichizin.githubnotificationssampleapp.domain.interactor;

import android.text.TextUtils;

import rx.Observable;
import rx.Subscriber;
import sample.ichizin.githubnotificationssampleapp.domain.executor.PostExecutionThread;
import sample.ichizin.githubnotificationssampleapp.domain.executor.ThreadExecutor;
import sample.ichizin.githubnotificationssampleapp.domain.repository.NotificationApiRepository;
import sample.ichizin.githubnotificationssampleapp.exception.InvalidParameterException;

/**
 * Created by ichizin on 16/02/16.
 *
 * @author ichizin
 */
public class GetNotifiacetions extends UseCase {

    private String accessToken;
    private final NotificationApiRepository notificationApiRepository;

    public GetNotifiacetions(NotificationApiRepository notificationApiRepository,
                             ThreadExecutor threadExecutor,
                             PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.notificationApiRepository = notificationApiRepository;
    }

    public void execute(String accessToken, Subscriber subscriber) {
        this.accessToken = accessToken;
        super.execute(subscriber);
    }

    @Override
    protected Observable buildUseCaseObservable() {
        try {
            invalidParameter();
            return notificationApiRepository.getNotifications(this.accessToken);
        } catch (InvalidParameterException e) {
            return Observable.error(e);
        }
    }

    private void invalidParameter() throws InvalidParameterException {
        if(TextUtils.isEmpty(this.accessToken)) {
            throw new InvalidParameterException("AccessToken");
        }
    }
}
