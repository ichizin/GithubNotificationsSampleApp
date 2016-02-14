package sample.ichizin.githubnotificationssampleapp.presentation.presenter;

import android.support.annotation.NonNull;

import sample.ichizin.githubnotificationssampleapp.presentation.view.LoadDataView;

public interface Presenter <T extends LoadDataView> {

    void resume();

    void pause();

    void destroy();

    void attachView(@NonNull T view);
}
