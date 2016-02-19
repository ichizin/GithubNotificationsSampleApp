package sample.ichizin.githubnotificationssampleapp.domain.model;

import java.io.Serializable;

/**
 * Created by ichizin on 16/02/19.
 *
 * @author ichizin
 */
public class Issue implements Serializable {

    private static final long serialVersionUID = 2647003676424319336L;

    private long id;

    private String html_url;

//    private int number;
//
//    private String url;
//
//    private String repository_url;
//
//    private String labels_url;
//
//    private String comments_url;
//
//    private String events_url;
//
//    private String title;
//
//    private Owner user;
//
//    private String state;
//
//    private boolean locked;
//
//    private int comments;
//
//    private String created_at;
//
//    private String updated_at;
//
//    private String closed_at;
//
//    private String body;
//
//    private Owner closed_by;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

//    public int getNumber() {
//        return number;
//    }
//
//    public void setNumber(int number) {
//        this.number = number;
//    }
//
//    public String getUrl() {
//        return url;
//    }
//
//    public void setUrl(String url) {
//        this.url = url;
//    }
//
//    public String getRepository_url() {
//        return repository_url;
//    }
//
//    public void setRepository_url(String repository_url) {
//        this.repository_url = repository_url;
//    }
//
//    public String getLabels_url() {
//        return labels_url;
//    }
//
//    public void setLabels_url(String labels_url) {
//        this.labels_url = labels_url;
//    }
//
//    public String getComments_url() {
//        return comments_url;
//    }
//
//    public void setComments_url(String comments_url) {
//        this.comments_url = comments_url;
//    }
//
//    public String getEvents_url() {
//        return events_url;
//    }
//
//    public void setEvents_url(String events_url) {
//        this.events_url = events_url;
//    }
//
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public Owner getUser() {
//        return user;
//    }
//
//    public void setUser(Owner user) {
//        this.user = user;
//    }
//
//    public String getState() {
//        return state;
//    }
//
//    public void setState(String state) {
//        this.state = state;
//    }
//
//    public boolean isLocked() {
//        return locked;
//    }
//
//    public void setLocked(boolean locked) {
//        this.locked = locked;
//    }
//
//    public int getComments() {
//        return comments;
//    }
//
//    public void setComments(int comments) {
//        this.comments = comments;
//    }
//
//    public String getCreated_at() {
//        return created_at;
//    }
//
//    public void setCreated_at(String created_at) {
//        this.created_at = created_at;
//    }
//
//    public String getUpdated_at() {
//        return updated_at;
//    }
//
//    public void setUpdated_at(String updated_at) {
//        this.updated_at = updated_at;
//    }
//
//    public String getClosed_at() {
//        return closed_at;
//    }
//
//    public void setClosed_at(String closed_at) {
//        this.closed_at = closed_at;
//    }
//
//    public String getBody() {
//        return body;
//    }
//
//    public void setBody(String body) {
//        this.body = body;
//    }
//
//    public Owner getClosed_by() {
//        return closed_by;
//    }
//
//    public void setClosed_by(Owner closed_by) {
//        this.closed_by = closed_by;
//    }
}
