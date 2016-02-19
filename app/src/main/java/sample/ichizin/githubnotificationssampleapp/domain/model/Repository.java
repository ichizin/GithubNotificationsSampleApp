package sample.ichizin.githubnotificationssampleapp.domain.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by ichizin on 16/02/16.
 *
 * @author ichizin
 */
public class Repository implements Serializable {

    private static final long serialVersionUID = 617349858006746512L;

    private long id;

    private String name;

    private String full_name;

    private String description;

    @SerializedName("private")
    private boolean private_repository;

    private boolean fork;

    private String url;

    private String html_url;

    private Owner owner;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isPrivate_repository() {
        return private_repository;
    }

    public void setPrivate_repository(boolean private_repository) {
        this.private_repository = private_repository;
    }

    public boolean isFork() {
        return fork;
    }

    public void setFork(boolean fork) {
        this.fork = fork;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }
}
