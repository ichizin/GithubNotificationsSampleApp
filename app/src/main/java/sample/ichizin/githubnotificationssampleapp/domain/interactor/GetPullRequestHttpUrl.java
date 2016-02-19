package sample.ichizin.githubnotificationssampleapp.domain.interactor;

import rx.Observable;
import rx.Subscriber;
import sample.ichizin.githubnotificationssampleapp.domain.executor.PostExecutionThread;
import sample.ichizin.githubnotificationssampleapp.domain.executor.ThreadExecutor;
import sample.ichizin.githubnotificationssampleapp.domain.repository.NotificationApiRepository;

/**
 * Created by ichizin on 16/02/19.
 *
 * @author ichizin
 */
public class GetPullRequestHttpUrl extends UseCase {

    private final NotificationApiRepository notificationApiRepository;
    private String apiUrl;

    public GetPullRequestHttpUrl(ThreadExecutor threadExecutor,
                                 PostExecutionThread postExecutionThread, NotificationApiRepository notificationApiRepository) {
        super(threadExecutor, postExecutionThread);
        this.notificationApiRepository = notificationApiRepository;
    }

    public void execute(String apiUrl, Subscriber subscriber) {
        this.apiUrl = apiUrl;
        execute(subscriber);
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return this.notificationApiRepository.getPullRequestUrl(this.apiUrl);
    }
}
