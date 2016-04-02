package eapli.ecafeteria.domain.users;

import eapli.ecafeteria.domain.users.SystemUser;

/**
 * Created by nuno on 21/03/16.
 */
public class InvalidPasswordException extends Exception {

    SystemUser user;

    public InvalidPasswordException(String message, SystemUser user) {
        super(message);
        this.user =user;
    }
}
