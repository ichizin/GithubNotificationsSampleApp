package sample.ichizin.githubnotificationssampleapp.domain.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import sample.ichizin.githubnotificationssampleapp.util.DateUtil;

/**
 * Created by ichizin on 16/02/16.
 *
 * @author ichizin
 */
public class Notification {

    private String id;

    private String reason;

    private boolean unread;

    private String updated_at;

    private String last_read_at;

    private String url;

    private Repository repository;

    private Subject subject;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public boolean isUnread() {
        return unread;
    }

    public void setUnread(boolean unread) {
        this.unread = unread;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public String getLocalizeLastUpdateTime() {
        try {
            return DateUtil.parseIso8601(updated_at);
        } catch (ParseException e) {
            return updated_at;
        }
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getLast_read_at() {
        return last_read_at;
    }

    public void setLast_read_at(String last_read_at) {
        this.last_read_at = last_read_at;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Repository getRepository() {
        return repository;
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
}
