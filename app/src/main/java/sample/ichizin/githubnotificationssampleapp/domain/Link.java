package sample.ichizin.githubnotificationssampleapp.domain;

import java.io.Serializable;

/**
 * Created by ichizin on 16/02/19.
 *
 * @author ichizin
 */
public class Link implements Serializable {

    private static final long serialVersionUID = 7445309910540713981L;
    String href;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}
