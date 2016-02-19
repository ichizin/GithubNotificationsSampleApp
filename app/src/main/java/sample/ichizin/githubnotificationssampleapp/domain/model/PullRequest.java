package sample.ichizin.githubnotificationssampleapp.domain.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ichizin on 16/02/19.
 *
 * @author ichizin
 */
public class PullRequest {

    private long id;

    private String url;

    private String html_url;

    private String diff_url;

    private String patch_url;

    private String issue_url;

    private int number;

    private String state;

    private boolean locked;

    private String title;

    private Owner user;

    private String body;

    private String created_at;

    private String updated_at;

    private String closed_at;

    private String merged_at;

    private String merge_commit_sha;

    private String assignee;

    private String milestone;

    private String commits_url;

    private String review_comments_url;

    private String review_comment_url;

    private String comments_url;

    private String statuses_url;

    private Head head;

    private Head base;

    @SerializedName("_links")
    private Links links;

    private boolean merged;

    private boolean mergeable;

//    private String mergeable_state;
//
//    private String merged_by;

    private int comments;

    private int review_comments;

    private int commits;

    private int additions;

    private int deletions;

    private int changed_files;

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

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    public String getDiff_url() {
        return diff_url;
    }

    public void setDiff_url(String diff_url) {
        this.diff_url = diff_url;
    }

    public String getPatch_url() {
        return patch_url;
    }

    public void setPatch_url(String patch_url) {
        this.patch_url = patch_url;
    }

    public String getIssue_url() {
        return issue_url;
    }

    public void setIssue_url(String issue_url) {
        this.issue_url = issue_url;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Owner getUser() {
        return user;
    }

    public void setUser(Owner user) {
        this.user = user;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
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

    public String getClosed_at() {
        return closed_at;
    }

    public void setClosed_at(String closed_at) {
        this.closed_at = closed_at;
    }

    public String getMerged_at() {
        return merged_at;
    }

    public void setMerged_at(String merged_at) {
        this.merged_at = merged_at;
    }

    public String getMerge_commit_sha() {
        return merge_commit_sha;
    }

    public void setMerge_commit_sha(String merge_commit_sha) {
        this.merge_commit_sha = merge_commit_sha;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public String getMilestone() {
        return milestone;
    }

    public void setMilestone(String milestone) {
        this.milestone = milestone;
    }

    public String getCommits_url() {
        return commits_url;
    }

    public void setCommits_url(String commits_url) {
        this.commits_url = commits_url;
    }

    public String getReview_comments_url() {
        return review_comments_url;
    }

    public void setReview_comments_url(String review_comments_url) {
        this.review_comments_url = review_comments_url;
    }

    public String getReview_comment_url() {
        return review_comment_url;
    }

    public void setReview_comment_url(String review_comment_url) {
        this.review_comment_url = review_comment_url;
    }

    public String getComments_url() {
        return comments_url;
    }

    public void setComments_url(String comments_url) {
        this.comments_url = comments_url;
    }

    public String getStatuses_url() {
        return statuses_url;
    }

    public void setStatuses_url(String statuses_url) {
        this.statuses_url = statuses_url;
    }

    public Head getHead() {
        return head;
    }

    public void setHead(Head head) {
        this.head = head;
    }

    public Head getBase() {
        return base;
    }

    public void setBase(Head base) {
        this.base = base;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    public boolean isMerged() {
        return merged;
    }

    public void setMerged(boolean merged) {
        this.merged = merged;
    }

    public boolean isMergeable() {
        return mergeable;
    }

    public void setMergeable(boolean mergeable) {
        this.mergeable = mergeable;
    }

//    public String getMergeable_state() {
//        return mergeable_state;
//    }
//
//    public void setMergeable_state(String mergeable_state) {
//        this.mergeable_state = mergeable_state;
//    }
//
//    public String getMerged_by() {
//        return merged_by;
//    }
//
//    public void setMerged_by(String merged_by) {
//        this.merged_by = merged_by;
//    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public int getReview_comments() {
        return review_comments;
    }

    public void setReview_comments(int review_comments) {
        this.review_comments = review_comments;
    }

    public int getCommits() {
        return commits;
    }

    public void setCommits(int commits) {
        this.commits = commits;
    }

    public int getAdditions() {
        return additions;
    }

    public void setAdditions(int additions) {
        this.additions = additions;
    }

    public int getDeletions() {
        return deletions;
    }

    public void setDeletions(int deletions) {
        this.deletions = deletions;
    }

    public int getChanged_files() {
        return changed_files;
    }

    public void setChanged_files(int changed_files) {
        this.changed_files = changed_files;
    }
}
