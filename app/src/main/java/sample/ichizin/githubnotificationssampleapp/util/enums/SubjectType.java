package sample.ichizin.githubnotificationssampleapp.util.enums;

import java.util.EnumSet;

/**
 * Created by ichizin on 16/02/19.
 *
 * @author ichizin
 */
public enum SubjectType {
    ISSUE("Issue"),
    PULL_REQUEST("PullRequest"),
    RELEASE("Release"),
    OTHER("");


    private String type;

     SubjectType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    static EnumSet<SubjectType> ids = EnumSet.allOf(SubjectType.class);

    public static SubjectType valueOfId(String type) {
        for (SubjectType i : ids)  {
            if(i.getType().equals(type)) { return i; }
        }
        return OTHER;
    }

}
