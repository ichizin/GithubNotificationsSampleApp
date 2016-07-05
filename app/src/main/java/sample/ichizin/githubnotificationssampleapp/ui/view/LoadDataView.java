package sample.ichizin.githubnotificationssampleapp.ui.view;

import android.content.Context;

public interface LoadDataView {

    Context getContext();

    void showLoading();

    void hideLoading();

    void showError(String message);
}
