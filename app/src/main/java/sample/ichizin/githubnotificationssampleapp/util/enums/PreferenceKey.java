package sample.ichizin.githubnotificationssampleapp.util.enums;

/**
 * PreferenceKey Enum
 *
 * @author ichizin
 */
public enum PreferenceKey {
    LOGIN(""),
    TOKEN_ACCESS_KEY("token_access_key"),
    ENCRYPTED_KEY("encrypted_key");


    private String key;

    public String getId() {
        return key;
    }

    PreferenceKey(String key) { this.key = key; }

}
