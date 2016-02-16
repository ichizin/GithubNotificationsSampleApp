package sample.ichizin.githubnotificationssampleapp.domain.model;

/**
 * Created by ichizin on 16/02/16.
 *
 * @author ichizin
 */
public class Subject {

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
}
