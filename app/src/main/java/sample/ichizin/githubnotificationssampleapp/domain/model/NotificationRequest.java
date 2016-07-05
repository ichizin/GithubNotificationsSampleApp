package sample.ichizin.githubnotificationssampleapp.domain.model;

import android.text.TextUtils;

import java.util.HashMap;
/**
 * Created by ichizin on 16/02/18.
 *
 * @author ichizin
 */
public class NotificationRequest extends HashMap<String, Object> {

    public static class Builder {

        private boolean all = true;

        private boolean participating;

        private String since;

        private String before;

        public Builder() {}

        public Builder all(boolean all) {
            this.all = all;
            return this;
        }

        public Builder participating(boolean participating) {
            this.participating = participating;
            return this;
        }

        public Builder since(String since) {
            this.since = since;
            return this;
        }

        public Builder brefore(String brefore) {
            this.before = before;
            return this;
        }

        public NotificationRequest build() { return new NotificationRequest(this); }
    }

    private NotificationRequest(Builder builder) {

        this.put("all", builder.all);
        this.put("participating", builder.participating);
        if(TextUtils.isEmpty(builder.since)) {
            this.put("since", "");
        } else {
            this.put("since", builder.since);
        }

        if(TextUtils.isEmpty(builder.before)) {
            this.put("before","");
        } else {
            this.put("before", builder.before);
        }

    }

}
