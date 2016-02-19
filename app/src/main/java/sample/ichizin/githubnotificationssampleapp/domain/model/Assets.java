package sample.ichizin.githubnotificationssampleapp.domain.model;

import java.io.Serializable;

/**
 * Created by ichizin on 16/02/19.
 *
 * @author ichizin
 */
public class Assets implements Serializable {

    private static final long serialVersionUID = 3983968249472771286L;

    private long id;

    private String url;

    private String name;

    private String lanel;

    private Owner uploader;

    private String cotent_type;

    private String state;

    private long size;

    private int download_count;

    private String created_at;

    private String updated_at;

    private String browser_download_url;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLanel() {
        return lanel;
    }

    public void setLanel(String lanel) {
        this.lanel = lanel;
    }

    public Owner getUploader() {
        return uploader;
    }

    public void setUploader(Owner uploader) {
        this.uploader = uploader;
    }

    public String getCotent_type() {
        return cotent_type;
    }

    public void setCotent_type(String cotent_type) {
        this.cotent_type = cotent_type;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public int getDownload_count() {
        return download_count;
    }

    public void setDownload_count(int download_count) {
        this.download_count = download_count;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getBrowser_download_url() {
        return browser_download_url;
    }

    public void setBrowser_download_url(String browser_download_url) {
        this.browser_download_url = browser_download_url;
    }
}
