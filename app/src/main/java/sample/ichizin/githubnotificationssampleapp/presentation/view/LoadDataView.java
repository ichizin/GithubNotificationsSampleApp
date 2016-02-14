package sample.ichizin.githubnotificationssampleapp.presentation.view;

import android.content.Context;

public interface LoadDataView {

    Context getContext();

    void showLoading();

    void hideLoading();

    void showError();
}
