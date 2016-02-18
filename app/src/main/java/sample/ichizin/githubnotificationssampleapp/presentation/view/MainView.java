package sample.ichizin.githubnotificationssampleapp.presentation.view;

import java.util.List;

import sample.ichizin.githubnotificationssampleapp.domain.model.Notification;

/**
 * Created by ichizin on 16/02/16.
 *
 * @author ichizin
 */
public interface MainView extends LoadDataView {

    void displayLoginView();

    void addAdapter(List<Notification> notifications);

    void isRefreshing(boolean flag);

    void clearAdapter();

    void showErrorRefreshPage(boolean isShow);
}
