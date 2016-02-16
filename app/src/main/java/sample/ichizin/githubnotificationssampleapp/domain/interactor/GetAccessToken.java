package sample.ichizin.githubnotificationssampleapp.domain.interactor;

import android.text.TextUtils;

import rx.Observable;
import rx.Subscriber;
import sample.ichizin.githubnotificationssampleapp.domain.executor.PostExecutionThread;
import sample.ichizin.githubnotificationssampleapp.domain.executor.ThreadExecutor;
import sample.ichizin.githubnotificationssampleapp.domain.repository.AuthRepository;
import sample.ichizin.githubnotificationssampleapp.exception.AuthLoginException;
import sample.ichizin.githubnotificationssampleapp.exception.InvalidParameterException;

/**
 * Created by ichizin on 16/02/15.
 *
 * @author ichizin
 */
public class GetAccessToken extends UseCase {

    private final AuthRepository authRepository;
    private String clientId;
    private String clientSecret;
    private String code;

    public GetAccessToken(AuthRepository authRepository,
            ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.authRepository = authRepository;
    }

    public void execute(String clientId, String clientSecret, String code, Subscriber Subscriber) {

        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.code = code;
        this.execute(Subscriber);
    }

    @Override
    protected Observable buildUseCaseObservable() {
        try {
            validateParameter();
            return this.authRepository.getAccessToken(this.clientId, this.clientSecret, this.code);
        } catch (InvalidParameterException e) {
            return Observable.error(e);
        }
    }

    /**
     * Parameter Check
     * @throws InvalidParameterException
     */
    private void validateParameter() throws AuthLoginException {

        if(TextUtils.isEmpty(this.clientId)
                || TextUtils.isEmpty(this.clientSecret)
                || TextUtils.isEmpty(this.code)) {
            throw new AuthLoginException();
        }
    }
}
