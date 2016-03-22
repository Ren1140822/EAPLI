package eapli.ecafeteria.domain.users;

import eapli.ecafeteria.domain.users.User;

/**
 * Created by nuno on 21/03/16.
 */
public class InvalidPasswordException extends Exception {

    User user;

    public InvalidPasswordException(String message, User user) {
        super(message);
        this.user =user;
    }
}
