package sample.ichizin.githubnotificationssampleapp.util.enums;

/**
 * PreferenceKey Enum
 *
 * @author ichizin
 */
public enum PreferenceKey {
    LOGIN("");

    private String value;

    public String getId() {
        return value;
    }

    PreferenceKey(String id) {
        this.value = value;
    }

}
