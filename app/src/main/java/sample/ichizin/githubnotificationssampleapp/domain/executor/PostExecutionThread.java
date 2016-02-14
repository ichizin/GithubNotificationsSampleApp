package sample.ichizin.githubnotificationssampleapp.domain.executor;

import rx.Scheduler;

/**
 * Created by ichizin on 16/02/14.
 *
 * @author ichizin
 */
public interface PostExecutionThread {
    Scheduler getScheduler();
}
