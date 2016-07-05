package sample.ichizin.githubnotificationssampleapp.domain;

import java.io.Serializable;

/**
 * Created by ichizin on 16/02/19.
 *
 * @author ichizin
 */
public class Head implements Serializable {

    private static final long serialVersionUID = 4532607511343142784L;

    private String label;

    private String ref;

    private String sha;

    private Owner user;

    private Repository repo;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getSha() {
        return sha;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }

    public Owner getUser() {
        return user;
    }

    public void setUser(Owner user) {
        this.user = user;
    }

    public Repository getRepo() {
        return repo;
    }

    public void setRepo(Repository repo) {
        this.repo = repo;
    }
}
