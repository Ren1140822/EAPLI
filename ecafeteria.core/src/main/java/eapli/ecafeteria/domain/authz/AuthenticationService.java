package eapli.ecafeteria.domain.authz;

import eapli.ecafeteria.persistence.PersistenceContext;

public class AuthenticationService {

    /**
     * Checks if a user can be authenticated with the username/password pair
     *
     * FIXME we are using exceptions for logic behaviour handling...
     *
     * @param username
     * @param pass
     * @return the authenticated user or null otherwise
     */
    public Session authenticate(Username username, Password pass) throws UnableToAuthenticateException {
        final SystemUser user = retrieveUser(username);
        if (null == user) {
            throw new UnableToAuthenticateException("Invalid User");
        }
        if (user.passwordMatches(pass) && user.isActive()) {
            return createSessionForUser(user);
        } else {
            throw new UnableToAuthenticateException("Invalid User");
        }
    }

    /**
     * Checks if a user can be authenticated with the username/password pair,
     * using Optional.
     * http://www.leveluplunch.com/java/examples/find-element-in-list/
     *
     * @param username
     * @param pass
     * @return the authenticated user or null otherwise
     */
    /*
     * public Session authenticate(Username username, Password pass) throws
     * InvalidUserException, InvalidPasswordException {
     *
     * try { final User user = retrieveUser(username);
     * user.passwordMatches(pass); return createSessionForUser(user); }
     * catch(NoSuchElementException ex){ throw new InvalidUserException(
     * "Invalid User"); } }
     */
    private Session createSessionForUser(SystemUser user) {
        return new Session(user);
    }

    private SystemUser retrieveUser(Username userName) {
        return PersistenceContext.repositories().users().findById(userName);
    }
}
