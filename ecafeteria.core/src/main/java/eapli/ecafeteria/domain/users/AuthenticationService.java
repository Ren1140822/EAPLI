package eapli.ecafeteria.domain.users;

import eapli.ecafeteria.persistence.PersistenceContext;

public class AuthenticationService {

	/**
	 * Checks if a user can be authenticated with the username/password pair
	 *
	 * @param username
	 * @param pass
	 * @return the authenticated user or null otherwise
	 */
	public Session authenticate(Username username, Password pass)
			throws InvalidUserException, InvalidPasswordException {
		final User user = retrieveUser(username);
		if (null == user) {
			throw new InvalidUserException("Invalid User");
		}
		user.passwordMatches(pass);
		return createSessionForUser(user);
	}


	/**
	 * Checks if a user can be authenticated with the username/password pair, using Optional.
	 * http://www.leveluplunch.com/java/examples/find-element-in-list/
	 * @param username
	 * @param pass
	 * @return the authenticated user or null otherwise
	 */
	/*public Session authenticate(Username username, Password pass)
			throws InvalidUserException, InvalidPasswordException {

        try {
            final User user = retrieveUser(username);
            user.passwordMatches(pass);
            return createSessionForUser(user);
        }
        catch(NoSuchElementException ex){
            throw new InvalidUserException("Invalid User");
        }
    }*/
	private Session createSessionForUser(User user) {
		return new Session(user);
	}

	private User retrieveUser(Username userName) {
		return PersistenceContext.repositories().users().findById(userName);
	}
}
