package sample.ichizin.githubnotificationssampleapp.presentation.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import sample.ichizin.githubnotificationssampleapp.R;
import sample.ichizin.githubnotificationssampleapp.domain.model.Notification;

/**
 *
 *
 * @author ichizin
 */
public class NotificationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<Notification> notifications;

    private final int HEADER_POSITION = 0;

    public interface NotificationAdapterLisnter {
        void onClickItem();
    }

    public NotificationAdapter(Context context) {
        this.context = context;
        this.notifications = new ArrayList<Notification>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.adapter_data_notification, parent, false);
        ViewDataHolder dataHolder = new ViewDataHolder(view);
        return dataHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        final ViewDataHolder dataHolder = (ViewDataHolder)holder;
        final Notification notification = getItem(position);

        dataHolder.repositoryName.setText(notification.getRepository().getName());
        dataHolder.subject.setText(notification.getSubject().getTitle());
        dataHolder.lastUpdate.setText(notification.getUpdated_at());
    }


    @Override
    public int getItemCount() {
        return this.notifications.size();
    }

    public Notification getItem(int position) {

        if(this.notifications == null || this.notifications.size() == 0) {
            return null;
        }
        return this.notifications.get(position);
    }

    public void add(List<Notification> notifications) {
        this.notifications.addAll(notifications);
        this.notifyDataSetChanged();
    }

    public void clear() {
        this.notifications.clear();
        this.notifyDataSetChanged();
    }

    static class ViewDataHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.notification_layout)
        LinearLayout notificationLayout;

        @Bind(R.id.repository_name)
        TextView repositoryName;

        @Bind(R.id.subject)
        TextView subject;

        @Bind(R.id.last_update)
        TextView lastUpdate;

        public ViewDataHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

    }
}
