package eapli.ecafeteria.domain.users;

/**
 * Created by nuno on 22/03/16.
 */
public class InvalidUserException extends Exception {
    public InvalidUserException(String message) {
        super(message);
    }
}
