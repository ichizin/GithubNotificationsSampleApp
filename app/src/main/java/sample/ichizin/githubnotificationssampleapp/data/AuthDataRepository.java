package sample.ichizin.githubnotificationssampleapp.data;

import android.support.annotation.NonNull;

import retrofit.Retrofit;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.Headers;
import retrofit.http.POST;
import rx.Observable;
import sample.ichizin.githubnotificationssampleapp.domain.AccessToken;
import sample.ichizin.githubnotificationssampleapp.domain.repository.AuthRepository;

/**
 *
 *
 * @author ichizin
 */
public class AuthDataRepository implements AuthRepository {

    private final AuthApi authApi;

    public AuthDataRepository(Retrofit retrofit) {
        this.authApi = retrofit.create(AuthApi.class);
    }

    /**
     * Get Access Token
     * @param clientId
     * @param clientSecret
     * @param code
     * @return
     */
    @Override
    public Observable<AccessToken> getAccessToken(@NonNull String clientId,
                                                  @NonNull String clientSecret, @NonNull String code) {
        return this.authApi.getAccessToken(clientId, clientSecret, code);
    }


    interface AuthApi {

        @Headers({
                "Accept:application/vnd.github.v3.full+json",
                "Accept:application/json"
        })
        @FormUrlEncoded
        @POST("login/oauth/access_token")
        Observable<AccessToken> getAccessToken(@Field("client_id") String clientId,
                                               @Field("client_secret") String clientSecret,
                                               @Field("code") String code);
    }
}
