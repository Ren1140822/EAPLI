package eapli.framework.authz;

/**
 * TODO should this interface exist?
 *
 * Created by nuno on 20/03/16.
 */
public interface Authorisable<T> {

    public boolean isAuthorizedTo(T action);
}
