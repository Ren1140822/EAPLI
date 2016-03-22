package eapli.ecafeteria.domain.users;

/**
 * Created by nuno on 22/03/16.
 */
public class NoUserSessionInitiatedException extends Exception {
    public NoUserSessionInitiatedException(String message) {
        super(message);
    }
}
