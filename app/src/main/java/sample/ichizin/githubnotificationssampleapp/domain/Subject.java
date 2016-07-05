package sample.ichizin.githubnotificationssampleapp.domain;

import java.io.Serializable;

/**
 * Created by ichizin on 16/02/16.
 *
 * @author ichizin
 */
public class Subject implements Serializable {

    private static final long serialVersionUID = 5948100450523512129L;

    private String title;

    private String url;

    private String latest_comment_url;

    private String type;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLatest_comment_url() {
        return latest_comment_url;
    }

    public void setLatest_comment_url(String latest_comment_url) {
        this.latest_comment_url = latest_comment_url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
