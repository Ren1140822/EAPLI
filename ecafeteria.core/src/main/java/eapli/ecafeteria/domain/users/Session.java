/**
 *
 */
package eapli.ecafeteria.domain.users;

import java.util.UUID;

import eapli.framework.domain.ValueObject;

/**
 * @author pgsou_000
 *
 */
public class Session implements ValueObject {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private final User user;
    private final UUID token;

    public User authenticatedUser() {
        return this.user;
    }

    public Session(User user) {
        if (user == null) {
            throw new IllegalStateException("user must not be null");
        }
        this.user = user;
        this.token = UUID.randomUUID();
    }

    @Override
    public String toString() {
        return this.user.id() + "@" + this.token;
    }
}
