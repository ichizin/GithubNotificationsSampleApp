package sample.ichizin.githubnotificationssampleapp.domain.repository;

import rx.Observable;
import sample.ichizin.githubnotificationssampleapp.domain.model.AccessToken;

/**
 * Created by ichizin on 16/02/15.
 *
 * @author ichizin
 */
public interface AuthRepository {

    /**
     * Get Access Token
     * @param clientId
     * @param client_secret
     * @param code
     * @return
     */
    Observable<AccessToken> getAccessToken(String clientId, String client_secret, String code);
}
